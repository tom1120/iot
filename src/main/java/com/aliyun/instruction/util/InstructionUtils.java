package com.aliyun.instruction.util;/**
 * Created by zhaoyipc on 2017/9/26.
 */

import com.aliyun.instruction.entity.Instruction;
import com.aliyun.instruction.entity.InstructionMsgBody;
import com.aliyun.instruction.entity.MsgType;

import java.util.List;

/**
 * @author zhaoyi
 * @date 2017-09-2017/9/26-21:43
 */
public class InstructionUtils {
    /**
     *
     * @return
     */
    public static Instruction getIotControllerInstruction(List<InstructionMsgBody> instructionMsgBodyList){
        Instruction instruction=new Instruction();
//        instruction.setMsgType(MsgType.iotControllerMsg);
        instruction.setMsgType("iotControllerMsg");
        instruction.setMsgBody(instructionMsgBodyList);
        return instruction;

    }
}
