package org.jeecgframework.core.quartz.pojo;/**
 * Created by zhaoyipc on 2017/6/26.
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

/**
 * @author zhaoyi
 * @date 2017-06-2017/6/26-21:25
 */
@Entity
@Table(name = "qrtz_triggers", schema = "jeecg", catalog = "")
@IdClass(QrtzTriggersPK.class)
public class QrtzTriggers implements Serializable{
    private String schedName;
    private String triggerName;
    private String triggerGroup;
    private String jobName;
    private String jobGroup;
    private String description;
    private Long nextFireTime;
    private Long prevFireTime;
    private Integer priority;
    private String triggerState;
    private String triggerType;
    private long startTime;
    private Long endTime;
    private String calendarName;
    private Short misfireInstr;
    private byte[] jobData;
    //任务，一个触发器只能有一个Job
//    private QrtzJobDetails qrtzJobDetails;
    //任务表达式
    private QrtzCronTriggers qrtzCronTriggers;
    @Id
    @Column(name = "SCHED_NAME", nullable = false, length = 120)
    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }

    @Id
    @Column(name = "TRIGGER_NAME", nullable = false, length = 150)
    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    @Id
    @Column(name = "TRIGGER_GROUP", nullable = false, length = 150)
    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    @Column(name = "JOB_NAME",nullable = false,length = 150)
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
    @Column(name = "JOB_GROUP",nullable = false,length = 150)
    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 250)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "NEXT_FIRE_TIME", nullable = true)
    public Long getNextFireTime() {
        return nextFireTime;
    }

    public void setNextFireTime(Long nextFireTime) {
        this.nextFireTime = nextFireTime;
    }

    @Basic
    @Column(name = "PREV_FIRE_TIME", nullable = true)
    public Long getPrevFireTime() {
        return prevFireTime;
    }

    public void setPrevFireTime(Long prevFireTime) {
        this.prevFireTime = prevFireTime;
    }

    @Basic
    @Column(name = "PRIORITY", nullable = true)
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Basic
//    @Column(name = "TRIGGER_STATE", nullable = false, length = 16)
    @Column(name = "TRIGGER_STATE", nullable = true, length = 16)
    public String getTriggerState() {
        return triggerState;
    }

    public void setTriggerState(String triggerState) {
        this.triggerState = triggerState;
    }

    @Basic
//    @Column(name = "TRIGGER_TYPE", nullable = false, length = 8)
    @Column(name = "TRIGGER_TYPE", nullable = true, length = 8)
    public String getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType;
    }

    @Basic
