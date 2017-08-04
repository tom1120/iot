package com.jeecg.entity.aliyuniot;/**
 * Created by zhaoyipc on 2017/8/3.
 */

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author zhaoyi
 * @date 2017-08-2017/8/3-8:49
 */
@Entity
@Table(name = "device_status_news", schema = "jeecg")
public class DeviceStatusNews {
    private String id;
    private String productKey;
    private String deviceKey;
    private String deviceStatus;
    private String statusReturnTime;
    private String statusLastcheckTime;
    private String note;

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name = "id", nullable = false, length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "product_key", nullable = false, length = 50)
    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    @Basic
    @Column(name = "device_key", nullable = false, length = 50)
    public String getDeviceKey() {
        return deviceKey;
    }

    public void setDeviceKey(String deviceKey) {
        this.deviceKey = deviceKey;
    }

    @Basic
    @Column(name = "device_status", nullable = false, length = 20)
    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    @Basic
    @Column(name = "status_return_time", nullable = false, length = 50)
    public String getStatusReturnTime() {
        return statusReturnTime;
    }

    public void setStatusReturnTime(String statusReturnTime) {
        this.statusReturnTime = statusReturnTime;
    }

    @Basic
    @Column(name = "status_lastcheck_time", nullable = false, length = 50)
    public String getStatusLastcheckTime() {
        return statusLastcheckTime;
    }

    public void setStatusLastcheckTime(String statusLastcheckTime) {
        this.statusLastcheckTime = statusLastcheckTime;
    }

    @Basic
    @Column(name = "note", nullable = true, length = 200)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceStatusNews that = (DeviceStatusNews) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (productKey != null ? !productKey.equals(that.productKey) : that.productKey != null) return false;
        if (deviceKey != null ? !deviceKey.equals(that.deviceKey) : that.deviceKey != null) return false;
        if (deviceStatus != null ? !deviceStatus.equals(that.deviceStatus) : that.deviceStatus != null) return false;
        if (statusReturnTime != null ? !statusReturnTime.equals(that.statusReturnTime) : that.statusReturnTime != null)
            return false;
        if (statusLastcheckTime != null ? !statusLastcheckTime.equals(that.statusLastcheckTime) : that.statusLastcheckTime != null)
            return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (productKey != null ? productKey.hashCode() : 0);
        result = 31 * result + (deviceKey != null ? deviceKey.hashCode() : 0);
        result = 31 * result + (deviceStatus != null ? deviceStatus.hashCode() : 0);
        result = 31 * result + (statusReturnTime != null ? statusReturnTime.hashCode() : 0);
        result = 31 * result + (statusLastcheckTime != null ? statusLastcheckTime.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }
}
