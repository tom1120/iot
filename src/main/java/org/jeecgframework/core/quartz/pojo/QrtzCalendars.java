package org.jeecgframework.core.quartz.pojo;/**
 * Created by zhaoyipc on 2017/6/26.
 */

import javax.persistence.*;
import java.util.Arrays;

/**
 * @author zhaoyi
 * @date 2017-06-2017/6/26-21:25
 */
@Entity
@Table(name = "qrtz_calendars", schema = "jeecg", catalog = "")
@IdClass(QrtzCalendarsPK.class)
public class QrtzCalendars {
    private String schedName;
    private String calendarName;
    private byte[] calendar;

    @Id
    @Column(name = "SCHED_NAME", nullable = false, length = 120)
    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }

    @Id
    @Column(name = "CALENDAR_NAME", nullable = false, length = 150)
    public String getCalendarName() {
        return calendarName;
    }

    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

    @Basic
    @Column(name = "CALENDAR", nullable = false)
    public byte[] getCalendar() {
        return calendar;
    }

    public void setCalendar(byte[] calendar) {
        this.calendar = calendar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QrtzCalendars that = (QrtzCalendars) o;

        if (schedName != null ? !schedName.equals(that.schedName) : that.schedName != null) return false;
        if (calendarName != null ? !calendarName.equals(that.calendarName) : that.calendarName != null) return false;
        if (!Arrays.equals(calendar, that.calendar)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = schedName != null ? schedName.hashCode() : 0;
        result = 31 * result + (calendarName != null ? calendarName.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(calendar);
        return result;
    }
}
