package org.jeecgframework.core.quartz.pojo;/**
 * Created by zhaoyipc on 2017/6/26.
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author zhaoyi
 * @date 2017-06-2017/6/26-21:25
 * //@IdClass(QrtzJobDetailsPK.class)
 */
@Entity
@Table(name = "qrtz_job_details")
@IdClass(QrtzJobDetailsPK.class)
public class QrtzJobDetails implements Serializable{
    private String schedName;
    private String jobName;
    private String jobGroup;
    private QrtzJobDetailsPK id;
    private String description;
    private String jobClassName;
    private String isDurable;
    private String isNonconcurrent;
    private String isUpdateData;
    private String requestsRecovery;
    private byte[] jobData;
    //触发器，一个任务可以有多个触发器
//    private Set<QrtzTriggers> triggersSet;


        @Id
    @Column(name = "SCHED_NAME", nullable = false, length = 120)
    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }

    @Id
    @Column(name = "JOB_NAME", nullable = false, length = 150)
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Id
    @Column(name = "JOB_GROUP", nullable = false, length = 150)
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
    @Column(name = "JOB_CLASS_NAME", nullable = false, length = 250)
    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    @Basic
    @Column(name = "IS_DURABLE", nullable = false, length = 1)
    public String getIsDurable() {
        return isDurable;
    }

    public void setIsDurable(String isDurable) {
        this.isDurable = isDurable;
    }

    @Basic
    @Column(name = "IS_NONCONCURRENT", nullable = false, length = 1)
    public String getIsNonconcurrent() {
        return isNonconcurrent;
    }

    public void setIsNonconcurrent(String isNonconcurrent) {
        this.isNonconcurrent = isNonconcurrent;
    }

    @Basic
    @Column(name = "IS_UPDATE_DATA", nullable = false, length = 1)
    public String getIsUpdateData() {
        return isUpdateData;
    }

    public void setIsUpdateData(String isUpdateData) {
        this.isUpdateData = isUpdateData;
    }

    @Basic
    @Column(name = "REQUESTS_RECOVERY", nullable = false, length = 1)
    public String getRequestsRecovery() {
        return requestsRecovery;
    }

    public void setRequestsRecovery(String requestsRecovery) {
        this.requestsRecovery = requestsRecovery;
    }

    @Basic
    @Column(name = "JOB_DATA", nullable = true)
    public byte[] getJobData() {
        return jobData;
    }

    public void setJobData(byte[] jobData) {
        this.jobData = jobData;
    }

/*    @OneToMany(mappedBy = "qrtzJobDetails",fetch = FetchType.EAGER)
    public Set<QrtzTriggers> getTriggersSet() {
        return triggersSet;
    }

    public void setTriggersSet(Set<QrtzTriggers> triggersSet) {
        this.triggersSet = triggersSet;
    }*/


    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QrtzJobDetails that = (QrtzJobDetails) o;

        if (getSchedName() != null ? !getSchedName().equals(that.getSchedName()) : that.getSchedName() != null)
            return false;
        if (getJobName() != null ? !getJobName().equals(that.getJobName()) : that.getJobName() != null) return false;
        if (getJobGroup() != null ? !getJobGroup().equals(that.getJobGroup()) : that.getJobGroup() != null)
            return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
            return false;
        if (getJobClassName() != null ? !getJobClassName().equals(that.getJobClassName()) : that.getJobClassName() != null)
            return false;
        if (getIsDurable() != null ? !getIsDurable().equals(that.getIsDurable()) : that.getIsDurable() != null)
            return false;
        if (getIsNonconcurrent() != null ? !getIsNonconcurrent().equals(that.getIsNonconcurrent()) : that.getIsNonconcurrent() != null)
            return false;
        if (getIsUpdateData() != null ? !getIsUpdateData().equals(that.getIsUpdateData()) : that.getIsUpdateData() != null)
            return false;
        if (getRequestsRecovery() != null ? !getRequestsRecovery().equals(that.getRequestsRecovery()) : that.getRequestsRecovery() != null)
            return false;
        if (!Arrays.equals(getJobData(), that.getJobData())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getSchedName() != null ? getSchedName().hashCode() : 0;
        result = 31 * result + (getJobName() != null ? getJobName().hashCode() : 0);
        result = 31 * result + (getJobGroup() != null ? getJobGroup().hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getJobClassName() != null ? getJobClassName().hashCode() : 0);
        result = 31 * result + (getIsDurable() != null ? getIsDurable().hashCode() : 0);
        result = 31 * result + (getIsNonconcurrent() != null ? getIsNonconcurrent().hashCode() : 0);
        result = 31 * result + (getIsUpdateData() != null ? getIsUpdateData().hashCode() : 0);
        result = 31 * result + (getRequestsRecovery() != null ? getRequestsRecovery().hashCode() : 0);
        result = 31 * result + Arrays.hashCode(getJobData());
        return result;
    }
}
