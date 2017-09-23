package com.jeecg.service.impl.wifiprobe;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.service.wifiprobe.DoorInterfaceParamServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("doorInterfaceParamService")
@Transactional
public class DoorInterfaceParamServiceImpl extends CommonServiceImpl implements DoorInterfaceParamServiceI {
	
}