package org.jeecgframework.core.quartz.pojo;/**
 * Created by zhaoyipc on 2017/6/26.
 */

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author zhaoyi
 * @date 2017-06-2017/6/26-21:25
 */
public class QrtzSchedulerStatePK implements Serializable {
    private String schedName;
    private String instanceName;

    @Column(name = "SCHED_NAME", nullable = false, length = 120)
    @Id
    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }

    @Column(name = "INSTANCE_NAME", nullable = false, length = 150)
    @Id
    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QrtzSchedulerStatePK that = (QrtzSchedulerStatePK) o;

        if (schedName != null ? !schedName.equals(that.schedName) : that.schedName != null) return false;
        if (instanceName != null ? !instanceName.equals(that.instanceName) : that.instanceName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = schedName != null ? schedName.hashCode() : 0;
        result = 31 * result + (instanceName != null ? instanceName.hashCode() : 0);
        return result;
    }
}
