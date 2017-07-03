/*
package org.jeecgframework.test.quartz;*/
/**
 * Created by zhaoyipc on 2017/6/24.
 *//*


import org.jeecgframework.core.quartz.QuartzManager;
import org.jeecgframework.core.quartz.service.SchedulerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.Job;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

*/
/**
 * @author zhaoyi
 * @date 2017-06-2017/6/24-9:00
 *//*

@RunWith(SpringJUnit4ClassRunner.class)
//使用正式环境的配置文件测试
//@ContextConfiguration(locations = {"classpath*:spring-mvc-timeTask.xml","classpath*:spring-mvc-hibernate.xml"})
//使用测试环境的配置文件测试
@ContextConfiguration(locations = {"file:src/test/resource/spring-mvc-managerTimeTask.xml","file:src/test/resource/spring-mvc-hibernate.xml"})
@ActiveProfiles("production")
public class TestQuartzManager {
    private static Logger LOGGER = LoggerFactory.getLogger(Test.class);
    private static String JOB_GROUP_NAME = "zydGroup";
    private static String TRIGGER_GROUP_NAME = "zydTriggerGroup";
//    private static SchedulerFactory sf = new StdSchedulerFactory();
    @Autowired
    private QuartzManager quartzManager;

    @Test
    public void test() throws SchedulerException {//,Exception,ParseException
        String jobname="job1";
        String jobgroup="jobgroup1";
        String triggername="triggername1";
        String triggerGroup="triggergroupname1";
        String cron="";
        Class<? extends Job> c=null;
        try {
           c = (Class<? extends Job>) Class.forName("org.jeecgframework.core.quartz.myQuartzJobBean");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Map<String,Object> dataMap=new HashMap<String,Object>();
        dataMap.put("data","1");
        dataMap.put("gg","2");
//        quartzManager.addJob(jobname,jobgroup,triggername,triggerGroup,c,"0/1 * * * * ?",null);

    }

    private static Date parse(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {

            return format.parse(dateStr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
*/
