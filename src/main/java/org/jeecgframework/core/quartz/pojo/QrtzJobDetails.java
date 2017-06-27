package org.jeecgframework.core.quartz.pojo;/**
 * Created by zhaoyipc on 2017/6/26.
 */

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author zhaoyi
 * @date 2017-06-2017/6/26-21:25
 */
@Entity
@Table(name = "qrtz_job_details", schema = "jeecg", catalog = "")
@IdClass(QrtzJobDetailsPK.class)
public class QrtzJobDetails {
    private String schedName;
    private String jobName;
    private String jobGroup;
    private String description;
    private String jobClassName;
    private String isDurable;
    private String isNonconcurrent;
    private String isUpdateData;
    private String requestsRecovery;
    private byte[] jobData;
    //触发器，一个任务可以有多个触发器
    private Set<QrtzTriggers> triggersSet;


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

    @OneToMany(mappedBy = "qrtzJobDetails",fetch = FetchType.EAGER)
    public Set<QrtzTriggers> getTriggersSet() {
        return triggersSet;
    }

    public void setTriggersSet(Set<QrtzTriggers> triggersSet) {
        this.triggersSet = triggersSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QrtzJobDetails that = (QrtzJobDetails) o;

        if (schedName != null ? !schedName.equals(that.schedName) : that.schedName != null) return false;
        if (jobName != null ? !jobName.equals(that.jobName) : that.jobName != null) return false;
        if (jobGroup != null ? !jobGroup.equals(that.jobGroup) : that.jobGroup != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (jobClassName != null ? !jobClassName.equals(that.jobClassName) : that.jobClassName != null) return false;
        if (isDurable != null ? !isDurable.equals(that.isDurable) : that.isDurable != null) return false;
        if (isNonconcurrent != null ? !isNonconcurrent.equals(that.isNonconcurrent) : that.isNonconcurrent != null)
            return false;
        if (isUpdateData != null ? !isUpdateData.equals(that.isUpdateData) : that.isUpdateData != null) return false;
        if (requestsRecovery != null ? !requestsRecovery.equals(that.requestsRecovery) : that.requestsRecovery != null)
            return false;
        if (!Arrays.equals(jobData, that.jobData)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = schedName != null ? schedName.hashCode() : 0;
        result = 31 * result + (jobName != null ? jobName.hashCode() : 0);
        result = 31 * result + (jobGroup != null ? jobGroup.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (jobClassName != null ? jobClassName.hashCode() : 0);
        result = 31 * result + (isDurable != null ? isDurable.hashCode() : 0);
        result = 31 * result + (isNonconcurrent != null ? isNonconcurrent.hashCode() : 0);
        result = 31 * result + (isUpdateData != null ? isUpdateData.hashCode() : 0);
        result = 31 * result + (requestsRecovery != null ? requestsRecovery.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(jobData);
        return result;
    }
}
