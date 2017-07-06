package org.jeecgframework.test.xfire;/**
 * Created by zhaoyipc on 2017/6/14.
 */

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * xfire与k6系统的测试类
 * @author zhaoyi
 * @date 2017-06-2017/6/14-14:31
 */
public class TestClient {
    @Test
    public void helloWorld() {
        try {
            System.out.println("================================helloWorld()========================");
            JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
            Client client = clientFactory.createClient("http://k6.kito.cn/services/XfireTestService?wsdl");
            Object[] result = new Object[0];
            try {
                result = client.invoke("add", new Object[]{1, 2});
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(result[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
