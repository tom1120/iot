package org.jeecgframework.core.quartz.service;

import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;

import java.util.Date;

/**
 * 动态调度服务接口
 * Created by zhaoyipc on 2017/6/24.
 */
public interface SchedulerService {

    void setJobDetail(JobDetail jobDetail);
    JobDetail getJobDetail();

    /**
     * 根据quartz cron express 调试任务
     * @param cronExpression Quartz Cron 表达式，如 "0/10 * * ? * * *"等
     */
    void schedule(String cronExpression);

    /**
     * 根据 Quartz Cron Expression 调试任务
     *
     * @param name Quartz CronTrigger名称
     * @param cronExpression Quartz Cron 表达式，如 "0/10 * * ? * * *"等
     */
    void schedule(String name, String cronExpression);

    /**
     * 根据 Quartz Cron Expression 调试任务
     *
     * @param name Quartz CronTrigger名称
     * @param group Quartz CronTrigger组
     * @param cronExpression Quartz Cron 表达式，如 "0/10 * * ? * * *"等
     */
    void schedule(String name, String group, String cronExpression);

    /**
     * 根据 Quartz Cron Expression 调试任务
     *
     * @param cronExpression Quartz CronExpression
     */
    void schedule(CronExpression cronExpression);

    /**
     * 根据 Quartz Cron Expression 调试任务
     *
     * @param name Quartz CronTrigger名称
     * @param cronExpression Quartz CronExpression
     */
    void schedule(String name, CronExpression cronExpression);

    /**
     * 根据 Quartz Cron Expression 调试任务
     *
     * @param name Quartz CronTrigger名称
     * @param group Quartz CronTrigger组
     * @param cronExpression Quartz CronExpression
     */
    void schedule(String name, String group, CronExpression cronExpression);

    /**
     * CronTriggerImpl设置cronTrigger触发器参数
     * @param cronTrigger
     */
    void schedule(CronTriggerImpl cronTrigger);



    /**
     * CronTriggerImpl设置cronTrigger触发器参数
     * @param cronTrigger
     */
    void schedule(CronTriggerImpl cronTrigger,JobDetail jobDetail);

    /**
     * 在startTime时执行调试一次
     *
     * @param startTime 调度开始时间
     */
    void schedule(Date startTime);

    void schedule(Date startTime, String group);

    /**
     * 在startTime时执行调试一次
     *
     * @param name Quartz SimpleTrigger 名称
     * @param startTime 调度开始时间
     */
    void schedule(String name, Date startTime);

    void schedule(String name, Date startTime, String group);

    /**
     * 在startTime时执行调试，endTime结束执行调度
     *
     * @param startTime 调度开始时间
     * @param endTime 调度结束时间
     */
    void schedule(Date startTime, Date endTime);

    void schedule(Date startTime, Date endTime, String group);

    /**
     * 在startTime时执行调试，endTime结束执行调度
     *
     * @param name Quartz SimpleTrigger 名称
     * @param startTime 调度开始时间
     * @param endTime 调度结束时间
     */
    void schedule(String name, Date startTime, Date endTime);

    void schedule(String name, Date startTime, Date endTime, String group);

    /**
     * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次
     *
     * @param startTime 调度开始时间
     * @param repeatCount 重复执行次数
     */
    void schedule(Date startTime, int repeatCount);

    /**
     * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次
     *
     * @param startTime 调度开始时间
     * @param endTime 调度结束时间
     * @param repeatCount 重复执行次数
     */
    void schedule(Date startTime, Date endTime, int repeatCount);

    void schedule(Date startTime, Date endTime, int repeatCount, String group);

    /**
     * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次
     *
     * @param name Quartz SimpleTrigger 名称
     * @param startTime 调度开始时间
     * @param endTime 调度结束时间
     * @param repeatCount 重复执行次数
     */
    void schedule(String name, Date startTime, Date endTime, int repeatCount);

    void schedule(String name, Date startTime, Date endTime, int repeatCount, String group);

