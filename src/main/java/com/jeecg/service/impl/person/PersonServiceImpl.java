package com.jeecg.service.impl.person;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.service.person.PersonServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("personService")
@Transactional
public class PersonServiceImpl extends CommonServiceImpl implements PersonServiceI {
	
}