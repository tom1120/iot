package com.kito.xfire;/**
 * Created by zhaoyipc on 2017/9/11.
 */

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import java.net.MalformedURLException;

/**
 * @author zhaoyi
 * @date 2017-09-2017/9/11-8:43
 */
public class OpenTheDoorClient {
    public static boolean openTheDoor(String ip,int port){
        try {
            System.out.println("================================开门========================");
//            URL url = new URL("http://localhost:8080/kito/services/FineReportSendService?wsdl");
//            Client client = new Client(url);
            JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
//            Client client=clientFactory.createClient("http://k6.kito.cn/services/FineReportSendService?wsdl");
//            Client client=clientFactory.createClient("http://iot.kito.cn:8080/kito/services/OpenTheDoorService?wsdl");
            Client client=clientFactory.createClient("http://k6.kito.cn/services/OpenTheDoorService?wsdl");

            Object[] results = new Object[1];
            results = client.invoke("openTheDoor",new Object[]{ip,port});
            System.out.println(results[0]);
            return (boolean) results[0];


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
       boolean b= openTheDoor("192.168.111.2",1);
        if(b){
            System.out.println("门禁已经打开!");
        }
    }

}
