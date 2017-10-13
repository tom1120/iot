package com.jeecg.controller.wifiprobe;/**
 * Created by zhaoyipc on 2017/9/7.
 */

import com.aliyun.instruction.entity.Instruction;
import com.aliyun.instruction.entity.InstructionMsgBody;
import com.aliyun.instruction.entity.InstructionType;
import com.aliyun.instruction.entity.MsgType;
import com.aliyun.instruction.util.InstructionUtils;
import com.aliyun.iot.InitSDK;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jeecg.entity.wifiprobe.WifiprobeSysParamEntity;
import com.jeecg.entity.wifiprobe.WifiprobeUploadInfoEntity;
import com.jeecg.service.wifiprobe.WifiprobeSysParamServiceI;
import com.jeecg.service.wifiprobe.WifiprobeUploadInfoServiceI;
import com.kito.k6.service.WifiBindK6PersonService;
import com.kito.util.dwr.ServerPushMessage;
import com.kito.xfire.OpenTheDoorClient;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.validator.Msg;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 目前一个门禁一个人一个缓存开门，如在此上报类实现多个门禁开门配置的话，那缓存就得更大些。
 *
 * @author zhaoyi
 * @date 2017-09-2017/9/7-8:39
 */
@Controller
@RequestMapping("/")
public class WifiprobeController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(WifiprobeController.class);

    /*    private static List<Integer> rssiList = new ArrayList<>();
        private static List<Integer> rssiListA = new ArrayList<>();
        private static List<Integer> rssiListB = new ArrayList<>();*/
    private static List<String> list = new ArrayList<String>();
    private static Map<String, List<Integer>> map = new HashedMap();

    private static int n = 2;//缓存数

    private static int rssidefine = 57;//强度值

    private static int rssiMin = 57;//最小临界值

    private static long afterOpenClientDelayTime=8000;//打开客户端后延时

    private static long beforeCloseClientDelayTime=30000;//关闭客户端前延时





    /*
        static {

            list.add("849fb537b0b1");//本人
            list.add("24240ec122cc");//龙总
            list.add("f4cb523eeab3");//阿其
            list.add("8c0d76dec53a");//阿飞
            list.add("64b0a6252c7f");//阿安
            list.add("7081eba5783d");//张梁

            for(int i=0;i<list.size();i++){
                List<Integer> list1=new ArrayList<Integer>();
                map.put(list.get(i),list1);
            }
        }
    */
    @Autowired
    WifiBindK6PersonService wifiBindK6PersonService;

    @Autowired
    WifiprobeUploadInfoServiceI wifiprobeUploadInfoService;

    @Autowired
    InitSDK initSDK;

    @Autowired
    WifiprobeSysParamServiceI wifiprobeSysParamService;

    public WifiprobeController() {

    }


    /**
     * 初始化人员方法，只能初始化一次
     *
     */
    private void initList() {

        List<WifiprobeSysParamEntity> wifiprobeSysParamEntities=wifiprobeSysParamService.findByQueryString("from WifiprobeSysParamEntity where isDefault=1");

        if(wifiprobeSysParamEntities.size()>0){
            WifiprobeSysParamEntity wifiprobeSysParamEntity=wifiprobeSysParamEntities.get(0);
            this.rssidefine=wifiprobeSysParamEntity.getRssi();
            this.rssiMin=wifiprobeSysParamEntity.getRssiMin();
            this.n=wifiprobeSysParamEntity.getCacheNumber();
            this.afterOpenClientDelayTime=wifiprobeSysParamEntity.getAfterOpenClientDelayTime();
            this.beforeCloseClientDelayTime=wifiprobeSysParamEntity.getBeforeCloseClientDelayTime();
        }


        if (list.size() == 0) {
            list = wifiBindK6PersonService.getWifiBindListPerson();
        }
        for (int i = 0; i < list.size(); i++) {
            List<Integer> list1 = new ArrayList<Integer>();
            map.put(list.get(i), list1);
        }
    }


    /**
     * 刷新缓存方法
     *
     */
    private void initList(WifiprobeSysParamEntity wifiprobeSysParamEntity) {

        this.rssidefine=wifiprobeSysParamEntity.getRssi();
        this.rssiMin=wifiprobeSysParamEntity.getRssiMin();
        this.n=wifiprobeSysParamEntity.getCacheNumber();
        this.afterOpenClientDelayTime=wifiprobeSysParamEntity.getAfterOpenClientDelayTime();
        this.beforeCloseClientDelayTime=wifiprobeSysParamEntity.getBeforeCloseClientDelayTime();

        if (list.size() == 0) {
            list = wifiBindK6PersonService.getWifiBindListPerson();
        }
        for (int i = 0; i < list.size(); i++) {
            List<Integer> list1 = new ArrayList<Integer>();
            map.put(list.get(i), list1);
        }
    }

    /**
     * 刷新缓存方法
     */
    public void refreshCache(WifiprobeSysParamEntity wifiprobeSysParamEntity) {
        initList(wifiprobeSysParamEntity);
    }



    @RequestMapping(value = "data/upload3", method = RequestMethod.POST)
    @ResponseBody
    public String dataupload3(HttpServletRequest request) {
        if (map.size() == 0) {
            initList();
        }

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
//            logger.debug("data:" + data);
            dataConvert(data, sta);
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
//        logger.debug("------------------------------");
        for (Map.Entry entry : set) {
//            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
//        logger.debug("------------------------------");
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


    public void dataConvert(String data, String sta) {
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
//                        logger.debug("mac = " + mac);
                        byte[] datasplitbytes = datasplits[i].getBytes();

                        for (int j = 12; j < datasplitbytes.length; j++) {
                            int rssi = datasplitbytes[j];
                            if (rssi > 9 && rssi < 100) {
                                //自己处理数据
//                                logger.debug("mac = " + mac);
//                                logger.debug("rssi = " + rssi);
                                Runnable runnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        long timelong = System.currentTimeMillis();
                                        Date d = new Date(timelong);
                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
                                        String dstring = sdf.format(d);

                                        WifiprobeUploadInfoEntity wifiprobeUploadInfoEntity = new WifiprobeUploadInfoEntity();
                                        wifiprobeUploadInfoEntity.setProbeInfo(sta);
                                        wifiprobeUploadInfoEntity.setMac(mac);
                                        wifiprobeUploadInfoEntity.setRssi(rssi);
                                        wifiprobeUploadInfoEntity.setUploadTime(dstring);
                                        wifiprobeUploadInfoEntity.setNote("测试");
                                        wifiprobeUploadInfoService.save(wifiprobeUploadInfoEntity);
                                    }
                                };

                                new Thread(runnable).start();

                                int n_min = 0;//计算超出最小临界值次数的计数器

                                for (String s : list) {//循环判断开门mac地址白名单
                                    String[] strings = s.split("\\$");
                                    if (mac.equals(strings[0])) {//在mac地址白名单中
                                        logger.debug("当前人员：" + s);
                                        logger.debug("mac = " + mac);
                                        logger.debug("rssi = " + rssi);
//                                        List<Integer> rssiList=map.get(mac);
                                        List<Integer> rssiList = new ArrayList<Integer>();
                                        rssiList = map.get(s);

                                        rssiList.add(rssi);
                                        logger.debug("rssiList大小：" + rssiList.size());

                                        if (rssi < rssiMin){
                                            n_min++;
                                        }

                                        if (rssiList.size() == n) {
                                            if ( n_min > 0 ) {
                                                int rssiSum = 0;

                                                for (int x = 0; x < n; x++) {
                                                    logger.debug("rssiList.get(x) = " + rssiList.get(x));
                                                    rssiSum += rssiList.get(x);
                                                }

                                                int rssiAvg = rssiSum / n;

                                                logger.debug("rssiAvg = " + rssiAvg);

                                                if (rssiAvg < rssidefine) {
                                                    rssiList.clear();//成功开门则清空缓存
                                                    n_min = 0;//成功开门则计数器归零
                                                    synchronized (this) {//防止上报过快造成数据出现超出缓存情况
                                                        boolean b = OpenTheDoorClient.openTheDoor("192.168.111.2", 1);
                                                        if (b) {
                                                            logger.debug(strings[1] + "门禁已经打开!");
                                                            //等到指令系统完善后再正式启用，正式部署先不执行这段代码
                                                            Runnable opendoor = new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    //1、发送指令给设备打开指定网址
                                                                    initSDK.initsdk();


                                                                    List<InstructionMsgBody> instructionMsgBodyList = new ArrayList<InstructionMsgBody>();
                                                                    InstructionMsgBody instructionMsgBody0 = new InstructionMsgBody();

                                                                    instructionMsgBody0.setInstructionType(InstructionType.DIRECT_DEFINE);
                                                                    instructionMsgBody0.setInstructionSeparator("#SEPARAL#");
//                                                                instructionMsgBody0.setInstructionContent("am force-stop com.android.browser");//打开Android自带浏览器并指定地址
                                                                    instructionMsgBody0.setInstructionContent("taskkill /F /IM chrome.exe /T");//打开Windows自带浏览器并指定地址

                                                                    instructionMsgBodyList.add(instructionMsgBody0);

                                                                    InstructionMsgBody instructionMsgBody1 = new InstructionMsgBody();
                                                                    instructionMsgBody1.setInstructionType(InstructionType.DIRECT_DEFINE);
                                                                    instructionMsgBody1.setInstructionSeparator("#SEPARAL#");
//                                                                instructionMsgBody1.setInstructionContent("am start -a android.intent.action.VIEW -d http://iot.kito.cn/jeecg/webpage/com/kito/dwr/welcomeVisitor.jsp");//打开Android自带浏览器并指定地址
                                                                    instructionMsgBody1.setInstructionContent("start C:\\Users\\Administrator\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe --kiosk http://cloud.kito.cn/jeecg/webpage/com/kito/dwr/welcomeVisitor.jsp");//打开Windows自带浏览器并指定地址
                                                                    instructionMsgBodyList.add(instructionMsgBody1);

                                                                    Instruction instruction = InstructionUtils.getIotControllerInstruction(instructionMsgBodyList);

                                                                    try {
//                                                                    initSDK.pubControllerMessageToTopic("7Pi3WAFJhC6","/7Pi3WAFJhC6/kitotv01/get",instruction);
                                                                        initSDK.pubControllerMessageToTopic("1Suqb1xdJg1", "/1Suqb1xdJg1/kito_windowstv_000001/get", instruction);
                                                                    } catch (JsonProcessingException e) {
                                                                        e.printStackTrace();
                                                                    }

                                                                    //2、做打开客户显示端前合理延时
                                                                    try {
                                                                        Thread.currentThread().sleep(afterOpenClientDelayTime);
                                                                    } catch (InterruptedException e) {
                                                                        e.printStackTrace();
                                                                    }

                                                                    //3、开始推送相关信息到浏览器端
                                                                    ServerPushMessage serverPushMessage = new ServerPushMessage("showVisitorName");
                                                                    //服务端推送消息
                                                                    JSONObject jsonObject = new JSONObject();
                                                                    jsonObject.put("visitorname", strings[1]);
//                                                                jsonObject.put("headimgurl",);
//                                                                jsonObject.put("sex",);
                                                                    if (serverPushMessage != null) {//找不到客户端就不推送
                                                                        logger.debug("============开始推送给浏览器客户端==================");
                                                                        serverPushMessage.sendMessageAuto(jsonObject.toString());
                                                                        logger.debug("============推送完成===============================");
                                                                    }


                                                                    //4、做关闭客户显示端前合理延时
                                                                    try {
                                                                        Thread.currentThread().sleep(beforeCloseClientDelayTime);
                                                                    } catch (InterruptedException e) {
                                                                        e.printStackTrace();
                                                                    }

                                                                    //5、发送指令关闭客户单显示端


                                                                    InstructionMsgBody instructionMsgBody3 = new InstructionMsgBody();
                                                                    instructionMsgBody3.setInstructionType(InstructionType.DIRECT_DEFINE);
                                                                    instructionMsgBody3.setInstructionSeparator("#SEPARAL#");
//                                                                instructionMsgBody3.setInstructionContent("am force-stop com.android.browser");//打开Android自带浏览器并指定地址
                                                                    instructionMsgBody3.setInstructionContent("taskkill /F /IM chrome.exe /T");//打开Windows自带浏览器并指定地址

                                                                    instructionMsgBodyList.clear();
                                                                    instructionMsgBodyList.add(instructionMsgBody3);

                                                                    instruction.setMsgBody(instructionMsgBodyList);
                                                                    try {
//                                                                    initSDK.pubControllerMessageToTopic("7Pi3WAFJhC6","/7Pi3WAFJhC6/kitotv01/get",instruction);
                                                                        initSDK.pubControllerMessageToTopic("1Suqb1xdJg1", "/1Suqb1xdJg1/kito_windowstv_000001/get", instruction);
                                                                    } catch (JsonProcessingException e) {
                                                                        e.printStackTrace();
                                                                    }

                                                                }
                                                            };

                                                            new Thread(opendoor).start();
                                                        }
                                                    }
                                                }
                                                else {
                                                    rssiList.remove(0);//不满足平均值条件则删除第一个数据
                                                }

                                            }
                                            else {
                                                rssiList.remove(0);//不满足最小临界值条件则删除第一个数据
                                            }
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
