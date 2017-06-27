package org.jeecgframework.core.quartz.pojo;/**
 * Created by zhaoyipc on 2017/6/26.
 */

import javax.persistence.*;

/**
 * @author zhaoyi
 * @date 2017-06-2017/6/26-21:25
 */
@Entity
@Table(name = "qrtz_locks", schema = "jeecg", catalog = "")
@IdClass(QrtzLocksPK.class)
public class QrtzLocks {
    private String schedName;
    private String lockName;

    @Id
    @Column(name = "SCHED_NAME", nullable = false, length = 120)
    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }

    @Id
    @Column(name = "LOCK_NAME", nullable = false, length = 40)
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

        QrtzLocks qrtzLocks = (QrtzLocks) o;

        if (schedName != null ? !schedName.equals(qrtzLocks.schedName) : qrtzLocks.schedName != null) return false;
        if (lockName != null ? !lockName.equals(qrtzLocks.lockName) : qrtzLocks.lockName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = schedName != null ? schedName.hashCode() : 0;
        result = 31 * result + (lockName != null ? lockName.hashCode() : 0);
        return result;
    }
}
