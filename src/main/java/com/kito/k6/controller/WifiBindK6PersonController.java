package com.kito.k6.controller;/**
 * Created by zhaoyipc on 2017/9/15.
 */

import com.jeecg.entity.person.PersonEntity;
import com.kito.k6.entity.HrStaffInfo;
import com.kito.k6.service.WifiBindK6PersonService;
import net.sf.json.JSONObject;
import org.activiti.engine.history.HistoricTaskInstance;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.DynamicDBUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.activiti.controller.ActivitiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author zhaoyi
 * @date 2017-09-2017/9/15-10:41
 */
@Controller
@RequestMapping("/wifiBindK6PersonController")
public class WifiBindK6PersonController extends BaseController{
    private static final Logger logger= LoggerFactory.getLogger(WifiBindK6PersonController.class);
    @Autowired
    WifiBindK6PersonService wifiBindK6PersonService;

    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/kito/k6/wifiBindK6PersonList");
    }


    private Map showParams(HttpServletRequest request) {
        Map map = new HashMap();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();

            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    map.put(paramName, paramValue);
                }
            }
        }

        Set<Map.Entry<String, String>> set = map.entrySet();
        logger.debug("------------------------------");
        for (Map.Entry entry : set) {
            logger.debug(entry.getKey() + ":" + entry.getValue());
        }
        logger.debug("------------------------------");
        return map;
    }

    @RequestMapping(params = "datagrid")
    public void datagrid(HrStaffInfo hrStaffInfo, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
/*        CriteriaQuery cq = new CriteriaQuery(HrStaffInfo.class, dataGrid);
        //查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hrStaffInfo, request.getParameterMap());
        this.wifiBindK6PersonService.getDataGridReturn(cq, true);*/

        int page=dataGrid.getPage();//当前页
        int rows=dataGrid.getRows();//每页显示记录数

        Map map=showParams(request);

        String field=dataGrid.getField();
        field=StringUtils.substringBeforeLast(field,",");
        String[] fields=field.split(",");
        String queryString="";
        for(String s:fields){
            String value= (String) map.get(s);
            if(value!=null&&!value.equals("")){
                queryString+=s+" like "+ "'%" +value+"%' and ";
            }
        }

        queryString=StringUtils.substringBeforeLast(queryString," and");


        String sql="select count(*) as num from hrstaffinfo";
        if(!queryString.equals("")){
            sql+=" where "+queryString;
        }
        Map numMap= (Map) DynamicDBUtil.findOne("sqlserver",sql,null);
        logger.debug("numMap = " +numMap.get("num") );
        dataGrid.setTotal((Integer)numMap.get("num"));



        //分页查询
        sql="select top "+(page)*rows+" staffid,staffcode,name,mobile_wifi_mac,mobile_wifi_mac_flag from hrstaffinfo except " +
                " select top "+(page-1)*rows+" staffid,staffcode,name,mobile_wifi_mac,mobile_wifi_mac_flag from hrstaffinfo";
        if(!queryString.equals("")){
            sql="select top "+(page)*rows+" staffid,staffcode,name,mobile_wifi_mac,mobile_wifi_mac_flag from hrstaffinfo "+"where "+queryString+"except " +
                    " select top "+(page-1)*rows+" staffid,staffcode,name,mobile_wifi_mac,mobile_wifi_mac_flag from hrstaffinfo "+"where "+queryString;
        }
        List<Map<String,Object>> list=DynamicDBUtil.findList("sqlserver",sql,null);
/*        StringBuffer rows = new StringBuffer();

        for(Map map : list){
            rows.append("{'id':'"+map.get("id")
                    +"','staffcode':'" +map.get("staffcode")
                    +"','name':'"+map.get("name")
                    +"','mobileWifiMac':'" +map.get("mobileWifiMac")
                    +"','mobileWifiMacFlag':'"+map.get("mobileWifiMacFlag")+"'},");
        }
        String rowStr = StringUtils.substringBeforeLast(rows.toString(), ",");
        JSONObject jObject = JSONObject.fromObject("{'total':"+list.size()+",'rows':["+rowStr+"]}");
        ActivitiController.responseDatagrid(response, jObject);*/

        dataGrid.setResults(list);
        TagUtil.datagrid(response,dataGrid);


    }


    @RequestMapping(params = "addorupdate")
    public ModelAndView addorupdate(HrStaffInfo hrStaffInfo, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(hrStaffInfo.getStaffId())) {
            String sql="select staffid,staffcode,name,mobile_wifi_mac,mobile_wifi_mac_flag from hrstaffinfo where staffid="+hrStaffInfo.getStaffId();
            Map map= (Map) DynamicDBUtil.findOne("sqlserver",sql,null);
            hrStaffInfo.setStaffId((Integer) map.get("staffid"));
            hrStaffInfo.setName((String) map.get("name"));
            hrStaffInfo.setMobileWifiMac((String) map.get("mobile_wifi_mac"));
            hrStaffInfo.setMobileWifiMacFlag((Integer) map.get("mobile_wifi_mac_flag"));
            req.setAttribute("hrStaffInfo", hrStaffInfo);
        }
        return new ModelAndView("com/kito/k6/wifiBindK6Person");
    }




}
