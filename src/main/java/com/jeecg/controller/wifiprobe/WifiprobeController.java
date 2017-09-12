package com.jeecg.controller.wifiprobe;/**
 * Created by zhaoyipc on 2017/9/7.
 */

import com.kito.xfire.OpenTheDoorClient;
import org.apache.commons.collections.map.HashedMap;
import org.jeecgframework.core.common.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhaoyi
 * @date 2017-09-2017/9/7-8:39
 */
@Controller
@RequestMapping("/")
public class WifiprobeController extends BaseController {
    private static final Logger logger= LoggerFactory.getLogger(WifiprobeController.class);

/*    private static List<Integer> rssiList = new ArrayList<>();
    private static List<Integer> rssiListA = new ArrayList<>();
    private static List<Integer> rssiListB = new ArrayList<>();*/
    private static List<String> list = new ArrayList<>();
    private static Map<String, List<Integer>> map = new HashedMap();

    private static int n = 3;//缓存数


    static {

        list.add("849fb537b0b1");//本人
        list.add("24240ec122cc");//龙总
        list.add("f4cb523eeab3");//阿其
        list.add("8c0d76dec53a");//阿飞
        list.add("64b0a6252c7f");//阿安
        list.add("7081eba5783d");//张梁

        for(int i=0;i<list.size();i++){
            List<Integer> list1=new ArrayList<>();
            map.put(list.get(i),list1);
        }
    }


    @RequestMapping(value = "data/upload3", method = RequestMethod.POST)
    @ResponseBody
    public String dataupload3(HttpServletRequest request) {

//        DataInputStream in=null;
        int len = request.getContentLength();
        if (len < 0) {
//            System.out.println("未检测到上报数据!");
            logger.debug("未检测到上报数据!");
            return "err";
        }

        logger.debug("===============进入上报方法============");


        logger.debug("content = " + request.getHeader("Content-Type"));
        Map map = showParams(request);

        String sta = (String) map.get("sta");
        String r = (String) map.get("r");
        String shop = (String) map.get("shop");
        String data = (String) map.get("data");
        String id = (String) map.get("id");
        String type = (String) map.get("type");
        String token = (String) map.get("token");


        if ((len > 1600) || (sta == null))
            return "ok";

        if ((sta == null) || (type == null) || (data == null))
            return "ok";

        long timelong = System.currentTimeMillis();
        Date d = new Date(timelong);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSS");
        String dstring = sdf.format(d);
        logger.debug("timelong = " + timelong);
        logger.debug("dstring = " + dstring);
        if (type.equals("ap")) {
            //处理ap扫描结果
            return "ok";
        }

        if (type.equals("configa")) {
            //处理wifi上报配置信息
            return "ok";
        }

        if (type.equals("flash")) {//默认不启动本地存储模式，如果需要，则要对探针进行高级配置
            //处理flash存储的信息，未成功上报的那部分数据
            flashDataConvert(data);
            return "ok";
        }

        if (type.equals("unixtime")) {//探针联网可更新探针的时间，此处还需要专门实现

            return "ok";
        }

        if (type.equals("check")) {
            //处理wifib上报心跳信息
            return "ok";
        }
        boolean probeature = false;
        if (type.equals("probea")) {
            probeature = true;
        }
        boolean probebture = false;
        if (type.equals("probeb")) {
            probebture = true;
        }
        if (probeature || probebture) {
            //处理上报探测到的mac地址信息
            dataConvert(data);
            return "ok";
        }
        if (type.equals("tag")) {
            //处理上报探测到的商品标签信息
            return "ok";
        }
        if (type.equals("register")) {
            //处理上报的mac与个人信息映射
            return "ok";
        }


        return "ok";
    }


