package org.jeecgframework.core.quartz.jobservice;/**
 * Created by zhaoyipc on 2017/6/24.
 */

import org.jeecgframework.core.util.DateUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author zhaoyi
 * @date 2017-06-2017/6/24-8:48
 */
@Service("simpleService")//执行定时任务业务
public class SimpleService implements Serializable{
    public void testMethod(String triggerName) {
// 这里执行定时调度业务
        System.out.println("1动态执行了"+ triggerName);
        System.out.println(DateUtils.getDate());
    }

    public void testMethod2(String triggerName) {
// 这里执行定时调度业务
        System.out.println("2动态执行了");
        System.out.println(DateUtils.getDate());
    }
}
