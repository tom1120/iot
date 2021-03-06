package org.jeecgframework.core.timer;

import java.text.ParseException;

import org.jeecgframework.web.system.pojo.base.TSTimeTaskEntity;
import org.jeecgframework.web.system.service.TimeTaskServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerBean;
/**
 * 在原有功能的基础上面增加数据库的读取
 * @author JueYue
 * @date 2013-9-22
 * @version 1.0
 */
public class DataBaseCronTriggerBean extends CronTriggerBean{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TimeTaskServiceI timeTaskService;
	/**
	 * 读取数据库更新文件
	 */
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
		TSTimeTaskEntity task = timeTaskService.findUniqueByProperty
				(TSTimeTaskEntity.class,"taskId",this.getName());
		if(task!=null&&task.getIsEffect().equals("1")
				&&!task.getCronExpression().equals(this.getCronExpression())){
			try {
				//使用数据库定义配置覆盖spring-mvc-timetask.xml定时配置
				this.setCronExpression(task.getCronExpression());
			} catch (ParseException e) {
				// TODO 异常必须被处理
				e.printStackTrace();
			}
			//更新配置文件spring-mvc-timetask.xml
			DynamicTask.updateSpringMvcTaskXML(this,task.getCronExpression());
		}
	}

}
