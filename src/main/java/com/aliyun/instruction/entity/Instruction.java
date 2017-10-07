package com.aliyun.instruction.entity;/**
 * Created by zhaoyipc on 2017/8/18.
 */

import java.util.List;

/**
 * @author zhaoyi
 * @date 2017-08-2017/8/18-16:02
 */
public class Instruction {
//    private MsgType msgType;
    private String msgType;
    private List<InstructionMsgBody> msgBody;

//    public MsgType getMsgType() {
//        return msgType;
//    }
//
//    public void setMsgType(MsgType msgType) {
//        this.msgType = msgType;
//    }


    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public List<InstructionMsgBody> getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(List<InstructionMsgBody> msgBody) {
        this.msgBody = msgBody;
    }
}
