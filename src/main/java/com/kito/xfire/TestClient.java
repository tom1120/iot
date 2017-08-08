package com.kito.xfire;/**
 * Created by zhaoyipc on 2017/6/14.
 */

//import org.codehaus.xfire.client.Client;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * xfire与k6系统的测试类，xfire调用方式，在cxf调用时会存在冲突问题，在测试类存在正常cxf调用xfire服务端的例子
 * @author zhaoyi
 * @date 2017-06-2017/6/14-14:31
 */
public class TestClient {
    public static void helloWorld(){
        try {
            System.out.println("================================helloWorld()========================");
//            URL url = new URL("http://localhost:8080/kito/services/XfireTestService?wsdl");
//            URL url = new URL("http://k6.kito.cn/services/XfireTestService?wsdl");
//            Client client = new Client(url);

            JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
            Client client=clientFactory.createClient("http://k6.kito.cn/services/XfireTestService?wsdl");
            Object[] results = new Object[1];
            Object[] results1 = new Object[1];
            results = client.invoke("mulity",new Object[]{3,4});
            results1 = client.invoke("add",new Object[]{3,4});
            client.invoke("print",new Object[]{"hello world,世界你好!"});
            System.out.println(results[0]);
            System.out.println(results1[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        helloWorld();
    }
}
