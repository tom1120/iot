package org.jeecgframework.core.quartz.service;
/**
 * Created by zhaoyipc on 2017/6/24.
 */


import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

/**
 * @author zhaoyi
 * @date 2017-06-2017/6/24-8:39*/


@Service("schedulerService")
@Transactional
public class SchedulerServiceImpl implements SchedulerService {
    private static final String NULLSTRING = null;
    private static final Date NULLDATE = null;

    @Autowired
    private Scheduler scheduler;
/*    @Autowired
    private JobDetail jobDetail;*/

    private JobDetail jobDetail;

    public JobDetail getJobDetail() {
        return jobDetail;
    }

    public void setJobDetail(JobDetail jobDetail) {
        this.jobDetail = jobDetail;
    }

    @Override
    public void schedule(String cronExpression) {
// System.out.print("222222222");
        schedule(NULLSTRING, cronExpression);
    }

    @Override
    public void schedule(String name, String cronExpression) {
        schedule(name, NULLSTRING, cronExpression);
    }

    @Override
    public void schedule(String name, String group, String cronExpression) {

        try {
            schedule(name, group, new CronExpression(cronExpression));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void schedule(CronExpression cronExpression) {
        schedule(NULLSTRING, cronExpression);
    }

    @Override
    public void schedule(String name, CronExpression cronExpression) {
        schedule(name, NULLSTRING, cronExpression);
    }

    @Override
    public void schedule(String name, String group, CronExpression cronExpression) {

        if (isValidExpression(cronExpression)) {

            if (name == null || name.trim().equals("")) {
                name = UUID.randomUUID().toString();
            }

            CronTriggerImpl trigger = new CronTriggerImpl();
            trigger.setCronExpression(cronExpression);

            TriggerKey triggerKey = new TriggerKey(name, group);

            trigger.setJobName(jobDetail.getKey().getName());
            trigger.setKey(triggerKey);

            try {
                scheduler.addJob(jobDetail, true);

                if (scheduler.checkExists(triggerKey)) {
                    scheduler.rescheduleJob(triggerKey, trigger);
                } else {
                    scheduler.scheduleJob(trigger);
                }
            } catch (SchedulerException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    @Override
    public void schedule(CronTriggerImpl cronTrigger){
        CronExpression cronExpression= null;
        try {
            cronExpression = new CronExpression(cronTrigger.getCronExpression());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String name=cronTrigger.getName();
        String group=cronTrigger.getGroup();
//        schedule(name, group, cronExpression);

        if (isValidExpression(cronExpression)) {

            if (name == null || name.trim().equals("")) {
                name = UUID.randomUUID().toString();
            }
            TriggerKey triggerKey = new TriggerKey(name, group);

            try {
//                scheduler.addJob(jobDetail, true);
                if(scheduler.checkExists(jobDetail.getKey())){
                    //移除job
//                    scheduler.deleteJob(jobDetail.getKey());
                    scheduler.addJob(jobDetail,true);
                }else{
                    scheduler.addJob(jobDetail, true);
                }


                if (scheduler.checkExists(triggerKey)) {
                    scheduler.rescheduleJob(triggerKey, cronTrigger);
                } else {
                    scheduler.scheduleJob(cronTrigger);
                }
            } catch (SchedulerException e) {
                throw new IllegalArgumentException(e);
            }
        }

    }


    @Override
    public void schedule(CronTriggerImpl cronTrigger, JobDetail jobDetail) {
        setJobDetail(jobDetail);
        schedule(cronTrigger);
    }

    @Override
    public void schedule(Date startTime) {
        schedule(startTime, NULLDATE);
    }

    @Override
    public void schedule(Date startTime, String group) {
        schedule(startTime, NULLDATE, group);
    }

    @Override
    public void schedule(String name, Date startTime) {
        schedule(name, startTime, NULLDATE);
    }

    @Override
    public void schedule(String name, Date startTime, String group) {
        schedule(name, startTime, NULLDATE, group);
    }

    @Override
    public void schedule(Date startTime, Date endTime) {
        schedule(startTime, endTime, 0);
    }

    @Override
    public void schedule(Date startTime, Date endTime, String group) {
        schedule(startTime, endTime, 0, group);
    }

    @Override
    public void schedule(String name, Date startTime, Date endTime) {
        schedule(name, startTime, endTime, 0);
    }

    @Override
    public void schedule(String name, Date startTime, Date endTime, String group) {
        schedule(name, startTime, endTime, 0, group);
    }

    @Override
    public void schedule(Date startTime, int repeatCount) {
        schedule(null, startTime, NULLDATE, 0);
    }

    @Override
    public void schedule(Date startTime, Date endTime, int repeatCount) {
        schedule(null, startTime, endTime, 0);
    }

    @Override
    public void schedule(Date startTime, Date endTime, int repeatCount, String group) {
        schedule(null, startTime, endTime, 0, group);
    }

    @Override
    public void schedule(String name, Date startTime, Date endTime, int repeatCount) {
        schedule(name, startTime, endTime, 0, 0L);
    }

    @Override
    public void schedule(String name, Date startTime, Date endTime, int repeatCount, String group) {
        schedule(name, startTime, endTime, 0, 0L, group);
    }

    @Override
    public void schedule(Date startTime, int repeatCount, long repeatInterval) {
        schedule(null, startTime, NULLDATE, repeatCount, repeatInterval);
    }

    @Override
    public void schedule(Date startTime, Date endTime, int repeatCount, long repeatInterval) {
        schedule(null, startTime, endTime, repeatCount, repeatInterval);
    }

    @Override
    public void schedule(Date startTime, Date endTime, int repeatCount, long repeatInterval, String group) {
        schedule(null, startTime, endTime, repeatCount, repeatInterval, group);
    }

    @Override
    public void schedule(String name, Date startTime, Date endTime, int repeatCount, long repeatInterval) {
        schedule(name, startTime, endTime, repeatCount, repeatInterval, NULLSTRING);
    }

    @Override
    public void schedule(String name, Date startTime, Date endTime, int repeatCount, long repeatInterval, String group) {

        if (this.isValidExpression(startTime)) {

            if (name == null || name.trim().equals("")) {
                name = UUID.randomUUID().toString();
            }

            TriggerKey triggerKey = new TriggerKey(name, group);

            SimpleTriggerImpl trigger = new SimpleTriggerImpl();
            trigger.setKey(triggerKey);
            trigger.setJobName(jobDetail.getKey().getName());

            trigger.setStartTime(startTime);
            trigger.setEndTime(endTime);
            trigger.setRepeatCount(repeatCount);
            trigger.setRepeatInterval(repeatInterval);

            try {
                scheduler.addJob(jobDetail, true);
                if (scheduler.checkExists(triggerKey)) {
                    scheduler.rescheduleJob(triggerKey, trigger);
                } else {
                    scheduler.scheduleJob(trigger);
                }
            } catch (SchedulerException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    @Override
    public void pauseTrigger(String triggerName) {
        pauseTrigger(triggerName, NULLSTRING);
    }

    @Override
    public boolean pauseTrigger(String triggerName, String group) {
        TriggerKey triggerKey=new TriggerKey(triggerName, group);
        return pauseTrigger(triggerKey);
    }

    @Override
    public boolean pauseTrigger(TriggerKey triggerKey) {
        boolean b=false;
        try {
            scheduler.pauseTrigger(triggerKey);// 停止触发器
            b=true;
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
        return b;
    }

    @Override
    public void resumeTrigger(String triggerName) {
        resumeTrigger(triggerName, NULLSTRING);
    }

    @Override
    public boolean resumeTrigger(String triggerName, String group) {
        TriggerKey triggerKey=new TriggerKey(triggerName, group);
        return resumeTrigger(triggerKey);
    }

    @Override
    public boolean resumeTrigger(TriggerKey triggerKey) {
        boolean b=false;
        try {
            scheduler.resumeTrigger(triggerKey);// 重启触发器
            b=true;
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
        return b;
    }


    @Override
    public boolean removeTrigger(String triggerName) {
        return removeTrigger(triggerName, NULLSTRING);
    }

    @Override
    public boolean removeTrigger(String triggerName, String group) {
        TriggerKey triggerKey = new TriggerKey(triggerName, group);
        return removeTrigger(triggerKey);

    }

    @Override
    public boolean removeTrigger(TriggerKey triggerKey) {
        boolean b=false;
        try {
            scheduler.pauseTrigger(triggerKey);// 停止触发器
            b= scheduler.unscheduleJob(triggerKey);// 移除触发器
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
        return b;
    }

    @Override
    public boolean deleteJob(JobKey jobKey) {
        boolean b=false;

        try {
            b=scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        return b;
    }

    @Override
    public boolean deleteJob(String jobName, String jobGroup) {
        boolean b=false;
        JobKey jobKey=new JobKey(jobName,jobGroup);
        b=deleteJob(jobKey);
        return b;
    }


    @Override
    public boolean pauseJob(String jobName, String jobGroup) {
        boolean b=false;
        JobKey jobKey=new JobKey(jobName,jobGroup);
        b=pauseJob(jobKey);

        return b;
    }

    @Override
    public boolean pauseJob(JobKey jobKey) {
        boolean b=false;

        try {
            scheduler.pauseJob(jobKey);
            b=true;
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        return b;
    }

    @Override
    public boolean resumeJob(String jobName, String jobGroup) {
        boolean b=false;
        b=resumeJob(new JobKey(jobName,jobGroup));
        return b;
    }

    @Override
    public boolean resumeJob(JobKey jobKey) {
        boolean b=false;
        try {
            scheduler.resumeJob(jobKey);
            b=true;
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public boolean triggeringJob(String jobName, String jobGroup) {
        boolean b=false;
        b=triggeringJob(new JobKey(jobName,jobGroup));
        return b;
    }

    @Override
    public boolean triggeringJob(JobKey jobKey) {
        boolean b=false;
        try {
            scheduler.triggerJob(jobKey);
            b=true;
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return b;
    }

    public boolean isValidExpression(final CronExpression cronExpression) {

        CronTriggerImpl trigger = new CronTriggerImpl();
        trigger.setCronExpression(cronExpression);

        Date date = trigger.computeFirstFireTime(null);

        return date != null && date.after(new Date());
    }

    public boolean isValidExpression(final Date startTime) {

        SimpleTriggerImpl trigger = new SimpleTriggerImpl();
        trigger.setStartTime(startTime);

        Date date = trigger.computeFirstFireTime(null);

        return date != null && date.after(new Date());
    }


}
