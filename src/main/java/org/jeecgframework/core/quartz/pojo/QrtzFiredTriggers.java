package org.jeecgframework.core.quartz.pojo;/**
 * Created by zhaoyipc on 2017/6/26.
 */

import javax.persistence.*;

/**
 * @author zhaoyi
 * @date 2017-06-2017/6/26-21:25
 */
@Entity
@Table(name = "qrtz_fired_triggers", schema = "jeecg", catalog = "")
@IdClass(QrtzFiredTriggersPK.class)
public class QrtzFiredTriggers {
    private String schedName;
    private String entryId;
    private String triggerName;
    private String triggerGroup;
    private String instanceName;
    private long firedTime;
    private long schedTime;
    private int priority;
    private String state;
    private String jobName;
    private String jobGroup;
    private String isNonconcurrent;
    private String requestsRecovery;

    @Id
    @Column(name = "SCHED_NAME", nullable = false, length = 120)
    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }

    @Id
    @Column(name = "ENTRY_ID", nullable = false, length = 95)
    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    @Basic
    @Column(name = "TRIGGER_NAME", nullable = false, length = 150)
    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    @Basic
    @Column(name = "TRIGGER_GROUP", nullable = false, length = 150)
    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    @Basic
    @Column(name = "INSTANCE_NAME", nullable = false, length = 150)
    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    @Basic
    @Column(name = "FIRED_TIME", nullable = false)
    public long getFiredTime() {
        return firedTime;
    }

    public void setFiredTime(long firedTime) {
        this.firedTime = firedTime;
    }

    @Basic
    @Column(name = "SCHED_TIME", nullable = false)
    public long getSchedTime() {
        return schedTime;
    }

    public void setSchedTime(long schedTime) {
        this.schedTime = schedTime;
    }

    @Basic
    @Column(name = "PRIORITY", nullable = false)
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Basic
    @Column(name = "STATE", nullable = false, length = 16)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "JOB_NAME", nullable = true, length = 150)
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Basic
    @Column(name = "JOB_GROUP", nullable = true, length = 150)
    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    @Basic
    @Column(name = "IS_NONCONCURRENT", nullable = true, length = 1)
    public String getIsNonconcurrent() {
        return isNonconcurrent;
    }

    public void setIsNonconcurrent(String isNonconcurrent) {
        this.isNonconcurrent = isNonconcurrent;
    }

    @Basic
    @Column(name = "REQUESTS_RECOVERY", nullable = true, length = 1)
    public String getRequestsRecovery() {
        return requestsRecovery;
    }

    public void setRequestsRecovery(String requestsRecovery) {
        this.requestsRecovery = requestsRecovery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QrtzFiredTriggers that = (QrtzFiredTriggers) o;

        if (firedTime != that.firedTime) return false;
        if (schedTime != that.schedTime) return false;
        if (priority != that.priority) return false;
        if (schedName != null ? !schedName.equals(that.schedName) : that.schedName != null) return false;
        if (entryId != null ? !entryId.equals(that.entryId) : that.entryId != null) return false;
        if (triggerName != null ? !triggerName.equals(that.triggerName) : that.triggerName != null) return false;
        if (triggerGroup != null ? !triggerGroup.equals(that.triggerGroup) : that.triggerGroup != null) return false;
        if (instanceName != null ? !instanceName.equals(that.instanceName) : that.instanceName != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (jobName != null ? !jobName.equals(that.jobName) : that.jobName != null) return false;
        if (jobGroup != null ? !jobGroup.equals(that.jobGroup) : that.jobGroup != null) return false;
        if (isNonconcurrent != null ? !isNonconcurrent.equals(that.isNonconcurrent) : that.isNonconcurrent != null)
            return false;
        if (requestsRecovery != null ? !requestsRecovery.equals(that.requestsRecovery) : that.requestsRecovery != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = schedName != null ? schedName.hashCode() : 0;
        result = 31 * result + (entryId != null ? entryId.hashCode() : 0);
        result = 31 * result + (triggerName != null ? triggerName.hashCode() : 0);
        result = 31 * result + (triggerGroup != null ? triggerGroup.hashCode() : 0);
        result = 31 * result + (instanceName != null ? instanceName.hashCode() : 0);
        result = 31 * result + (int) (firedTime ^ (firedTime >>> 32));
        result = 31 * result + (int) (schedTime ^ (schedTime >>> 32));
        result = 31 * result + priority;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (jobName != null ? jobName.hashCode() : 0);
        result = 31 * result + (jobGroup != null ? jobGroup.hashCode() : 0);
        result = 31 * result + (isNonconcurrent != null ? isNonconcurrent.hashCode() : 0);
        result = 31 * result + (requestsRecovery != null ? requestsRecovery.hashCode() : 0);
        return result;
    }
}
