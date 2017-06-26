package test.testCron;/**
 * Created by zhaoyipc on 2017/6/20.
 */

import net.sf.json.JSONObject;
import org.quartz.*;
import org.quartz.spi.MutableTrigger;
import org.quartz.spi.OperableTrigger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author zhaoyi
 * @date 2017-06-2017/6/20-12:48
 */
public class CronTest {

    public static JSONObject getMoreTimesByCronExpression(String cronExpression,int times) throws ParseException {
        JSONObject jsonObject=new JSONObject();
        //低版本的写法，高版本这个类变为接口
//        CronTrigger cronTriggerImpl = new CronTrigger();
//        cronTriggerImpl.setCronExpression(cronExpression);//这里写要准备猜测的cron表达式

        // 触发器
        TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
        // 触发器名,触发器组
        // 触发器时间设定
        triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression));
        // 创建Trigger对象
        OperableTrigger trigger = (OperableTrigger) triggerBuilder.build();

        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.add(Calendar.YEAR, 2);//把统计的区间段设置为从现在到2年后的今天（主要是为了方法通用考虑，如那些1个月跑一次的任务，如果时间段设置的较短就不足20条)
        List<Date> dates = TriggerUtils.computeFireTimesBetween(trigger, null, now, calendar.getTime());//这个是重点，一行代码搞定~~
//        System.out.println(dates.size());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(int i =0;i < dates.size();i ++){
            if(i >times){//这个是提示的日期个数
                break;
            }
//            System.out.println(dateFormat.format(dates.get(i)));
            jsonObject.put(i,dateFormat.format(dates.get(i)));
        }

        return jsonObject;

    }

    public static void main(String[] args){
//        String cronExpression="0 0 15 5 * ?";
        String cronExpression="0 0/2 0 0 1,2,3 ? *";
        try {
            JSONObject jsonObject=getMoreTimesByCronExpression(cronExpression,4);
            System.out.println("jsonObject.toString() = " + jsonObject.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
