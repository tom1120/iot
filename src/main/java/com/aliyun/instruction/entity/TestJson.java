package com.aliyun.instruction.entity;/**
 * Created by zhaoyipc on 2017/8/18.
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyi
 * @date 2017-08-2017/8/18-16:36
 */
public class TestJson {
    private static ObjectMapper objectMapper= new ObjectMapper();;
    public static void main(String[] args) throws JsonProcessingException {

        Instruction instruction=new Instruction();
//        instruction.setMsgType(MsgType.iotControllerMsg);
        instruction.setMsgType("iotControllerMsg");

        List<InstructionMsgBody> instructionMsgBodyList=new ArrayList<InstructionMsgBody>();
        InstructionMsgBody instructionMsgBody0=new InstructionMsgBody();
        instructionMsgBody0.setInstructionType(InstructionType.CUSTOMER_DEFINE);
        instructionMsgBody0.setInstructionSeparator("#SEPARAL#");
        instructionMsgBody0.setInstructionContent("test0");
        InstructionMsgBody instructionMsgBody1=new InstructionMsgBody();
        instructionMsgBody1.setInstructionType(InstructionType.DIRECT_DEFINE);
        instructionMsgBody1.setInstructionSeparator("#SEPARAL#");
        instructionMsgBody1.setInstructionContent("test1");
        InstructionMsgBody instructionMsgBody2=new InstructionMsgBody();
        instructionMsgBody2.setInstructionType(InstructionType.APP_SERVICE);
        instructionMsgBody2.setInstructionSeparator("#SEPARAL#");
        instructionMsgBody2.setInstructionContent("test2");

        instructionMsgBodyList.add(instructionMsgBody0);
        instructionMsgBodyList.add(instructionMsgBody1);
        instructionMsgBodyList.add(instructionMsgBody2);

        instruction.setMsgBody(instructionMsgBodyList);


       String jsonObject = objectMapper.writeValueAsString(instruction);
        System.out.println("jsonObject = " + jsonObject);

    }
}
