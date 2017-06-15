package com.kito.xfire;/**
 * Created by zhaoyipc on 2017/6/14.
 */

import org.codehaus.xfire.client.Client;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * xfire与k6系统的测试类
 * @author zhaoyi
 * @date 2017-06-2017/6/14-14:31
 */
public class TestClient {
    public static void helloWorld(){
        try {
            System.out.println("================================helloWorld()========================");
            URL url = new URL("http://localhost:8080/kito/services/XfireTestService?wsdl");
            Client client = new Client(url);
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
