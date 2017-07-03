/*
package org.jeecgframework.test.quartz;*/
/**
 * Created by zhaoyipc on 2017/6/24.
 *//*


import org.jeecgframework.core.quartz.pojo.QrtzJobDetails;
import org.jeecgframework.core.quartz.pojo.QrtzTriggers;
import org.jeecgframework.core.quartz.service.QrtzJobDetailsService;
import org.jeecgframework.core.quartz.service.QrtzTriggersService;
import org.jeecgframework.core.quartz.SchedulerManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.CronExpression;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.impl.triggers.CronTriggerImpl;
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
import java.util.Set;

*/
/**
 * @author zhaoyi
 * @date 2017-06-2017/6/24-9:00
 *//*

@RunWith(SpringJUnit4ClassRunner.class)
//使用正式环境的配置文件测试
//@ContextConfiguration(locations = {"classpath*:spring-mvc-timeTask.xml","classpath*:spring-mvc-hibernate.xml"})
//使用测试环境的配置文件测试
@ContextConfiguration(locations = {"file:src/test/resource/spring-mvc-dataBasetimeTask.xml","file:src/test/resource/spring-minidao.xml",
        "file:src/test/resource/spring-mvc-hibernate.xml"})
@ActiveProfiles("production")
public class TestSchedulerDataBaseManager {
    private static Logger LOGGER = LoggerFactory.getLogger(Test.class);
    private static String JOB_GROUP_NAME = "zydGroup";
    private static String TRIGGER_GROUP_NAME = "zydTriggerGroup";
    //    private static SchedulerFactory sf = new StdSchedulerFactory();
    @Autowired
    private SchedulerManager schedulerManager;
    @Autowired
    private QrtzJobDetailsService qrtzJobDetailsService;


    @Test
    public void test() throws SchedulerException {//,Exception,ParseException

        */
/**
         * 持久化此任务到数据库中
         *//*

//        schedulerService.schedule("0 45 10 * * ? *");

        */
/**
         * 获取当前数据库Job数据
         *//*

        List<QrtzJobDetails> qrtzJobDetailsList=qrtzJobDetailsService.findByQueryString("from QrtzJobDetails");

        for(QrtzJobDetails qrtzJobDetails:qrtzJobDetailsList){
            Class c=null;
            try {
                c=Class.forName(qrtzJobDetails.getJobClassName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            JobDetail jobDetail=JobBuilder.newJob(c).
                    withIdentity(qrtzJobDetails.getJobName(),qrtzJobDetails.getJobGroup()).
                    build();

            //获取JOB对应触发器
            Set<QrtzTriggers> qrtzTriggersSet=qrtzJobDetails.getTriggersSet();
            for(QrtzTriggers qrtzTriggers:qrtzTriggersSet){
                CronTriggerImpl cronTrigger=new CronTriggerImpl();
                try {
                    cronTrigger.setCronExpression(qrtzTriggers.getQrtzCronTriggers().getCronExpression());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                cronTrigger.setName(qrtzTriggers.getTriggerName());
                cronTrigger.setGroup(qrtzTriggers.getTriggerGroup());

                schedulerManager.schedule(cronTrigger,jobDetail);
            }

        }


    }


}
*/
