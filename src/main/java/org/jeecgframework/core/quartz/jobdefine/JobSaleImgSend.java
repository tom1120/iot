package org.jeecgframework.core.quartz.jobdefine;/**
 * Created by zhaoyipc on 2017/7/7.
 */

import org.jeecgframework.core.quartz.jobservice.FineReportSendService;
import org.jeecgframework.core.quartz.jobservice.FineReportSendServiceImpl;
import org.jeecgframework.core.quartz.jobservice.SimpleService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author zhaoyi
 * @date 2017-07-2017/7/7-10:39
 */
public class JobSaleImgSend extends QuartzJobBean{
    @Override
    protected void executeInternal(JobExecutionContext jobexecutioncontext) throws JobExecutionException {
        Trigger trigger = jobexecutioncontext.getTrigger();
        String triggerName = trigger.getKey().getName();
        FineReportSendServiceImpl fineReportSendService = getApplicationContext(jobexecutioncontext).getBean("fineReportSendServiceImpl",
                FineReportSendServiceImpl.class);
        fineReportSendService.executeSaleImgSend();
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
