package org.jeecgframework.test.cxf;/**
 * Created by zhaoyipc on 2017/7/11.
 */

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhaoyi
 * @date 2017-07-2017/7/11-11:12
 */
public class TestFineReport8 {
    private static Logger LOGGER = LoggerFactory.getLogger(Test.class);

    @Test
    public  void test(){

//        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        //不依赖服务器端接口来完成调用的，也就是不仅仅能调用Java的接口
        JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
        Client client = clientFactory.createClient("http://localhost:8080/kito/services/FineReportSendService?wsdl");
        Object[] result = new Object[0];
        try {
            result = client.invoke("executeFineReport8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result[0]);
    }
}
