package com.aliyun.iot;/**
 * Created by zhaoyipc on 2017/7/13.
 */

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.iot.model.v20170620.PubRequest;
import com.aliyuncs.iot.model.v20170620.PubResponse;
import com.aliyuncs.iot.model.v20170620.QueryDeviceRequest;
import com.aliyuncs.iot.model.v20170620.QueryDeviceResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhaoyi
 * @date 2017-07-2017/7/13-14:08
 */
public class InitSDK {
    private DefaultAcsClient client=null;
    //初始化sdk
    public void initsdk() {
        String accessKey = "LTAIFlCD8jBQXlqH";
        String accessSecret = "TPunQG9dHa81VlI63JuMPyK8X8Hgs3";
        try {
            DefaultProfile.addEndpoint("cn-shanghai", "cn-shanghai", "Iot", "iot.cn-shanghai.aliyuncs.com");
        } catch (ClientException e) {
            e.printStackTrace();
        }
        IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", accessKey, accessSecret);
        client = new DefaultAcsClient(profile); //初始化SDK客户端
    }

    public InitSDK(){
        initsdk();
    }

    public DefaultAcsClient getClient() {
        return client;
    }

    //发布消息到topic测试
    public void pubMessageToTopic() {
        PubRequest request = new PubRequest();
        request.setProductKey("7Pi3WAFJhC6");
        request.setMessageContent(Base64.encodeBase64String("hello world".getBytes()));
        request.setTopicFullName("/7Pi3WAFJhC6/kitotv01/data");
        request.setQos(0); //目前支持QoS0和QoS1
        PubResponse response = null;
        try {
            response = client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        System.out.println(response.getSuccess());
        System.out.println(response.getErrorMessage());
    }







    public QueryDeviceResponse queryDeviceInfoList(int currentPage, String productKey, int pageSize){
        QueryDeviceRequest queryDeviceRequest = new QueryDeviceRequest();
        queryDeviceRequest.setCurrentPage(currentPage);
        queryDeviceRequest.setProductKey(productKey);
        queryDeviceRequest.setPageSize(pageSize);
        QueryDeviceResponse response = null;
        try {
            response = client.getAcsResponse(queryDeviceRequest);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if(response != null){
            System.out.println("Response requestId:"+response.getRequestId()+" isSuccess:"+response.getSuccess() +" Error:"+response.getErrorMessage());
        }
        return response;
    }



    public static void main(String[] args) {
        InitSDK initSDK=new InitSDK();
        initSDK.pubMessageToTopic();



//        QueryDeviceResponse response=initSDK.queryDeviceInfoList(1,"7Pi3WAFJhC6",10);
//        JSONObject j=JSONObject.fromObject(response);
//        System.out.println("j.toString() = " + j.toString());
    }





}
