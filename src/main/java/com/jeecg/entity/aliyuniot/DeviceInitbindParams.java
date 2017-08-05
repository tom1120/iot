package com.jeecg.entity.aliyuniot;/**
 * Created by zhaoyipc on 2017/8/5.
 */

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author zhaoyi
 * @date 2017-08-2017/8/5-8:34
 */
@Entity
@Table(name = "device_initbind_params", schema = "jeecg")
public class DeviceInitbindParams implements Serializable{
    private String id;
    private String authUrl;
    private String osNameVersion;
    private String deviceSn;
    private String wifiMac;
    private String productKey;
    private String deviceKey;
    private String deviceSecret;
    private String note;

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator",strategy = "uuid")
    @Column(name = "id", nullable = false, length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Basic
    @Column(name="os_name_version",nullable = false,length = 50)
    public String getOsNameVersion() {
        return osNameVersion;
    }

    public void setOsNameVersion(String osNameVersion) {
        this.osNameVersion = osNameVersion;
    }

    @Basic
    @Column(name ="auth_url",nullable = false)
    public String getAuthUrl() {
        return authUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }

    @Basic
    @Column(name = "device_sn", nullable = false, length = 50)
    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    @Basic
    @Column(name = "wifi_mac", nullable = true, length = 30)
    public String getWifiMac() {
        return wifiMac;
    }

    public void setWifiMac(String wifiMac) {
        this.wifiMac = wifiMac;
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
    @Column(name = "device_secret",nullable = false,length = 50)
    public String getDeviceSecret() {
        return deviceSecret;
    }

    public void setDeviceSecret(String deviceSecret) {
        this.deviceSecret = deviceSecret;
    }

    @Basic
    @Column(name = "note", nullable = true, length = 200)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


}
