package com.jeecg.service.impl.aliyuniot;/**
 * Created by zhaoyipc on 2017/8/5.
 */

import com.jeecg.service.aliyuniot.DeviceInitbindParamsServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhaoyi
 * @date 2017-08-2017/8/5-8:38
 */
@Service("deviceInitbinParamsService")
@Transactional
public class DeviceInitbinParamsServiceImpl extends CommonServiceImpl implements DeviceInitbindParamsServiceI {
}
