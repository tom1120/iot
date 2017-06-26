package org.jeecgframework.web.cronwork;/**
 * Created by zhaoyipc on 2017/6/16.
 */

import org.codehaus.xfire.client.Client;
import org.quartz.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhaoyi
 * @date 2017-06-2017/6/16-16:03
 */

public class FineReportSendServiceImpl implements FineReportSendService,Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        System.err.println("Instance " + key);
        System.err.println("dataMap " + dataMap.toString());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println("在" + sdf.format(new Date()) + "执行任务");
    }

    private boolean execute(String url, String method){
        boolean b=false;
        try {
            System.out.println("================================execute()========================");
//            URL url = new URL("http://localhost:8080/kito/services/FineReportSendService?wsdl");
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
        }

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
