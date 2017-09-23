package com.jeecg.service.impl.wifiprobe;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.service.wifiprobe.DeviceActionServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("deviceActionService")
@Transactional
public class DeviceActionServiceImpl extends CommonServiceImpl implements DeviceActionServiceI {
	
}