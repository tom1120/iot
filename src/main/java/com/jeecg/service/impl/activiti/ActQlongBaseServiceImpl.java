package com.jeecg.service.impl.activiti;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.service.activiti.ActQlongBaseServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("actQlongBaseService")
@Transactional
public class ActQlongBaseServiceImpl extends CommonServiceImpl implements ActQlongBaseServiceI {
	
}