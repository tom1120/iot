package com.jeecg.service.impl.wifiprobe;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.service.wifiprobe.InteractiveDeviceServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("interactiveDeviceService")
@Transactional
public class InteractiveDeviceServiceImpl extends CommonServiceImpl implements InteractiveDeviceServiceI {
	
}