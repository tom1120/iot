package org.jeecgframework.test.dwr;/**
 * Created by zhaoyipc on 2017/9/20.
 */

import com.kito.util.dwr.ServerPushMessage;
import net.sf.json.JSONObject;

/**
 * @author zhaoyi
 * @date 2017-09-2017/9/20-16:25
 */
public class ServerPushMessageTest {
    public static void main(String[] args) {
        ServerPushMessage serverPushMessage = new ServerPushMessage("showVisitorName");
        //服务端推送消息
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("visitorname", "赵义");
//        jsonObject.put("headimgurl", );
//        jsonObject.put("sex", );
        serverPushMessage.sendMessageAuto(jsonObject.toString());
    }
}
