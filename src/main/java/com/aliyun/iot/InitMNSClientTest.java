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
import org.jeecgframework.core.util.PropertiesUtil;
import org.jeecgframework.core.util.UUIDGenerator;
import org.junit.internal.runners.statements.RunAfters;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.*;
import java.util.Properties;
import java.util.UUID;

/**
 * mns客户端初始化，并执行相关消息的订阅授权
 * @author zhaoyi
 * @date 2017-07-2017/7/18-15:48
 */
public class InitMNSClientTest {
    private MNSClient mnsClient=null;


    //访问授权
    String accessKey;
    String accessSecret;

    //华东2节点队列相关参数
    //公网Endpoint
    String accountPublicEndpoint;
    //私网Endpoint
    String accountPrivateEndpoint;
    //VpcEndpoint
    String accountVpcEndpoint;

    String msgQueue;

    public void initMnsClient(){
        String filePath= "iotconfig.properties";
        //这个工具类直接从classpath路径下找
        PropertiesUtil propertiesUtil=new PropertiesUtil(filePath);
        Properties properties= propertiesUtil.getProperties();

        this.accessKey=properties.getProperty("accessKey");
        this.accessSecret=properties.getProperty("accessSecret");
        this.accountPublicEndpoint=properties.getProperty("accountPublicEndpoint");
        this.accountPrivateEndpoint=properties.getProperty("accountPrivateEndpoint");
        this.accountVpcEndpoint=properties.getProperty("accountVpcEndpoint");
        this.msgQueue=properties.getProperty("msgQueue");


        CloudAccount account=new CloudAccount(accessKey,accessSecret,accountPublicEndpoint);

        this.mnsClient=account.getMNSClient();

    }

    public InitMNSClientTest() {
//        initMnsClient();
    }

    public MNSClient getMnsClient() {
        return mnsClient;
    }

    public void testMNSQueue(){

        CloudQueue queue = mnsClient.getQueueRef(msgQueue); //参数请输入IoT自动创建的队列名称，从此队列中获取消息
        msgReceiveHandler(queue);

    }


    private void msgReceiveHandler(CloudQueue queue) {
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
                JSONObject json= JSON.parseObject(msg);
                String messagetype=json.getString("messagetype");

                String payload= json.getString("payload");
                payload=new String(Base64.decodeBase64(payload));
                System.out.println("payload = " + payload);

                JSONObject payloadJson=JSON.parseObject(payload);
                String clientVersion=payloadJson.getString("version");
                //设备感知status/设备上报upload
                if(messagetype.equals("upload")){
                    if(clientVersion!=null){
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

                }else if(messagetype.equals("status")){//设备状态上报
                    Runnable runnable=new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Class.forName("com.mysql.jdbc.Driver");
                                String url="jdbc:mysql://localhost:3306/jeecg?useUnicode=true&characterEncoding=UTF-8";
                                String user="root";
                                String password="kitoiotserver42899975";
                                Connection connection= DriverManager.getConnection(url,user,password);

                                String sql="INSERT INTO device_status_news(id,product_key,device_key,device_status,status_return_time,status_lastcheck_time,note) VALUES(?,?,?,?,?,?,?) ";
                                PreparedStatement preparedStatement=connection.prepareStatement(sql);

                                preparedStatement.setString(1, UUIDGenerator.generate());
                                preparedStatement.setString(2,payloadJson.getString("productKey"));
                                preparedStatement.setString(3,payloadJson.getString("deviceName"));
                                preparedStatement.setString(4,payloadJson.getString("status"));
                                preparedStatement.setString(5,payloadJson.getString("time"));
                                preparedStatement.setString(6,payloadJson.getString("lastTime"));

                                preparedStatement.setString(7,"jdbc插入测试");
                                preparedStatement.executeUpdate();


                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    new Thread(runnable).start();

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
        initMNSClient.initMnsClient();
        initMNSClient.testMNSQueue();
    }

}