    /**
     * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次，每隔repeatInterval秒执行一次
     *
     * @param startTime 调度开始时间
     * @param repeatCount 重复执行次数
     * @param repeatInterval 执行时间隔间
     */
    void schedule(Date startTime, int repeatCount, long repeatInterval);

    /**
     * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次，每隔repeatInterval秒执行一次
     *
     * @param startTime 调度开始时间
     * @param endTime 调度结束时间
     * @param repeatCount 重复执行次数
     * @param repeatInterval 执行时间隔间
     */
    void schedule(Date startTime, Date endTime, int repeatCount, long repeatInterval);

    void schedule(Date startTime, Date endTime, int repeatCount, long repeatInterval, String group);

    /**
     * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次，每隔repeatInterval秒执行一次
     *
     * @param name Quartz SimpleTrigger 名称
     * @param startTime 调度开始时间
     * @param endTime 调度结束时间
     * @param repeatCount 重复执行次数
     * @param repeatInterval 执行时间隔间
     */
    void schedule(String name, Date startTime, Date endTime, int repeatCount, long repeatInterval);

    void schedule(String name, Date startTime, Date endTime, int repeatCount, long repeatInterval, String group);

    /**
     * 暂停触发器
     *
     * @param triggerName 触发器名称
     */
    void pauseTrigger(String triggerName);

    /**
     * 暂停触发器
     *
     * @param triggerName 触发器名称
     * @param group 触发器组
     */
    boolean pauseTrigger(String triggerName, String group);

    /**
     * 暂停触发器
     * @param triggerKey
     */
    boolean pauseTrigger(TriggerKey triggerKey);


    /**
     * 恢复触发器
     *
     * @param triggerName 触发器名称
     */
    void resumeTrigger(String triggerName);

    /**
     * 恢复触发器
     *
     * @param triggerName 触发器名称
     * @param group 触发器组
     */
    boolean resumeTrigger(String triggerName, String group);

    /**
     * 恢复触发器
     *
     * @param triggerKey
     */
    boolean resumeTrigger(TriggerKey triggerKey);


    /**
     * 删除触发器
     *
     * @param triggerName 触发器名称
     * @return
     */
    boolean removeTrigger(String triggerName);
    /**
     * 删除触发器
     *
     * @param triggerName 触发器名称
     * @param group 触发器组
     * @return
     */
    boolean removeTrigger(String triggerName, String group);

    /**
     * 删除触发器
     *@param triggerKey 触发器封装类
     * @return
     */
    boolean removeTrigger(TriggerKey triggerKey);



    /**
     * 删除job及相关触发器
     * @param jobKey
     * @return
     */
    boolean deleteJob(JobKey jobKey);

    /**
     * 删除job及相关的触发器
     * @param jobName
     * @param jobGroup
     * @return
     */
    boolean deleteJob(String jobName,String jobGroup);

    /**
     * 暂停该任务的所有当前正在触发的任务触发器
     * @param jobName
     * @param jobGroup
     * @return
     */
    boolean pauseJob(String jobName,String jobGroup);

    /**
     * 暂停该任务的所有当前正在触发的任务触发器
     * @param jobKey
     * @return
     */
    boolean pauseJob(JobKey jobKey);

    /**
     * 恢复任务到触发状态
     * @param jobName
     * @param jobGroup
     * @return
     */
    boolean resumeJob(String jobName,String jobGroup);

    /**
     * 恢复任务到触发状态
     * @param jobKey
     * @return
     */
    boolean resumeJob(JobKey jobKey);

    /**
     * 立即运行任务
     * @param jobName
     * @param jobGroup
     * @return
     */
    boolean triggeringJob(String jobName,String jobGroup);

    /**
     * 立即运行任务
     * @param jobKey
     * @return
     */
    boolean triggeringJob(JobKey jobKey);

    /**
     * 更新cron表达式
     * @param cronTrigger
     * @param jobKey
     */
    boolean updateCronExpression(CronTriggerImpl cronTrigger,JobKey jobKey);

}
