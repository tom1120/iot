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
public class QrtzFiredTriggersPK implements Serializable {
    private String schedName;
    private String entryId;

    @Column(name = "SCHED_NAME", nullable = false, length = 120)
    @Id
    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }

    @Column(name = "ENTRY_ID", nullable = false, length = 95)
    @Id
    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QrtzFiredTriggersPK that = (QrtzFiredTriggersPK) o;

        if (schedName != null ? !schedName.equals(that.schedName) : that.schedName != null) return false;
        if (entryId != null ? !entryId.equals(that.entryId) : that.entryId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = schedName != null ? schedName.hashCode() : 0;
        result = 31 * result + (entryId != null ? entryId.hashCode() : 0);
        return result;
    }
}
