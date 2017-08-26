package org.jeecgframework.core.quartz.jobdefine;

import org.jeecgframework.core.quartz.jobservice.FineReportSendService;
import org.jeecgframework.core.quartz.jobservice.FineReportSendServiceImpl;
import org.jeecgframework.core.quartz.jobservice.SimpleService;
import org.jeecgframework.core.quartz.jobservice.UpdateSendServiceImpl;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by Administrator on 2017-8-23.
 */

public class JobUpdateSend extends QuartzJobBean {

    private ApplicationContext getApplicationContext(final JobExecutionContext jobexecutioncontext) {
        try {
            return (ApplicationContext) jobexecutioncontext.getScheduler().getContext().get("applicationContextKey");
        } catch (SchedulerException e) {
// logger.error("jobexecutioncontext.getScheduler().getContext() error!", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        UpdateSendServiceImpl updateSendService = getApplicationContext(jobExecutionContext).getBean("updateSendServiceImpl",
                UpdateSendServiceImpl.class);
        updateSendService.executeUpdate();
    }
}





