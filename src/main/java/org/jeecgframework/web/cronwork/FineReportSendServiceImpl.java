package org.jeecgframework.web.cronwork;/**
 * Created by zhaoyipc on 2017/6/16.
 */

import org.codehaus.xfire.client.Client;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author zhaoyi
 * @date 2017-06-2017/6/16-16:03
 */
@Service("fineReportSendService")
public class FineReportSendServiceImpl implements FineReportSendService {
    @Override
    public boolean work() {
        boolean b=false;
        try {
            System.out.println("================================helloWorld()========================");
            URL url = new URL("http://localhost:8080/kito/services/FineReportSendService?wsdl");
            Client client = new Client(url);
            Object[] results = new Object[1];
            results = client.invoke("executeSaleImgSend",null);
            b=(boolean)results[0];
            System.out.println(results[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return b;
    }
}