//    @Column(name = "START_TIME", nullable = false)
    @Column(name = "START_TIME", nullable = true)
    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "END_TIME", nullable = true)
    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "CALENDAR_NAME", nullable = true, length = 100)
    public String getCalendarName() {
        return calendarName;
    }

    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

    @Basic
    @Column(name = "MISFIRE_INSTR", nullable = true)
    public Short getMisfireInstr() {
        return misfireInstr;
    }

    public void setMisfireInstr(Short misfireInstr) {
        this.misfireInstr = misfireInstr;
    }

    @Basic
    @Column(name = "JOB_DATA", nullable = true)
    public byte[] getJobData() {
        return jobData;
    }

    public void setJobData(byte[] jobData) {
        this.jobData = jobData;
    }

 /*   @ManyToOne
    @JoinColumns({

            @JoinColumn(name="SCHED_NAME",referencedColumnName="SCHED_NAME",insertable = false,updatable = false),
            @JoinColumn(name="JOB_NAME",referencedColumnName="JOB_NAME",insertable = false,updatable = false),
            @JoinColumn(name="JOB_GROUP",referencedColumnName="JOB_GROUP",insertable = false,updatable = false),
    })
    public QrtzJobDetails getQrtzJobDetails() {
        return qrtzJobDetails;
    }

    public void setQrtzJobDetails(QrtzJobDetails qrtzJobDetails) {
        this.qrtzJobDetails = qrtzJobDetails;
    }*/

    @OneToOne
    @JoinColumns({
            @JoinColumn(name="SCHED_NAME",referencedColumnName = "SCHED_NAME",insertable = false,updatable = false),
            @JoinColumn(name="TRIGGER_NAME",referencedColumnName = "TRIGGER_NAME",insertable = false,updatable = false),
            @JoinColumn(name="TRIGGER_GROUP",referencedColumnName = "TRIGGER_GROUP",insertable = false,updatable = false),
    })
    public QrtzCronTriggers getQrtzCronTriggers() {
        return qrtzCronTriggers;
    }

    public void setQrtzCronTriggers(QrtzCronTriggers qrtzCronTriggers) {
        this.qrtzCronTriggers = qrtzCronTriggers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QrtzTriggers that = (QrtzTriggers) o;

        if (getStartTime() != that.getStartTime()) return false;
        if (getSchedName() != null ? !getSchedName().equals(that.getSchedName()) : that.getSchedName() != null)
            return false;
        if (getTriggerName() != null ? !getTriggerName().equals(that.getTriggerName()) : that.getTriggerName() != null)
            return false;
        if (getTriggerGroup() != null ? !getTriggerGroup().equals(that.getTriggerGroup()) : that.getTriggerGroup() != null)
            return false;
        if (getJobName() != null ? !getJobName().equals(that.getJobName()) : that.getJobName() != null) return false;
        if (getJobGroup() != null ? !getJobGroup().equals(that.getJobGroup()) : that.getJobGroup() != null)
            return false;
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
            return false;
        if (getNextFireTime() != null ? !getNextFireTime().equals(that.getNextFireTime()) : that.getNextFireTime() != null)
            return false;
        if (getPrevFireTime() != null ? !getPrevFireTime().equals(that.getPrevFireTime()) : that.getPrevFireTime() != null)
            return false;
        if (getPriority() != null ? !getPriority().equals(that.getPriority()) : that.getPriority() != null)
            return false;
        if (getTriggerState() != null ? !getTriggerState().equals(that.getTriggerState()) : that.getTriggerState() != null)
            return false;
        if (getTriggerType() != null ? !getTriggerType().equals(that.getTriggerType()) : that.getTriggerType() != null)
            return false;
        if (getEndTime() != null ? !getEndTime().equals(that.getEndTime()) : that.getEndTime() != null) return false;
        if (getCalendarName() != null ? !getCalendarName().equals(that.getCalendarName()) : that.getCalendarName() != null)
            return false;
        if (getMisfireInstr() != null ? !getMisfireInstr().equals(that.getMisfireInstr()) : that.getMisfireInstr() != null)
            return false;
        if (!Arrays.equals(getJobData(), that.getJobData())) return false;
        return getQrtzCronTriggers() != null ? getQrtzCronTriggers().equals(that.getQrtzCronTriggers()) : that.getQrtzCronTriggers() == null;

    }

    @Override
    public int hashCode() {
        int result = getSchedName() != null ? getSchedName().hashCode() : 0;
        result = 31 * result + (getTriggerName() != null ? getTriggerName().hashCode() : 0);
        result = 31 * result + (getTriggerGroup() != null ? getTriggerGroup().hashCode() : 0);
        result = 31 * result + (getJobName() != null ? getJobName().hashCode() : 0);
        result = 31 * result + (getJobGroup() != null ? getJobGroup().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getNextFireTime() != null ? getNextFireTime().hashCode() : 0);
        result = 31 * result + (getPrevFireTime() != null ? getPrevFireTime().hashCode() : 0);
        result = 31 * result + (getPriority() != null ? getPriority().hashCode() : 0);
        result = 31 * result + (getTriggerState() != null ? getTriggerState().hashCode() : 0);
        result = 31 * result + (getTriggerType() != null ? getTriggerType().hashCode() : 0);
        result = 31 * result + (int) (getStartTime() ^ (getStartTime() >>> 32));
        result = 31 * result + (getEndTime() != null ? getEndTime().hashCode() : 0);
        result = 31 * result + (getCalendarName() != null ? getCalendarName().hashCode() : 0);
        result = 31 * result + (getMisfireInstr() != null ? getMisfireInstr().hashCode() : 0);
        result = 31 * result + Arrays.hashCode(getJobData());
        result = 31 * result + (getQrtzCronTriggers() != null ? getQrtzCronTriggers().hashCode() : 0);
        return result;
    }
}
