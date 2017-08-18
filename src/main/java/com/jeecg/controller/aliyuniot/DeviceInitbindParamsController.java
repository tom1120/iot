package com.jeecg.controller.aliyuniot;/**
 * Created by zhaoyipc on 2017/8/5.
 */

import com.jeecg.entity.aliyuniot.DeviceInitbindParams;
import com.jeecg.service.aliyuniot.DeviceInitbindParamsServiceI;
import net.sf.json.JSONObject;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * @author zhaoyi
 * @date 2017-08-2017/8/5-8:42
 */
@Controller
@RequestMapping("/DeviceInitBindParams")
public class DeviceInitbindParamsController extends BaseController{
    private static final Logger logger=Logger.getLogger(DeviceInitbindParamsController.class);
    @Autowired
    private DeviceInitbindParamsServiceI deviceInitbindParamsService;

    @RequestMapping(value = "addOrUpdate",method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson addOrUpdate(HttpServletRequest request, @RequestBody String jsonString){
        try {
            jsonString= URLDecoder.decode(jsonString,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        AjaxJson json=new AjaxJson();
        json.setSuccess(false);
        json.setMsg("云平台设备参数写入失败!");
        logger.error("接收到设备写入请求!");
        JSONObject jo=new JSONObject();
        jo=jo.fromObject(jsonString);
        String authUrl=jo.getString("authUrl");
        String productKey=jo.getString("productKey");
        String deviceName=jo.getString("deviceName");
        String deviceSecret=jo.getString("deviceSecret");
        String deviceSN=jo.getString("deviceSN");
        String wifiMac=jo.getString("wifiMac");
        String osNameVersion=jo.getString("osNameVersion");

        DeviceInitbindParams deviceInitbindParams=new DeviceInitbindParams();
        deviceInitbindParams.setAuthUrl(authUrl);
        deviceInitbindParams.setProductKey(productKey);
        deviceInitbindParams.setDeviceKey(deviceName);
        deviceInitbindParams.setDeviceSecret(deviceSecret);
        deviceInitbindParams.setDeviceSn(deviceSN);
        deviceInitbindParams.setWifiMac(wifiMac);
        deviceInitbindParams.setOsNameVersion(osNameVersion);
        try {
            //判断是否存在，如设备记录已经写过则更新相关记录，否则则创建新记录
            List<DeviceInitbindParams> deviceInitbindParamsList0=deviceInitbindParamsService.findHql("from DeviceInitbindParams where productKey=? and deviceKey=?",new String[]{productKey,deviceName});

            //改变为device_sn设备序号来判断设备是否写过，写过则更新
            List<DeviceInitbindParams> deviceInitbindParamsList=deviceInitbindParamsService.findHql("from DeviceInitbindParams where deviceSn=?",new String[]{deviceSN});

            if(deviceInitbindParamsList0.size()==0){
                if(deviceInitbindParamsList.size()==0){
                    deviceInitbindParamsService.save(deviceInitbindParams);//新增
                }else{
                    deviceInitbindParamsService.deleteAllEntitie(deviceInitbindParamsList);//删除，然后新增
                    deviceInitbindParamsService.save(deviceInitbindParams);

                }
                json.setSuccess(true);
                json.setMsg("云平台设备参数写入成功！");
            }else {
                DeviceInitbindParams d=deviceInitbindParamsList0.get(0);
                if(d.getDeviceSn().equals(deviceSN)){
                    json.setMsg("此设备参数云平台已登记！");
                }else {
                    json.setMsg("此设备参数已经被"+d.getDeviceSn()+"设备初始化");
                }

            }

        }catch (Exception e){
            e.printStackTrace();

        }


        return json;




    }


}
