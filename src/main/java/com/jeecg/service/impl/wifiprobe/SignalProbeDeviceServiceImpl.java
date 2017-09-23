package com.jeecg.service.impl.wifiprobe;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.service.wifiprobe.SignalProbeDeviceServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("signalProbeDeviceService")
@Transactional
public class SignalProbeDeviceServiceImpl extends CommonServiceImpl implements SignalProbeDeviceServiceI {
	
}