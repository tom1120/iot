package org.jeecgframework.core.quartz.pojo;/**
 * Created by zhaoyipc on 2017/6/26.
 */

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author zhaoyi
 * @date 2017-06-2017/6/26-21:25
 */
@Entity
@Table(name = "qrtz_simprop_triggers", schema = "jeecg", catalog = "")
@IdClass(QrtzSimpropTriggersPK.class)
public class QrtzSimpropTriggers {
    private String schedName;
    private String triggerName;
    private String triggerGroup;
    private String strProp1;
    private String strProp2;
    private String strProp3;
    private Integer intProp1;
    private Integer intProp2;
    private Long longProp1;
    private Long longProp2;
    private BigDecimal decProp1;
    private BigDecimal decProp2;
    private String boolProp1;
    private String boolProp2;

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

    @Basic
    @Column(name = "STR_PROP_1", nullable = true, length = 512)
    public String getStrProp1() {
        return strProp1;
    }

    public void setStrProp1(String strProp1) {
        this.strProp1 = strProp1;
    }

    @Basic
    @Column(name = "STR_PROP_2", nullable = true, length = 512)
    public String getStrProp2() {
        return strProp2;
    }

    public void setStrProp2(String strProp2) {
        this.strProp2 = strProp2;
    }

    @Basic
    @Column(name = "STR_PROP_3", nullable = true, length = 512)
    public String getStrProp3() {
        return strProp3;
    }

    public void setStrProp3(String strProp3) {
        this.strProp3 = strProp3;
    }

    @Basic
    @Column(name = "INT_PROP_1", nullable = true)
    public Integer getIntProp1() {
        return intProp1;
    }

    public void setIntProp1(Integer intProp1) {
        this.intProp1 = intProp1;
    }

    @Basic
    @Column(name = "INT_PROP_2", nullable = true)
    public Integer getIntProp2() {
        return intProp2;
    }

    public void setIntProp2(Integer intProp2) {
        this.intProp2 = intProp2;
    }

    @Basic
    @Column(name = "LONG_PROP_1", nullable = true)
    public Long getLongProp1() {
        return longProp1;
    }

    public void setLongProp1(Long longProp1) {
        this.longProp1 = longProp1;
    }

    @Basic
    @Column(name = "LONG_PROP_2", nullable = true)
    public Long getLongProp2() {
        return longProp2;
    }

    public void setLongProp2(Long longProp2) {
        this.longProp2 = longProp2;
    }

    @Basic
    @Column(name = "DEC_PROP_1", nullable = true, precision = 4)
    public BigDecimal getDecProp1() {
        return decProp1;
    }

    public void setDecProp1(BigDecimal decProp1) {
        this.decProp1 = decProp1;
    }

    @Basic
    @Column(name = "DEC_PROP_2", nullable = true, precision = 4)
    public BigDecimal getDecProp2() {
        return decProp2;
    }

    public void setDecProp2(BigDecimal decProp2) {
        this.decProp2 = decProp2;
    }

    @Basic
    @Column(name = "BOOL_PROP_1", nullable = true, length = 1)
    public String getBoolProp1() {
        return boolProp1;
    }

    public void setBoolProp1(String boolProp1) {
        this.boolProp1 = boolProp1;
    }

    @Basic
    @Column(name = "BOOL_PROP_2", nullable = true, length = 1)
    public String getBoolProp2() {
        return boolProp2;
    }

    public void setBoolProp2(String boolProp2) {
        this.boolProp2 = boolProp2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QrtzSimpropTriggers that = (QrtzSimpropTriggers) o;

        if (schedName != null ? !schedName.equals(that.schedName) : that.schedName != null) return false;
        if (triggerName != null ? !triggerName.equals(that.triggerName) : that.triggerName != null) return false;
        if (triggerGroup != null ? !triggerGroup.equals(that.triggerGroup) : that.triggerGroup != null) return false;
        if (strProp1 != null ? !strProp1.equals(that.strProp1) : that.strProp1 != null) return false;
        if (strProp2 != null ? !strProp2.equals(that.strProp2) : that.strProp2 != null) return false;
        if (strProp3 != null ? !strProp3.equals(that.strProp3) : that.strProp3 != null) return false;
        if (intProp1 != null ? !intProp1.equals(that.intProp1) : that.intProp1 != null) return false;
        if (intProp2 != null ? !intProp2.equals(that.intProp2) : that.intProp2 != null) return false;
        if (longProp1 != null ? !longProp1.equals(that.longProp1) : that.longProp1 != null) return false;
        if (longProp2 != null ? !longProp2.equals(that.longProp2) : that.longProp2 != null) return false;
        if (decProp1 != null ? !decProp1.equals(that.decProp1) : that.decProp1 != null) return false;
        if (decProp2 != null ? !decProp2.equals(that.decProp2) : that.decProp2 != null) return false;
        if (boolProp1 != null ? !boolProp1.equals(that.boolProp1) : that.boolProp1 != null) return false;
        if (boolProp2 != null ? !boolProp2.equals(that.boolProp2) : that.boolProp2 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = schedName != null ? schedName.hashCode() : 0;
        result = 31 * result + (triggerName != null ? triggerName.hashCode() : 0);
        result = 31 * result + (triggerGroup != null ? triggerGroup.hashCode() : 0);
        result = 31 * result + (strProp1 != null ? strProp1.hashCode() : 0);
        result = 31 * result + (strProp2 != null ? strProp2.hashCode() : 0);
        result = 31 * result + (strProp3 != null ? strProp3.hashCode() : 0);
        result = 31 * result + (intProp1 != null ? intProp1.hashCode() : 0);
        result = 31 * result + (intProp2 != null ? intProp2.hashCode() : 0);
        result = 31 * result + (longProp1 != null ? longProp1.hashCode() : 0);
        result = 31 * result + (longProp2 != null ? longProp2.hashCode() : 0);
        result = 31 * result + (decProp1 != null ? decProp1.hashCode() : 0);
        result = 31 * result + (decProp2 != null ? decProp2.hashCode() : 0);
        result = 31 * result + (boolProp1 != null ? boolProp1.hashCode() : 0);
        result = 31 * result + (boolProp2 != null ? boolProp2.hashCode() : 0);
        return result;
    }
}
