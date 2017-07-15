package com.jeecg.service.impl.aliyuniot;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.service.aliyuniot.ProductInfoServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("productInfoService")
@Transactional
public class ProductInfoServiceImpl extends CommonServiceImpl implements ProductInfoServiceI {
	
}