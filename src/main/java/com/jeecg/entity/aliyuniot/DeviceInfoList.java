package com.jeecg.entity.aliyuniot;/**
 * Created by zhaoyipc on 2017/7/15.
 */

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaoyi
 * @date 2017-07-2017/7/15-8:11
 */
public class DeviceInfoList implements Serializable {
    private List<Deviceinfo> deviceInfoList;

    public List<Deviceinfo> getDeviceInfoList() {
        return deviceInfoList;
    }

    public void setDeviceInfoList(List<Deviceinfo> deviceInfoList) {
        this.deviceInfoList = deviceInfoList;
    }
}
