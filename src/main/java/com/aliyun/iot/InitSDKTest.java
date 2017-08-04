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
import com.jeecg.entity.aliyuniot.IotConfig;
import org.apache.commons.codec.binary.Base64;
import org.jeecgframework.core.util.GetClassDir;
import org.jeecgframework.core.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author zhaoyi
 * @date 2017-07-2017/7/13-14:08
 */

public class InitSDKTest {
    private DefaultAcsClient client=null;

    private static String accessKey;
    private static String accessSecret;
    //公网Endpoint
    private static String accountPublicEndpoint;
    //私网Endpoint
    private static String accountPrivateEndpoint;
    //VpcEndpoint
    private static String accountVpcEndpoint;

    //初始化sdk
    public void initsdk() {
//        String filePath= GetClassDir.getInstance().getResourcePath("/iotconfig.properties");
        String filePath= "iotconfig.properties";
        //这个工具类直接从classpath路径下找
        PropertiesUtil propertiesUtil=new PropertiesUtil(filePath);
        Properties properties= propertiesUtil.getProperties();

        this.accessKey=properties.getProperty("accessKey");
        this.accessSecret=properties.getProperty("accessSecret");
        this.accountPublicEndpoint=properties.getProperty("accountPublicEndpoint");
        this.accountPrivateEndpoint=properties.getProperty("accountPrivateEndpoint");
        this.accountVpcEndpoint=properties.getProperty("accountVpcEndpoint");


        try {
            DefaultProfile.addEndpoint("cn-shanghai", "cn-shanghai", "Iot", "iot.cn-shanghai.aliyuncs.com");
        } catch (ClientException e) {
            e.printStackTrace();
        }
        IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", accessKey, accessSecret);
        client = new DefaultAcsClient(profile); //初始化SDK客户端
    }

    public InitSDKTest(){
//        initsdk();
    }

    public DefaultAcsClient getClient() {
        return client;
    }

    //发布消息到topic测试
    public void pubMessageToTopic() {
        PubRequest request = new PubRequest();
        request.setProductKey("PCfvBOR1A1i");
        request.setMessageContent(Base64.encodeBase64String("hello world".getBytes()));
        request.setTopicFullName("/PCfvBOR1A1i/kito_android_tv01/get");
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
        InitSDKTest initSDK=new InitSDKTest();
        initSDK.initsdk();
        initSDK.pubMessageToTopic();



//        QueryDeviceResponse response=initSDK.queryDeviceInfoList(1,"7Pi3WAFJhC6",10);
//        JSONObject j=JSONObject.fromObject(response);
//        System.out.println("j.toString() = " + j.toString());
    }





}