    private Map showParams(HttpServletRequest request) {
        Map map = new HashMap();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();

            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    map.put(paramName, paramValue);
                }
            }
        }

        Set<Map.Entry<String, String>> set = map.entrySet();
        logger.debug("------------------------------");
        for (Map.Entry entry : set) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        logger.debug("------------------------------");
        return map;
    }


    private Double distance(String rssi) {
//        d=10^((ABS(RSSI)-A)/(10*n))
//        通过实验，A值的最佳范围为45—49，n值最佳范围为3.25—4.5，N在15---25。
        Double d;
        int A = 45;
        Double n = 3.25;
//        d=10^((Integer.valueOf(rssi)-A)/(10*n));

        d = Math.pow(10, (Integer.valueOf(rssi) - A) / (10 * n));

        return d;
    }


    public void dataConvert(String data) {
        String delimiter = "\1";
        int startp = data.indexOf(delimiter);
        if (startp != -1) {
            int endp = data.lastIndexOf(delimiter);
            if (((endp - startp) - 1) > 12) {
                String datatrimed = data.substring(startp + 1, endp);
                String[] datasplits = datatrimed.split(delimiter);
                for (int i = 0; i < datasplits.length; i++) {
                    if (datasplits[i].length() > 12) {
                        String mac = datasplits[i].substring(0, 12);
                        logger.debug("mac = " + mac);
                        byte[] datasplitbytes = datasplits[i].getBytes();

                        for (int j = 12; j < datasplitbytes.length; j++) {
                            int rssi = datasplitbytes[j];
                            if (rssi > 9 && rssi < 100) {
                                //自己处理数据
                                logger.debug("rssi = " + rssi);


                                for (String s : list) {
                                    if (mac.equals(s)) {

                                        List<Integer> rssiList=map.get(mac);
                                        rssiList.add(rssi);
                                        logger.debug("rssiList大小："+rssiList.size());
                                        if (rssiList.size() == n) {
                                            int rssiSum = 0;

                                            for (int x = 0; x < n; x++) {
                                                logger.debug("rssiList.get(x) = " + rssiList.get(x));
                                                rssiSum += rssiList.get(x);
                                            }
                                            int rssiAvg = rssiSum / n;
                                            logger.debug("rssiAvg = " + rssiAvg);
                                            if (rssiAvg < 65) {
                                                synchronized (this) {
                                                    boolean b = OpenTheDoorClient.openTheDoor("192.168.111.2", 1);

                                                    if (b) {
                                                        logger.debug("门禁已经打开!");
                                                    }
                                                }
                                            }

                                            rssiList.clear();
                                        }
                                    }
                                }


//多个人
/*                                if (mac.equals("849fb537b0b1")) {
                                    rssiList.add(rssi);
                                    if (rssiList.size() == n) {
                                        int rssiSum = 0;

                                        for (int x = 0; x < n; x++) {
                                            rssiSum += rssiList.get(x);
                                        }
                                        int rssiAvg = rssiSum / n;
                                        System.out.println("rssiAvg = " + rssiAvg);
                                        if (rssiAvg < 58) {
                                            synchronized (this) {
                                                boolean b = OpenTheDoorClient.openTheDoor("192.168.111.2", 1);

                                                if (b) {
                                                    System.out.println("门禁已经打开!");
                                                }
                                            }
                                        }

                                        rssiList.clear();


                                    }


                                } else if (mac.equals("24240ec122cc")) {
                                    rssiListA.add(rssi);
                                    if (rssiListA.size() == n) {
                                        int rssiSum = 0;

                                        for (int x = 0; x < n; x++) {
                                            rssiSum += rssiListA.get(x);
                                        }
                                        int rssiAvg = rssiSum / n;
                                        System.out.println("rssiAvg = " + rssiAvg);
                                        if (rssiAvg < 58) {
                                            synchronized (this) {
                                                boolean b = OpenTheDoorClient.openTheDoor("192.168.111.2", 1);

                                                if (b) {
                                                    System.out.println("门禁已经打开!");
                                                }
                                            }
                                        }

                                        rssiListA.clear();
                                    }
                                }else if (mac.equals("f4cb523eeab3")) {
                                        rssiListB.add(rssi);
                                        if (rssiListB.size() == n) {
                                            int rssiSum = 0;

                                            for (int x = 0; x < n; x++) {
                                                rssiSum += rssiListB.get(x);
                                            }
                                            int rssiAvg = rssiSum / n;
                                            System.out.println("rssiAvg = " + rssiAvg);
                                            if (rssiAvg < 58) {
                                                synchronized (this) {
                                                    boolean b = OpenTheDoorClient.openTheDoor("192.168.111.2", 1);

                                                    if (b) {
                                                        System.out.println("门禁已经打开!");
                                                    }
                                                }
                                            }
                                            rssiListB.clear();
                                        }

                                    }*/

                                //单个人
/*                                if(mac.equals("849fb537b0b1")&&rssi<65){
                                    synchronized (this){
                                        boolean b= OpenTheDoorClient.openTheDoor("192.168.111.2",1);
                                        if(b){
                                            System.out.println("门禁已经打开!");
                                        }
                                    }

                                }*/


                            }
                        }
                    }
                }
            }
        }
    }


    private void flashDataConvert(String data) {
        String delimiter = "\1";
        int startp = data.indexOf(delimiter);
        if (startp != -1) {
            int endp = data.lastIndexOf(delimiter);
            if ((endp - startp) - 1 > 12) {
                String datatrimed = data.substring(startp + 1, endp);
                String[] datasplits = datatrimed.split(datatrimed);
                for (int i = 0; i < datasplits.length; i++) {
                    if (datasplits[i].length() > 12) {
                        String mac = datasplits[i].substring(0, 12);
                        logger.debug("mac = " + mac);
                        String flashtime = datasplits[i].substring(13, 24);
                        logger.debug("flashtime = " + flashtime);

                    }

                }

            }
        }


    }


}
