package com.kito.util.dwr;/**
 * Created by zhaoyipc on 2016/10/31.
 */

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;

import java.util.Collection;

/**
 * @author zhaoyi
 * @create 2016-10-2016/10/31-10:33
 */
public class ServerPushMessage {
    private String jsFunctionName;

    public ServerPushMessage() {
    }

    public ServerPushMessage(String jsFunctionName) {
        this.jsFunctionName=jsFunctionName;
    }

    public void sendMessageAuto(String message){

//      final String userId = userid;
        final String autoMessage = message;
        Browser.withAllSessionsFiltered(new ScriptSessionFilter() {
            public boolean match(ScriptSession session){
                return true;
//              if (session.getAttribute("userId") == null)
//                  return false;
//              else
//                  return (session.getAttribute("userId")).equals(userId);
            }
        }, new Runnable(){

            private ScriptBuffer script = new ScriptBuffer();

            public void run(){

                script.appendCall(jsFunctionName, autoMessage);

                Collection<ScriptSession> sessions = Browser

                        .getTargetSessions();

                for (ScriptSession scriptSession : sessions){
                    scriptSession.addScript(script);
                }
            }
        });
    }
}
