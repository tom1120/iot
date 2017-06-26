package org.jeecgframework.test.quartz;/**
 * Created by zhaoyipc on 2017/6/24.
 */

import org.jeecgframework.core.quartz.JobDetailList;
import org.jeecgframework.core.quartz.SchedulerManager;
import org.jeecgframework.core.quartz.SchedulerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author zhaoyi
 * @date 2017-06-2017/6/24-9:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
//使用正式环境的配置文件测试
//@ContextConfiguration(locations = {"classpath*:spring-mvc-timeTask.xml","classpath*:spring-mvc-hibernate.xml"})
//使用测试环境的配置文件测试
@ContextConfiguration(locations = {"file:src/test/resource/spring-mvc-timeTask.xml","file:src/test/resource/spring-mvc-hibernate.xml"})
@ActiveProfiles("production")
public class TestSchedulerManager {
    private static Logger LOGGER = LoggerFactory.getLogger(Test.class);
    private static String JOB_GROUP_NAME = "zydGroup";
    private static String TRIGGER_GROUP_NAME = "zydTriggerGroup";
    //    private static SchedulerFactory sf = new StdSchedulerFactory();
    @Autowired
    private SchedulerManager schedulerManager;


    @Test
    public void test() throws SchedulerException {//,Exception,ParseException
//schedule ("", Date startTime, Date endTime,int repeatCount, long repeatInterval, String group)
//        Date startTime = parse("2017-06-26 10:30:00");
//        Date endTime = parse("2017-06-26 15:15:00");
// schedulerService.resumeTrigger("f64eee37-62f4-473f-b5b9-d7a76d86c443");

        /**
         * 持久化此任务到数据库中
         */
//        schedulerService.schedule("0 45 10 * * ? *");
        String name="test1";
        String group="test1";
        String name1="test2";
        String group1="test2";
        CronExpression cronExpression=null;
        CronExpression cronExpression1=null;

        try {
            cronExpression= new CronExpression("0 45 10 * * ? *");
            cronExpression1= new CronExpression("0 11 10 * * ? *");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 任务名，任务组，任务执行类
        List<JobDetail> jobDetails= schedulerManager.getJobDetails();

        for(JobDetail jobDetail:jobDetails){

//            JobDetail jobDetail= JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
            if(jobDetail.getKey().getName().equals("jobDetail0")){
                schedulerManager.schedule(name,group,cronExpression,jobDetail);
            }else{
                schedulerManager.schedule(name1,group1,cronExpression1,jobDetail);
            }

        }



//        schedulerService.schedule("0fa05274-1c9b-49e5-b2ac-b43f90451226","0/3 42 11 * * ? *");
//        schedulerService.pauseTrigger("0fa05274-1c9b-49e5-b2ac-b43f90451226");
//        schedulerService.resumeTrigger("0fa05274-1c9b-49e5-b2ac-b43f90451226");




// schedulerService.schedule("f64eee37-62f4-473f-b5b9-d7a76d86c443", "0 45 09 * * ? *");
//schedulerService.pauseTrigger("f64eee37-62f4-473f-b5b9-d7a76d86c443");
//schedulerService.resumeTrigger("0fdb7134-9701-494c-9879-bbf2e30120b8");
// schedulerService.schedule("0 17 16 * * ? *");
//schedulerService.schedule(startTime);
// schedulerService.pauseTrigger("f4a610a7-5b43-4738-949c-150eec96fb62");
// schedulerService.schedule(startTime, endTime,5,5);
// schedulerService.schedule("ZYD", startTime, endTime, 1, 10);
// schedulerService.schedule(startTime);
// schedulerService.removeTrigdger("f4a610a7-5b43-4738-949c-150eec96fb62");
//        int repeatCount = 10;
//        long repeatInterval = 2;
// String name="b870cf9b-c313-4fd6-87ad-e99de7580290";

// schedulerService.schedule(name, startTime, endTime, repeatCount, repeatInterval);


/* schedulerService.schedule("0/10 * * ? * * *");


// 2014-08-19 16:33:00开始执行调度
schedulerService.schedule(startTime);

// 2014-08-19 16:33:00开始执行调度，2014-08-22 21:10:00结束执行调试
schedulerService.schedule(startTime, endTime);

// 2014-08-19 16:33:00开始执行调度，执行5次结束
schedulerService.schedule(startTime, 5);*/

// 2014-08-19 16:33:00开始执行调度，每隔20秒执行一次，执行5次结束
// schedulerService.schedule(startTime, 5, 20);

    }





/*

try {
LOGGER.info("33333333333");
Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
LOGGER.info("Scheduler starting up...");
System.out.print("4444444444");
scheduler.start();
} catch (Exception exception) {

System.out.print(exception);
}


schedulerService.schedule("0/2 * * ? * * *");
Date startTime = parse("2015-08-05 10:15:00");
Date endTime = parse("2015-08-05 15:15:00");
int repeatCount=10;
long repeatInterval=2;
// String name="b870cf9b-c313-4fd6-87ad-e99de7580290";

// schedulerService.schedule(name, startTime, endTime, repeatCount, repeatInterval);



schedulerService.schedule("0/10 * * ? * * *");







// 2014-08-19 16:33:00开始执行调度
schedulerService.schedule(startTime);

// 2014-08-19 16:33:00开始执行调度，2014-08-22 21:10:00结束执行调试
schedulerService.schedule(startTime, endTime);

// 2014-08-19 16:33:00开始执行调度，执行5次结束
schedulerService.schedule(startTime, 5);

// 2014-08-19 16:33:00开始执行调度，每隔20秒执行一次，执行5次结束
schedulerService.schedule(startTime, 5, 20);


*/

// schedulerService.schedule("quartz1", "ZYD", "0/2 * * ? * * *");



    private static Date parse(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {

            return format.parse(dateStr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
