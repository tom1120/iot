package com.aliyun.iot;/**
 * Created by zhaoyipc on 2017/7/18.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.Message;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * mns客户端初始化，并执行相关消息的订阅授权
 * @author zhaoyi
 * @date 2017-07-2017/7/18-15:48
 */
public class InitMNSClientTest {
    private MNSClient mnsClient=null;

    public void initMnsClient(){
        String accessKey = "LTAIFlCD8jBQXlqH";
        String accessSecret = "TPunQG9dHa81VlI63JuMPyK8X8Hgs3";
        //公网Endpoint
        String accountPublicEndpoint="http://1038576842133397.mns.cn-shanghai.aliyuncs.com/";
        //私网Endpoint
        String accountPrivateEndpoint="http://1038576842133397.mns.cn-shanghai-internal.aliyuncs.com/";
        //VpcEndpoint
        String accountVpcEndpoint="http://1038576842133397.mns.cn-shanghai-internal-vpc.aliyuncs.com";

        CloudAccount account=new CloudAccount(accessKey,accessSecret,accountPublicEndpoint);

        this.mnsClient=account.getMNSClient();



    }

    public InitMNSClientTest() {
        initMnsClient();
    }

    public MNSClient getMnsClient() {
        return mnsClient;
    }

    public void testMNSQueue(){

        CloudQueue queue = mnsClient.getQueueRef("aliyun-iot-7Pi3WAFJhC6"); //参数请输入IoT自动创建的队列名称
        while (true) {
            // 获取消息
            Message popMsg = queue.popMessage(10); //长轮询等待时间为10秒
            if (popMsg != null) {
                //获取原始消息
//                System.out.println("PopMessage raw Body: "
//                        + popMsg.getMessageBodyAsRawString());
                String msg=popMsg.getMessageBodyAsString();
                System.out.println("PopMessage decode Body:"
                        + msg);
                JSONObject json=JSON.parseObject(msg);
                String messagetype=json.getString("messagetype");

                String payload= json.getString("payload");
                payload=new String(Base64.decodeBase64(payload));
                System.out.println("payload = " + payload);

                JSONObject payloadJson=JSON.parseObject(payload);
                String clientVersion=payloadJson.getString("version");
                //设备感知status/设备上报upload
                if(messagetype.equals("upload")&&clientVersion!=null){
                    //设备端与云端进行版本比较
                    String cloudVersion="1.0.0-SNAPSHOT";
                    if(clientVersion.equals(cloudVersion)){
                        System.out.println("版本一致不用升级！");
                    }else {
                        cloudVersion=cloudVersion.replaceAll("[^0-9|\\.]","");
                        clientVersion=clientVersion.replaceAll("[^0-9|\\.]","");
                        String[] clientVersionArr=clientVersion.split("\\.");
                        String[] cloudVersionArr=cloudVersion.split("\\.");
                        if(twoArrayBigOrSmall(clientVersionArr,cloudVersionArr)){
                            //发送更新指令到客户端
                            InitSDK initSDK=new InitSDK();//到时候改为从容器中注入
                            ProductApi productApi=new ProductApi();
                            productApi.setInitSDK(initSDK);
                            //String productKey,String messageContent,String topicFullName,int qosFlag
                            String messageContent="{\"isUpdateVersion\":true}";
                            try {
                                productApi.publishMessageToTopic(payloadJson.getString("productKey"),messageContent,json.getString("topic"),1);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        }
                    }

                }


                queue.deleteMessage(popMsg.getReceiptHandle()); //从队列中删除消息
            } else {
                System.out.println("Continuing");
            }
        }


    }


    /**
     * 只有当客户端版本小于云端版本时此方法返回值为true
     * @param clientVersion
     * @param cloundVersion
     * @return
     */
    private boolean twoArrayBigOrSmall(String[] clientVersion,String[] cloundVersion){
        boolean b=false;
        if(clientVersion.length!=3||cloundVersion.length!=3){
            try {
                throw new Exception("客户端版本与云端版本只能存在三段版本！");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        for(int i=0;i<clientVersion.length;i++){
            if(Integer.valueOf(clientVersion[i])<Integer.valueOf(cloundVersion[i])){
                return true;
            }
        }


        return b;

    }


    public static void main(String[] args) {
        InitMNSClientTest initMNSClient=new InitMNSClientTest();
        initMNSClient.testMNSQueue();
    }

}
