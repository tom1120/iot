package com.jeecg.service.impl.wifiprobe;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.service.wifiprobe.DeviceProductActionInstructionServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("deviceProductActionInstructionService")
@Transactional
public class DeviceProductActionInstructionServiceImpl extends CommonServiceImpl implements DeviceProductActionInstructionServiceI {
	
}