package com.aliyun.iot;/**
 * Created by zhaoyipc on 2017/7/13.
 */

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.iot.model.v20160530.RevertRpcRequest;
import com.aliyuncs.iot.model.v20160530.RevertRpcResponse;
import com.aliyuncs.iot.model.v20170620.*;
import com.jeecg.entity.aliyuniot.ProductInfoEntity;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author zhaoyi
 * @date 2017-07-2017/7/13-16:01
 */
@Component
public class ProductApi {
    @Autowired
    private InitSDK initSDK;

    /**
     * 创建产品api
     * @param catid
     * @param productDesc
     * @param productName
     * @return productInfoEntity 产品信息实例
     *
     {
    RequestId:8AE93DAB-958F-49BD-BE45-41353C6DEBCE,
    Success:true,
    ProductInfo:{
    ProductKey:...,
    CatId:56000,
    ProductName:工业产品
    }
    }
     *
     */
    public ProductInfoEntity createProduct(String productName,Long catid,String productDesc){
        ProductInfoEntity productInfoEntity=new ProductInfoEntity();

        CreateProductRequest request = new CreateProductRequest();
        request.setName(productName);
        request.setCatId(catid);
        request.setDesc(productDesc);

        CreateProductResponse response = null;
        try {
            DefaultAcsClient client=initSDK.getClient();
            response = client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if(response != null){
            System.out.println("Response requestId:"+response.getRequestId()+" isSuccess:"+response.getSuccess() +" Error:"+response.getErrorMessage()+" ProductInfo:"+response.getProductInfo());
            CreateProductResponse.ProductInfo productInfo=response.getProductInfo();
            productInfoEntity.setCatid(productInfo.getCatId());
            productInfoEntity.setGmtcreate(productInfo.getGmtCreate());
            productInfoEntity.setGmtmodified(productInfo.getGmtModified());
            productInfoEntity.setProductdesc(productInfo.getProductDesc());
            productInfoEntity.setProductname(productInfo.getProductName());
            productInfoEntity.setProductkey(productInfo.getProductKey());
            productInfoEntity.setProductsecret(productInfo.getProductSecret());
        }

        return productInfoEntity;
    }


    /**
     *更新产品名称
     * @param productKey
     * @param productName
     * @param catid
     * @param productDesc
     * @return boolean 是否成功更新
     *
     {
    "RequestId":"C4FDA54C-4201-487F-92E9-022F42387458",
    "Success":true,
    }
     *
     */
    public boolean updateProduct(String productKey,String productName,Long catid,String productDesc){
        boolean b=false;
        UpdateProductRequest request = new UpdateProductRequest();
        request.setProductKey(productKey);
        request.setProductName(productName);
        request.setProductDesc(productDesc);
        request.setCatId(catid);
        UpdateProductResponse response = null;
        try {
            DefaultAcsClient client=initSDK.getClient();
            response = client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if(response != null){
            System.out.println("Response requestId:"+response.getRequestId()+" isSuccess:"+response.getSuccess() +" Error:"+response.getErrorMessage());
            b= response.getSuccess();
        }
        return b;

    }

    /**
     * 查询产品的设备列表
     * @param currentPage 设备列表显示页面当前页数
     * @param productKey 产品唯一ID
     * @param pageSize 设备列表显示页面大小
     * @return
     *
     {
    PageCount:1,
    Data:{
    DeviceInfo:[
    {DeviceId:..., DeviceName:..., ProductKey:..., DeviceSecret:..., GmtCreate:Thu, 17-Nov-2016 02:08:12 GMT, GmtModified:Thu, 17-Nov-2016 02:08:12 GMT}
    ]
    },
    PageSize:10,
    Page:1,
    Total:9
    RequestId:06DC77A0-4622-42DB-9EE0-26A6E1FA08D3,
    Success:true,
    }
     *
     */
    public QueryDeviceResponse queryDeviceInfoList(int currentPage,String productKey,int pageSize){
        QueryDeviceRequest queryDeviceRequest = new QueryDeviceRequest();
        queryDeviceRequest.setCurrentPage(currentPage);
        queryDeviceRequest.setProductKey(productKey);
        queryDeviceRequest.setPageSize(pageSize);
        QueryDeviceResponse response = null;
        try {
            DefaultAcsClient client=initSDK.getClient();
            response = client.getAcsResponse(queryDeviceRequest);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if(response != null){
            System.out.println("Response requestId:"+response.getRequestId()+" isSuccess:"+response.getSuccess() +" Error:"+response.getErrorMessage());
        }
        return response;
    }

    /**
     * 使用场景：用户通过服务器端生成设备。目前只支持单个设备生成。
     * 设备注册
     * @param productKey 产品key
     * @param deviceName 设备名
     * @return
     *
    {
    "RequestId":"120F5EB3-7023-4F0C-B419-9303AB336885",
    "Success":true
    "DeviceId":"", //阿里云颁发的设备id 全局唯一
    "DeviceName":"",//设备名称，用户自定义或系统生成
    "DeviceSecret":"",//设备私钥
    "DeviceStatus":"",//预留状态字段
    "ErrorMessage":""//错误信息
    }
     */
    public RegistDeviceResponse deviceRegist(String productKey,String deviceName){
        RegistDeviceRequest request = new RegistDeviceRequest();
        request.setProductKey(productKey);
        request.setDeviceName(deviceName);//可以设空，如果名称为空则由阿里云生成设备名称默认与设备id一致。设备名称在产品内唯一，如果已存在则返回已有设备
        DefaultAcsClient client=initSDK.getClient();
        RegistDeviceResponse resp = null;
        try {
            resp = client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if(resp!=null){
            System.out.println(resp.getSuccess());
            System.out.println(resp.getErrorMessage());
            System.out.println(resp.getDeviceSecret());
            System.out.println(resp.getDeviceId());
            System.out.println(resp.getDeviceName());

        }

        return resp;

    }

    /**
     * 批量申请设备
     * @param productKey
     * @param devices
     * @return
     *
    {
        "RequestId":"BB71E443-4447-4024-A000-EDE09922891E",
        "Success":true,
        "ApplyID":68
    }
     *
     */
    public ApplyDeviceWithNamesResponse applyBatchDevice(String productKey,List<String> devices){
        ApplyDeviceWithNamesRequest request = new ApplyDeviceWithNamesRequest();
//        List<String> devices = new ArrayList<String>();
//        devices.add("device_a");
//        devices.add("device_b");
//        devices.add("device_c");
//        devices.add("device01");
        request.setProductKey(productKey);
        request.setDeviceNames(devices);
        ApplyDeviceWithNamesResponse response =  null;
        try {
            DefaultAcsClient client=initSDK.getClient();
            response = client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if(response != null){
            System.out.println("Response requestId:"+response.getRequestId()+" isSuccess:"+response.getSuccess() +" Error:"+response.getErrorMessage());
        }
        return response;
    }

    /**
     * 查询批量设备的申请状态
     * @param applyId
     * @return
     *
     {
    "RequestId":"BB71E443-4447-4024-A000-EDE09922891E",
    "Success":true,
    "Finish":true
    }
     */
    public QueryApplyStatusResponse queryApplyBatchStatus(Long applyId){
        QueryApplyStatusRequest request = new QueryApplyStatusRequest();
        request.setApplyId(applyId);
        QueryApplyStatusResponse response =  null;
        try {
            DefaultAcsClient client=initSDK.getClient();
            response = client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if(response != null){
            System.out.println("Response requestId:"+response.getRequestId()+" isSuccess:"+response.getSuccess() +" Error:"+response.getErrorMessage());
        }
        return response;
    }

    /**
     * 查询批量生成的设备信息
     * @param applyId
     * @param currentPage
     * @param pageSize
     * @return
     *
     {
    "RequestId":"BB71E443-4447-4024-A000-EDE09922891E",
    "Success":true,
    Page:1,
    PageSize:10,
    PageCount:1,
    Total:4,
    ApplyDeviceList:{
    ApplyDeviceInfo:[
    {DeviceId:...,DeviceName:...,DeviceSecret:...},
    {DeviceId:...,DeviceName:...,DeviceSecret:...}
    ]
    }
    }
     */
    public QueryPageByApplyIdResponse queryPageByApplyId(Long applyId,int currentPage,int pageSize){
        QueryPageByApplyIdRequest request = new QueryPageByApplyIdRequest();
        request.setApplyId(applyId);
        request.setCurrentPage(currentPage);
        request.setPageSize(pageSize);
        QueryPageByApplyIdResponse response =  null;
        try {
            DefaultAcsClient client=initSDK.getClient();
            response = client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if(response != null){
            System.out.println("Response requestId:"+response.getRequestId()+" isSuccess:"+response.getSuccess() +" Error:"+response.getErrorMessage());
        }

        return response;
    }

    /**
     * 根据设备名称查询设备信息
     * @param productKey
     * @param deviceName
     * @return
     {
    RequestId:07C19236-7CFC-4BF9-99AD-EA6054092FDB,
    DeviceInfo:{
    DeviceId:...,
    DeviceName:...,
    ProductKey:...,
    DeviceSecret:...,
    GmtCreate:Thu, 17-Nov-2016 02:08:12 GMT,
    GmtModified:Thu, 17-Nov-2016 02:08:12 GMT
    },
    Success:true
    }
     */
    public QueryDeviceByNameResponse queryDeviceByName(String productKey,String deviceName){
        QueryDeviceByNameRequest request = new QueryDeviceByNameRequest();
        request.setProductKey(productKey);
        request.setDeviceName(deviceName);
        QueryDeviceByNameResponse response =  null;
        try {
            DefaultAcsClient client=initSDK.getClient();
            response = client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if(response != null){
            System.out.println("Response requestId:"+response.getRequestId()+" isSuccess:"+response.getSuccess() +" Error:"+response.getErrorMessage());

        }
        return response;
    }

    /**
     * 批量获取设备状态
     * @param productKey
     * @param devices
     * @return
    {
    DeviceStatusList:{
    DeviceStatus:[
    {DeviceId:..., Status:UNACTIVE, DeviceName:...},
    {DeviceId:..., Status:UNACTIVE, DeviceName:...}
    ]
    },
    RequestId:1A540BD7-176C-42D4-B3C0-A2C549DD00A3,
    Success:true
    }
     */
    public BatchGetDeviceStateResponse batchGetDeviceState(String productKey,List<String> devices){
        BatchGetDeviceStateRequest request = new BatchGetDeviceStateRequest();
        request.setProductKey(productKey);
//        List<String> devices = new ArrayList<String>();
//        devices.add("...");
//        devices.add("...");
//        devices.add("...");
//        devices.add("...");
        request.setDeviceNames(devices);
        BatchGetDeviceStateResponse response =  null;
        try {
            DefaultAcsClient client=initSDK.getClient();
            response = client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if(response != null){
            System.out.println("Response requestId:"+response.getRequestId()+" isSuccess:"+response.getSuccess() +" Error:"+response.getErrorMessage());
        }
        return response;
    }

    /**
     * 发布消息到topic
     * @param productKey
     * @param messageContent
     * @param topicFullName
     * @param qosFlag
     * @return
     * @throws Exception
     {
    "RequestId":"BB71E443-4447-4024-A000-EDE09922891E",
    "Success":true,
    }
     */
    public PubResponse publishMessageToTopic(String productKey,String messageContent,String topicFullName,int qosFlag) throws Exception {
        PubRequest pub = new PubRequest();
        pub.setProductKey(productKey);
        pub.setMessageContent(Base64.encodeBase64String(messageContent.getBytes()));//base64 String.
        pub.setTopicFullName(topicFullName);//消息发送给哪个topic中.
        if(qosFlag!=0||qosFlag!=1){
            throw new Exception("qos标志必须为0或者1！");
        }
        pub.setQos(qosFlag);//设置Qos为1，那么设备如果不在线，重新上线会收到离线消息，消息最多在Iot Hub中保存7天.
        DefaultAcsClient client=initSDK.getClient();
        PubResponse response = null;
        try {
            response = client.getAcsResponse(pub);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if(response!=null){
            System.out.println(response.getRequestId());//当次请求的ID
            System.out.println(response.getSuccess());//请求是否成功.
            System.out.println(response.getErrorMessage());//出错时的错误信息
        }


        return response;

    }

    /**
     * 发送消息给设备并得到设备响应
     * 推送数据给设备并得到响应，特别注意：该接口目前只适用CCP协议接入的设备，MQTT协议不支持
     * @param deviceName
     * @param productKey
     * @param timeout
     * @param rpcContent
     * @return
     {
    "Rpccode":"UNKONW",
    "Success":true,
    "ResponseContent":"bm90IGZvdW5kIHJvdXRlciByZWNvcmQu"
    }

    UNKNOW:系统异常
    SUCCESS:成功
    TIMEOUT:设备回执超时
    OFFLINE: 设备离线
    HALFCONN: 设备离线(设备连接断开但是断开时间未超过一个心跳周期)

     */
    public RevertRpcResponse revertRpc(String deviceName,Long productKey,int timeout,String rpcContent){
        RevertRpcRequest rpcRequest = new RevertRpcRequest();
        rpcRequest.setDeviceName(deviceName);//设备名称
        rpcRequest.setProductKey(productKey);//设备接入时候填写的productKey
        rpcRequest.setTimeOut(timeout); //超时时间，单位毫秒.如果超过这个时间设备没反应则返回"TIMEOUT"
        rpcRequest.setRpcContent(Base64.encodeBase64String(rpcContent.getBytes()));//推送给设备的数据.数据要求二进制数据做一次BASE64编码.(示例里面是"helloworld"编码后的值)
        DefaultAcsClient client=initSDK.getClient();
        RevertRpcResponse rpcResponse = null;
        try {
            rpcResponse = client.getAcsResponse(rpcRequest);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        //得到设备返回的数据信息. 注意:得到的数据是设备返回二进制数据然后再经过IOT平台base64转换之后的字符串.需要转换一下才能拿到原始的二进制数据.
        System.out.println(rpcResponse.getResponseContent());
        System.out.println(rpcResponse.getRpcCode());//对应的响应码( TIMEOUT/SUCCESS/OFFLINE/HALFCONN等)
        return rpcResponse;
    }

    /**
     * 发布广播消息
     * @param productKey
     * @param messageContent
     * @param topicFullName
     * @return
     *
     {
    "RequestId":"BB71E443-4447-4024-A000-EDE09922891E",
    "Success":true,
    }
     */
    public PubBroadcastResponse pubBroadcast(String productKey,String messageContent,String topicFullName){
        PubBroadcastRequest request = new PubBroadcastRequest();
        request.setProductKey(productKey);
        request.setMessageContent(Base64.encodeBase64String(messageContent.getBytes())); //Hello world base64 String
        request.setTopicFullName(topicFullName); //消息发送到的Topic
        DefaultAcsClient client=initSDK.getClient();
        PubBroadcastResponse response = null;
        try {
            response = client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if(response!=null){
            System.out.println(response.getRequestId()); //当前请求的ID
            System.out.println(response.getSuccess()); //请求是否成功
            System.out.println(response.getErrorMessage()); //出错时的错误信息
        }

        return response;

    }

    /**
     * 查询设备影子
     * @param productKey
     * @param deviceName
     * @return
     {
    "RequestId":"BB71E443-4447-4024-A000-EDE09922891E",
    "Success":true,
    DeviceShadow:{...}
    }
     */
    public GetDeviceShadowResponse getDeviceShadow(String productKey,String deviceName){
        GetDeviceShadowRequest  shadowRequest = new GetDeviceShadowRequest();
        shadowRequest.setProductKey(productKey);//填写productKey
        shadowRequest.setDeviceName(deviceName);//填写设备名称
        GetDeviceShadowResponse  shadowResponse=null;
        try {
            DefaultAcsClient client=initSDK.getClient();
            shadowResponse = client.getAcsResponse(shadowRequest);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if(shadowResponse!=null){
            System.out.println(shadowResponse.getSuccess());//判断返回值成功or失败
            System.out.println(shadowResponse.getShadowMessage());//获取设备影子JSON内容
        }
        return shadowResponse;
    }

    /**
     * 更新设备影子
     * @param attributeMap
     * @param productKey
     * @param deviceName
     * @return
     {
    "RequestId":"BB71E443-4447-4024-A000-EDE09922891E",
    "Success":true,
    }
     */
    public UpdateDeviceShadowResponse updateDeviceShadow(Map<String, Object> attributeMap,String productKey,String deviceName){
//        Map<String, Object> attributeMap = new LinkedHashMap<String, Object>(16);
//        attributeMap.put("window","open");
//        attributeMap.put("temperature", 25);
        UpdateDeviceShadowRequest shadowRequest = new UpdateDeviceShadowRequest();
        shadowRequest.setProductKey(productKey);//填写productKey
        shadowRequest.setDeviceName(deviceName);//添加设备名称
        shadowRequest.setShadowMessage(genUpdateShadowMsg(attributeMap));//生成更新影子的JSON格式数据
        UpdateDeviceShadowResponse shadowResponse=null;
        try {
            DefaultAcsClient client=initSDK.getClient();
            shadowResponse = client.getAcsResponse(shadowRequest);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if(shadowResponse!=null){
            System.out.println(shadowResponse.getSuccess());//判断是否更新影子是否成功
        }
        return shadowResponse;
    }
    public static long shadowVersion = 1;
    //构造更新设备影子json内容
    public static String genUpdateShadowMsg(Map<String, Object> attributeMap){
        Set<String> attSet = attributeMap.keySet();
        Map attMap = new LinkedHashMap();
        for (String attKey : attSet){
            attMap.put(attKey, attributeMap.get(attKey));
        }
        Map reportedMap = new LinkedHashMap();
        reportedMap.put("desired", attMap);
        Map shadowJsonMap = new LinkedHashMap();
        shadowJsonMap.put("method", "update");
        shadowJsonMap.put("state", reportedMap);
        shadowVersion ++;
        shadowJsonMap.put("version", shadowVersion);
        return JSON.toJSONString(shadowJsonMap);
    }




}
