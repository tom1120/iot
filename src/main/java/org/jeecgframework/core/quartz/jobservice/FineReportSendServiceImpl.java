package org.jeecgframework.core.quartz.jobservice;/**
 * Created by zhaoyipc on 2017/6/16.
 */

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.quartz.*;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhaoyi
 * @date 2017-06-2017/6/16-16:03
 */
@Service("fineReportSendServiceImpl")
public class FineReportSendServiceImpl implements FineReportSendService,Serializable {

    private boolean execute(String url, String method){
        boolean b=false;
//        原xfire调用方式
/*        try {
            System.out.println("================================execute()========================");
            URL urlService = new URL(url);
            Client client = new Client(urlService);
            Object[] results = new Object[1];
            results = client.invoke(method,null);
            b=(boolean)results[0];
            System.out.println(results[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        JaxWsDynamicClientFactory clientFactory=JaxWsDynamicClientFactory.newInstance();
        Client client=clientFactory.createClient(url);
        Object[] result = new Object[0];
        try {
            //result = client.invoke(method,null);//空参数不能传
            result = client.invoke(method);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result[0]);
        b=(boolean)result[0];
        return b;

    }

    @Override
    public boolean executeSaleImgSend() {
        String url="http://k6.kito.cn/services/FineReportSendService?wsdl";
        String method="executeSaleImgSend";
        return execute(url,method);

    }

    @Override
    public boolean executeNewProductSend() {
        String url="http://k6.kito.cn/services/FineReportSendService?wsdl";
        String method="executeNewProductSend";
        return execute(url,method);
    }

    @Override
    public boolean executeFactoryProductInSend() {
        String url="http://k6.kito.cn/services/FineReportSendService?wsdl";
        String method="executeFactoryProductInSend";
        return execute(url,method);
    }

    @Override
    public boolean executeProductRealtimeStoreSend() {
        String url="http://k6.kito.cn/services/FineReportSendService?wsdl";
        String method="executeProductRealtimeStoreSend";
        return execute(url,method);
    }

    @Override
    public boolean executeChannelRealtimeStoreAgeSend() {
        String url="http://k6.kito.cn/services/FineReportSendService?wsdl";
        String method="executeChannelRealtimeStoreAgeSend";
        return execute(url,method);
    }

    @Override
    public boolean executeForeignTradeLightSend() {
        String url="http://k6.kito.cn/services/FineReportSendService?wsdl";
        String method="executeForeignTradeLightSend";
        return execute(url,method);
    }
}
