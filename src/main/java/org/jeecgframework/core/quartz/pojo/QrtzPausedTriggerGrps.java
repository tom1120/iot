package org.jeecgframework.core.quartz.pojo;/**
 * Created by zhaoyipc on 2017/6/26.
 */

import javax.persistence.*;

/**
 * @author zhaoyi
 * @date 2017-06-2017/6/26-21:25
 */
@Entity
@Table(name = "qrtz_paused_trigger_grps", schema = "jeecg", catalog = "")
@IdClass(QrtzPausedTriggerGrpsPK.class)
public class QrtzPausedTriggerGrps {
    private String schedName;
    private String triggerGroup;

    @Id
    @Column(name = "SCHED_NAME", nullable = false, length = 120)
    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }

    @Id
    @Column(name = "TRIGGER_GROUP", nullable = false, length = 150)
    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QrtzPausedTriggerGrps that = (QrtzPausedTriggerGrps) o;

        if (schedName != null ? !schedName.equals(that.schedName) : that.schedName != null) return false;
        if (triggerGroup != null ? !triggerGroup.equals(that.triggerGroup) : that.triggerGroup != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = schedName != null ? schedName.hashCode() : 0;
        result = 31 * result + (triggerGroup != null ? triggerGroup.hashCode() : 0);
        return result;
    }
}
