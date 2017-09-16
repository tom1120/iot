package com.kito.k6.service;

import org.jeecgframework.core.common.service.CommonService;

import java.util.List;

/**
 * Created by zhaoyipc on 2017/9/15.
 */
public interface WifiBindK6PersonService extends CommonService{
    /**
     * 获取绑定mac地址白名单
     * @return
     */
    public List<String> getWifiBindListPerson();

}
