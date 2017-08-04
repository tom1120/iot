package com.jeecg.service.impl.aliyuniot;/**
 * Created by zhaoyipc on 2017/8/3.
 */

import com.jeecg.service.aliyuniot.DeviceStatusNewsServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhaoyi
 * @date 2017-08-2017/8/3-8:56
 */
@Service("deviceStatusNewsService")
@Transactional
public class DeviceStatusNewsServiceImpl extends CommonServiceImpl implements DeviceStatusNewsServiceI {
}
