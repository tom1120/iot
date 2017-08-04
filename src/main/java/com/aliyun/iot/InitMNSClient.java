package com.aliyun.iot;/**
 * Created by zhaoyipc on 2017/7/18.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.Message;
import com.jeecg.entity.aliyuniot.DeviceStatusNews;
import com.jeecg.entity.aliyuniot.IotConfig;
import com.jeecg.service.aliyuniot.DeviceStatusNewsServiceI;
import org.apache.commons.codec.binary.Base64;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Properties;

/**
 * mns客户端初始化，并执行相关消息的订阅授权
 * @author zhaoyi
 * @date 2017-07-2017/7/18-15:48
 */
public class InitMNSClient implements ServletContextListener {
    private MNSClient mnsClient=null;
    //Listener不能@Autowired直接注入服务，必须在类层面使用，否则变量共享不了
    private static DeviceStatusNewsServiceI deviceStatusNewsService;

    private static IotConfig iotConfig;

    private static InitSDK initSDK;

    private static ProductApi productApi;

    private static String accessKey;
    private static String accessSecret;
    //公网Endpoint
    private static String accountPublicEndpoint;
    //私网Endpoint
    private static String accountPrivateEndpoint;
    //VpcEndpoint
    private static String accountVpcEndpoint;

    private static String msgQueue;


    private void initMnsClient(){


        CloudAccount account=new CloudAccount(accessKey,accessSecret,accountPublicEndpoint);

        this.mnsClient=account.getMNSClient();



    }
    //先进入构造方法
    public InitMNSClient() {
//        initMnsClient();
    }

    public MNSClient getMnsClient() {
        return mnsClient;
    }

    public void testMNSQueue(){

        CloudQueue queue = mnsClient.getQueueRef(msgQueue); //参数请输入IoT自动创建的队列名称

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
//                                InitSDK initSDK=new InitSDK();//到时候改为从容器中注入
//                                ProductApi productApi=new ProductApi();
//                                productApi.setInitSDK(initSDK);


                                initSDK.initsdk();

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
                            DeviceStatusNews deviceStatusNews=new DeviceStatusNews();
                            deviceStatusNews.setProductKey(payloadJson.getString("productKey"));
                            deviceStatusNews.setDeviceKey(payloadJson.getString("deviceName"));
                            deviceStatusNews.setDeviceStatus(payloadJson.getString("status"));
                            deviceStatusNews.setStatusReturnTime(payloadJson.getString("time"));
                            deviceStatusNews.setStatusLastcheckTime(payloadJson.getString("lastTime"));
                            deviceStatusNewsService.save(deviceStatusNews);
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
    private static boolean twoArrayBigOrSmall(String[] clientVersion, String[] cloundVersion){
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
        InitMNSClient initMNSClient=new InitMNSClient();
        initMNSClient.testMNSQueue();
    }


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext=sce.getServletContext();
        servletContext.log("iot-mns监听服务正在启动..........！");
        WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        deviceStatusNewsService= (DeviceStatusNewsServiceI) springContext.getBean("deviceStatusNewsService");
        iotConfig= (IotConfig) springContext.getBean("iotConfig");

        Properties properties=iotConfig.getMergedProps();
        this.accessKey=properties.getProperty("accessKey");
        this.accessSecret=properties.getProperty("accessSecret");
        this.accountPublicEndpoint=properties.getProperty("accountPublicEndpoint");
        this.accountPrivateEndpoint=properties.getProperty("accountPrivateEndpoint");
        this.accountVpcEndpoint=properties.getProperty("accountVpcEndpoint");
        this.msgQueue=properties.getProperty("msgQueue");


        initMnsClient();

        DeviceStatusNews deviceStatusNews=new DeviceStatusNews();
        deviceStatusNews.setProductKey("testproduct");
        deviceStatusNews.setDeviceKey("testdevice");
        deviceStatusNews.setDeviceStatus("offline");
        deviceStatusNews.setStatusReturnTime("time");
        deviceStatusNews.setStatusLastcheckTime("lasttime");
        deviceStatusNewsService.save(deviceStatusNews);



        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    testMNSQueue();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().log("iot-mns监听服务正在关闭...........!");

    }
}
