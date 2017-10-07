package com.aliyun.iot;

import com.aliyun.instruction.entity.Instruction;
import com.aliyun.instruction.entity.InstructionMsgBody;
import com.aliyun.instruction.entity.InstructionType;
import com.aliyun.instruction.entity.MsgType;
import com.aliyun.instruction.util.InstructionUtils;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.iot.model.v20170620.PubRequest;
import com.aliyuncs.iot.model.v20170620.PubResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhaoyipc on 2017/9/25.
 */
public class InitSDKTestTest {



/*    @Test
    public void pubWindowsControllerMessageToTopic() throws Exception {
        InitSDKTest initSDK=new InitSDKTest();
        initSDK.initsdk();
//        initSDK.pubMessageToTopic();

        Instruction instruction=new Instruction();
//        instruction.setMsgType(MsgType.iotControllerMsg);
        instruction.setMsgType("iotControllerMsg");

        List<InstructionMsgBody> instructionMsgBodyList=new ArrayList<InstructionMsgBody>();
        InstructionMsgBody instructionMsgBody0=new InstructionMsgBody();

        instructionMsgBody0.setInstructionType(InstructionType.DIRECT_DEFINE);
        instructionMsgBody0.setInstructionSeparator("#SEPARAL#");
        instructionMsgBody0.setInstructionContent("taskkill /F /IM chrome.exe /T");//打开Windows自带浏览器并指定地址

        instructionMsgBodyList.add(instructionMsgBody0);

        InstructionMsgBody instructionMsgBody1=new InstructionMsgBody();

        instructionMsgBody1.setInstructionType(InstructionType.DIRECT_DEFINE);
        instructionMsgBody1.setInstructionSeparator("#SEPARAL#");
        instructionMsgBody1.setInstructionContent("start C:/\"Program Files (x86)\"/Google/Chrome/Application/chrome.exe --kiosk www.kito.cn");//打开Windows自带浏览器并指定地址
//        instructionMsgBody1.setInstructionContent("start C:/Users/Administrator/AppData/Local/Google/Chrome/Application/chrome.exe --kiosk  www.baidu.com");//打开Windows自带浏览器并指定地址
//        instructionMsgBody1.setInstructionContent("start notepad.exe");//打开Windows自带浏览器并指定地址

        instructionMsgBodyList.add(instructionMsgBody1);

        instruction.setMsgBody(instructionMsgBodyList);

//        Instruction instruction= InstructionUtils.getIotControllerInstruction(instructionMsgBodyList);



        try {
//            initSDK.pubControllerMessageToTopic("7Pi3WAFJhC6","/7Pi3WAFJhC6/kitotv01/get",instruction);
            initSDK.pubControllerMessageToTopic("1Suqb1xdJg1","/1Suqb1xdJg1/kito_windowstv_000001/get",instruction);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }*/

}