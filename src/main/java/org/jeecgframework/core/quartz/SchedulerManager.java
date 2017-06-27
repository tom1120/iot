package org.jeecgframework.core.quartz;/**
 * Created by zhaoyipc on 2017/6/26.
 */

import org.jeecgframework.web.system.service.UserService;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author zhaoyi
 * @date 2017-06-2017/6/26-14:57
 */
@Service("schedulerManager")
public class SchedulerManager {
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private SchedulerService schedulerService;

/*
    //spring注入List<JobDetail>
    @Autowired
//    @Qualifier("jobDetails") 加入这个反而无法找到
    private List<JobDetail> jobDetails;
    public List<JobDetail> getJobDetails() {
        return jobDetails;
    }
*/


    //改为读取数据数据注入
    private List<JobDetail> jobDetails;

    public List<JobDetail> getJobDetails() {
        return jobDetails;
    }

    public void setJobDetails(List<JobDetail> jobDetails) {
        this.jobDetails = jobDetails;
    }

    public SchedulerManager(List<JobDetail> jobDetails) {
        this.jobDetails = jobDetails;
    }

    public SchedulerManager() {
    }

    /**
     * 对job持久化调度
     * @param name
     * @param group
     * @param cronExpression
     * @param jobDetail
     */
    public void schedule(String name, String group, CronExpression cronExpression,JobDetail jobDetail) {
            schedulerService.setJobDetail(jobDetail);
            schedulerService.schedule(name,group,cronExpression);
    }

    /**
     * 对job持久化调度
     * @param cronTrigger
     * @param jobDetail
     */
    public void schedule(CronTriggerImpl cronTrigger,JobDetail jobDetail){
        schedulerService.setJobDetail(jobDetail);
        schedulerService.schedule(cronTrigger);
    }


    private boolean isValidExpression(final CronExpression cronExpression) {

        CronTriggerImpl trigger = new CronTriggerImpl();
        trigger.setCronExpression(cronExpression);

        Date date = trigger.computeFirstFireTime(null);

        return date != null && date.after(new Date());
    }

    private boolean isValidExpression(final Date startTime) {

        SimpleTriggerImpl trigger = new SimpleTriggerImpl();
        trigger.setStartTime(startTime);

        Date date = trigger.computeFirstFireTime(null);

        return date != null && date.after(new Date());
    }


}
