package org.jeecgframework.core.quartz.service;/**
 * Created by zhaoyipc on 2017/6/26.
 */

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhaoyi
 * @date 2017-06-2017/6/26-21:40
 */
@Service("qrtzTriggersService")
@Transactional
public class QrtzTriggersServiceImpl extends CommonServiceImpl implements QrtzTriggersService{

}
