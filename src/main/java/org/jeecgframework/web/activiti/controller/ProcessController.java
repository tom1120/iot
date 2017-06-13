package org.jeecgframework.web.activiti.controller;/**
 * Created by zhaoyipc on 2017/6/8.
 */

import net.sf.json.JSONObject;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:TODO:(工作流程运行实例相关处理)
 * @author zhaoyi
 * @date 2017-06-2017/6/8-14:37
 */
@Controller
@RequestMapping("/processController")
public class ProcessController {
    private static final Logger logger = Logger.getLogger(ActivitiController.class);

    @Autowired
    HistoryService historyService;
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    ManagementService managementService;



    /**
     * 流程定义列表
     */
    @RequestMapping(params = "historyList")
    public ModelAndView processList(HttpServletRequest request) {
        return new ModelAndView("jeecg/activiti/process/processHistoryList");
    }

    /**
     * 显示历史数据列表
     * @param request
     * @param response
     * @param grid
     */
    @RequestMapping(value = "/history/datagrid")
    public void datagrid(HttpServletRequest request, HttpServletResponse response,DataGrid grid){
        String searchfield=request.getParameter("searchfield");

        HistoricProcessInstanceQuery query =historyService.createHistoricProcessInstanceQuery();
        List<HistoricProcessInstance> list = new ArrayList<>();
        String sql="";

        if(searchfield!=null&&!searchfield.equals("")){
            String searchfieldValue=request.getParameter(searchfield);
            if(!searchfieldValue.equals("")){

                if(searchfield.equals("processDefinitionName")){
                    sql="select * from "+managementService.getTableName(ProcessDefinition.class)+" where name_ like #{searchfieldValue}";
                    //获取流程定义id
                    List<ProcessDefinition> processDefinitions=repositoryService.createNativeProcessDefinitionQuery().
                            sql(sql).parameter("searchfieldValue","%"+searchfieldValue+"%").list();
                    //根据流程定义id获取
                    for(ProcessDefinition processDefinition:processDefinitions){

                        List<HistoricProcessInstance> historicProcessInstances=historyService.createHistoricProcessInstanceQuery().processDefinitionId(processDefinition.getId())
                                .list();
                        list.addAll(historicProcessInstances);
                    }





                }else{
                    list=historyService.createNativeHistoricProcessInstanceQuery().
                            sql("select * from "+managementService.getTableName(HistoricProcessInstance.class)+" where "+searchfield+" like #{searchfieldValue}").
                            parameter("searchfieldValue","%"+searchfieldValue+"%").list();
                }



            }else {
                list=query.list();
            }

        }else{

            list=query.list();
        }



        StringBuffer rows = new StringBuffer();
        int i = 0;
        for(HistoricProcessInstance pi : list){
            i++;

            //根据流程定义id获取流程名称
            ProcessDefinitionQuery processDefinitionQuery=repositoryService.createProcessDefinitionQuery().processDefinitionId(pi.getProcessDefinitionId());
            ProcessDefinition processDefinition=processDefinitionQuery.singleResult();
            String processDefinitionName=processDefinition.getName();

            rows.append("{'id':"+i
                    +",'procInstId':'"+pi.getId()
                    +"','businessKey':'"+pi.getBusinessKey()
                    +"','processDefinitionName':'"+processDefinitionName
                    +"','procDefId':'"+pi.getProcessDefinitionId()
                    +"','startTime':'"+pi.getStartTime()
                    +"','endTime':'"+pi.getEndTime()
                    +"','duration':'"+pi.getDurationInMillis()
                    +"','startUserId':'"+pi.getStartUserId()
                    +"','startActId':'"+pi.getStartActivityId()
                    +"','endActId':'"+pi.getEndActivityId()
                    +"','superProcessInstanceId':'"+pi.getSuperProcessInstanceId()
                    +"','deleteReason':'"+pi.getDeleteReason()

                    +"'},");
        }
        String rowStr = StringUtils.substringBeforeLast(rows.toString(), ",");

//		JSONObject jObject = JSONObject.fromObject("{'total':"+query.count()+",'rows':["+rowStr+"]}");
        JSONObject jObject = JSONObject.fromObject("{'total':"+list.size()+",'rows':["+rowStr+"]}");
        ActivitiController.responseDatagrid(response, jObject);

    }

    /**
     * 删除历史流程实例
     * @param processInstanceId
     * @return
     */
    @RequestMapping(value = "history/historic-process-instances/{processInstanceId}")
    @ResponseBody
    public AjaxJson delete(@PathVariable("processInstanceId") String processInstanceId){
        AjaxJson ajaxJson=new AjaxJson();
        ajaxJson.setMsg("删除失败");
        ajaxJson.setSuccess(false);
        try {
            historyService.deleteHistoricProcessInstance(processInstanceId);
            ajaxJson.setMsg("删除成功！");
            ajaxJson.setSuccess(true);
        }catch (Exception e){
            logger.error("删除流程实例"+processInstanceId+"异常");
            e.printStackTrace();
        }

        return ajaxJson;
    }







}
