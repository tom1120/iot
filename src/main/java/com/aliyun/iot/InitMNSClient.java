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

/**
 * mns客户端初始化，并执行相关消息的订阅授权
 * @author zhaoyi
 * @date 2017-07-2017/7/18-15:48
 */
public class InitMNSClient {
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

    public InitMNSClient() {
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
                System.out.println("PopMessage raw Body: "
                        + popMsg.getMessageBodyAsRawString()); //获取原始消息
                String msg=popMsg.getMessageBodyAsString();
                System.out.println("PopMessage decode Body:"
                        + msg);
                JSONObject json=JSON.parseObject(msg);
                String payload= (String) json.get("payload");
                payload=new String(Base64.decodeBase64(payload));
                System.out.println("payload = " + payload);
                queue.deleteMessage(popMsg.getReceiptHandle()); //从队列中删除消息
            } else {
                System.out.println("Continuing");
            }
        }


    }


    public static void main(String[] args) {
        InitMNSClient initMNSClient=new InitMNSClient();
        initMNSClient.testMNSQueue();
    }


}
