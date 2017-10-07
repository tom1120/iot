package com.kito.k6.service.impl;/**
 * Created by zhaoyipc on 2017/9/15.
 */

import com.kito.k6.service.WifiBindK6PersonService;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.DynamicDBUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhaoyi
 * @date 2017-09-2017/9/15-11:02
 */
@Service("wifiBindK6PersonService")
public class WifiBindK6PersonServiceImpl extends CommonServiceImpl implements WifiBindK6PersonService{

    @Override
    public List<String> getWifiBindListPerson() {
        List<String> list=new ArrayList<String>();
//        String sql="select staffid,staffcode,name,mobile_wifi_mac as mobileWifiMac,mobile_wifi_mac_flag as mobileWifiMacFlag from hrstaffinfo " +
//                " where mobile_wifi_mac_flag=1 and mobile_wifi_mac is not null";
//        String sql="select mobile_wifi_mac as mobileWifiMac from hrstaffinfo " +
//                " where mobile_wifi_mac_flag=1 and mobile_wifi_mac is not null";

        String sql="select mobile_wifi_mac+'$'+name as mobileWifiMac from hrstaffinfo " +
                " where mobile_wifi_mac_flag=1 and mobile_wifi_mac is not null";

        List<Map<String,Object>> list1=DynamicDBUtil.findList("sqlserver",sql,null);

        for(Map m:list1){
            list.add((String) m.get("mobileWifiMac"));
        }

        return list;
    }
}
