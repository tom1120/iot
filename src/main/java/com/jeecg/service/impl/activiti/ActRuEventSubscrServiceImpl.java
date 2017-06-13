package com.jeecg.service.impl.activiti;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.service.activiti.ActRuEventSubscrServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("actRuEventSubscrService")
@Transactional
public class ActRuEventSubscrServiceImpl extends CommonServiceImpl implements ActRuEventSubscrServiceI {
	
}