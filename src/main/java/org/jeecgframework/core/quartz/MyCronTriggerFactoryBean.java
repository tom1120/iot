package org.jeecgframework.core.quartz;/**
 * Created by zhaoyipc on 2017/6/26.
 */

import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailAwareTrigger;

/**
 * @author zhaoyi
 * @date 2017-06-2017/6/26-13:35
 */
public class MyCronTriggerFactoryBean extends CronTriggerFactoryBean{
    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        // Remove the JobDetail element
        getJobDataMap().remove(JobDetailAwareTrigger.JOB_DETAIL_KEY);
    }
}
