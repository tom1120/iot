package com.jeecg.service.impl.wifiprobe;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.service.wifiprobe.SignalDoorInteractiveServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("signalDoorInteractiveService")
@Transactional
public class SignalDoorInteractiveServiceImpl extends CommonServiceImpl implements SignalDoorInteractiveServiceI {
	
}