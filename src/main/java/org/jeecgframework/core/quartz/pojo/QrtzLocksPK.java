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
public class QrtzLocksPK implements Serializable {
    private String schedName;
    private String lockName;

    @Column(name = "SCHED_NAME", nullable = false, length = 120)
    @Id
    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }

    @Column(name = "LOCK_NAME", nullable = false, length = 40)
    @Id
    public String getLockName() {
        return lockName;
    }

    public void setLockName(String lockName) {
        this.lockName = lockName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QrtzLocksPK that = (QrtzLocksPK) o;

        if (schedName != null ? !schedName.equals(that.schedName) : that.schedName != null) return false;
        if (lockName != null ? !lockName.equals(that.lockName) : that.lockName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = schedName != null ? schedName.hashCode() : 0;
        result = 31 * result + (lockName != null ? lockName.hashCode() : 0);
        return result;
    }
}
