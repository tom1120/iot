package com.aliyun.instruction.entity;/**
 * Created by zhaoyipc on 2017/8/18.
 */


import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author zhaoyi
 * @date 2017-08-2017/8/18-16:24
 */
public class InstructionMsgBody {
    @JsonIgnore
    private InstructionType instructionType;
    @JsonIgnore
    private String instructionSeparator;
    @JsonIgnore
    private String instructionContent;
    private String instructionMsgBody;

    public InstructionType getInstructionType() {
        return instructionType;
    }

    public void setInstructionType(InstructionType instructionType) {
        this.instructionType = instructionType;
    }

    public String getInstructionSeparator() {
        return instructionSeparator;
    }

    public void setInstructionSeparator(String instructionSeparator) {
        this.instructionSeparator = instructionSeparator;
    }

    public String getInstructionContent() {
        return instructionContent;
    }

    public void setInstructionContent(String instructionContent) {
        this.instructionContent = instructionContent;
    }

    public String getInstructionMsgBody() {
        if(this.instructionMsgBody==null){
            this.instructionMsgBody=this.instructionType+this.instructionSeparator+this.getInstructionContent();
        }
        return instructionMsgBody;
    }

    public void setInstructionMsgBody(String instructionMsgBody) {
        this.instructionMsgBody = instructionMsgBody;
    }
}
