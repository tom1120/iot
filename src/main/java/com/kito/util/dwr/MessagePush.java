package com.kito.util.dwr;/**
 * Created by zhaoyipc on 2016/10/31.
 */

import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContextFactory;

import javax.servlet.ServletException;

/**
 * @author zhaoyi
 * @create 2016-10-2016/10/31-11:01
 */
public class MessagePush implements IMessagePush{
    @Override
    public void onPageLoad(String userId) {
        ScriptSession scriptSession = WebContextFactory.get().getScriptSession();
        scriptSession.setAttribute(userId, userId);
        DwrScriptSessionManagerUtil dwrScriptSessionManagerUtil = new DwrScriptSessionManagerUtil();
        try {
            dwrScriptSessionManagerUtil.init();
            System.out.println("cacaca");
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
