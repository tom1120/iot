package org.jeecgframework.core.quartz;/**
 * Created by zhaoyipc on 2017/6/24.
 */

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author zhaoyi
 * @date 2017-06-2017/6/24-8:44
 */
public class MyQuartzJobBean extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobexecutioncontext) throws JobExecutionException {
        Trigger trigger = jobexecutioncontext.getTrigger();
        String triggerName = trigger.getKey().getName();
        SimpleService simpleService = getApplicationContext(jobexecutioncontext).getBean("simpleService",
                SimpleService.class);
        simpleService.testMethod(triggerName);
    }
    private ApplicationContext getApplicationContext(final JobExecutionContext jobexecutioncontext) {
        try {
            return (ApplicationContext) jobexecutioncontext.getScheduler().getContext().get("applicationContextKey");
        } catch (SchedulerException e) {
// logger.error("jobexecutioncontext.getScheduler().getContext() error!", e);
            throw new RuntimeException(e);
        }
    }
}
