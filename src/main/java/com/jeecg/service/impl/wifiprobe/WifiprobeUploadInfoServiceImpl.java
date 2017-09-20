package com.jeecg.service.impl.wifiprobe;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.service.wifiprobe.WifiprobeUploadInfoServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("wifiprobeUploadInfoService")
@Transactional
public class WifiprobeUploadInfoServiceImpl extends CommonServiceImpl implements WifiprobeUploadInfoServiceI {
	
}