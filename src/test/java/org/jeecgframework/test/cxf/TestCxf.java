package org.jeecgframework.test.cxf; /**
 * Created by zhaoyipc on 2017/7/6.
 */

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;


/**
 * @author zhaoyi
 * @date 2017-07-2017/7/6-9:43
 */
public class TestCxf {
    private static Logger LOGGER = LoggerFactory.getLogger(Test.class);

    @Test
    public  void test(){

        //不依赖服务器端接口来完成调用的，也就是不仅仅能调用Java的接口
//        JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
//        Client client = clientFactory.createClient("http://localhost:8088/jeewx/cxf/HelloWorldService?wsdl");
//        Object[] result = new Object[0];
//        try {
//            result = client.invoke("say", "ggg");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(result[0]);
    }
}
