/*
package org.jeecgframework.core.timer;

import org.jeecgframework.web.system.pojo.base.TSTimeTaskEntity;
import org.jeecgframework.web.system.service.TimeTaskServiceI;
import org.quartz.CronTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
//import org.springframework.scheduling.quartz.CronTriggerBean;

*/
/**
 * 在原有功能的基础上面增加数据库的读取
 * @author JueYue
 * @date 2013-9-22
 * @version 1.0
 *//*

public class DataBaseCronTriggerBean162 extends CronTriggerFactoryBean{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TimeTaskServiceI timeTaskService;
	*/
/**
	 * 读取数据库更新文件
	 *//*

	public void afterPropertiesSet() {
		super.afterPropertiesSet();
		CronTrigger cronTrigger=this.getObject();
		TSTimeTaskEntity task = timeTaskService.findUniqueByProperty
				(TSTimeTaskEntity.class,"taskId",cronTrigger.getName());
		if(task!=null&&task.getIsEffect().equals("1")
				&&!task.getCronExpression().equals(cronTrigger.getCronExpression())){
			//使用数据库定义配置覆盖spring-mvc-timetask.xml定时配置
			this.setCronExpression(task.getCronExpression());
			//更新配置文件spring-mvc-timetask.xml
			DynamicTask.updateSpringMvcTaskXML(cronTrigger,task.getCronExpression());
		}
	}

}
*/
