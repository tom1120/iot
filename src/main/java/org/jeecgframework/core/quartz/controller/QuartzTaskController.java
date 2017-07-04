package org.jeecgframework.core.quartz.controller;/**
 * Created by zhaoyipc on 2017/6/27.
 */

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.quartz.QrtzTriggersList;
import org.jeecgframework.core.quartz.pojo.*;
import org.jeecgframework.core.quartz.service.SchedulerService;
import org.jeecgframework.core.quartz.service.QrtzJobDetailsService;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.activiti.controller.ActivitiController;
import org.jeecgframework.web.system.service.SystemService;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import test.testCron.CronTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

/**
 * @author zhaoyi
 * @date 2017-06-2017/6/27-16:35
 */
@Controller
@RequestMapping("/quartzTaskController")
public class QuartzTaskController {

    @Autowired
    private QrtzJobDetailsService qrtzJobDetailsService;
    @Autowired
    private SchedulerService schedulerService;
    @Autowired
    private SystemService systemService;

	@RequestMapping(params = "testHello1")
	public void testHello(HttpServletRequest servletRequest){
		System.out.println(" hello world! ");
	}

/**
 * 定时任务管理列表 页面跳转
 *
 * @return
 */

	@RequestMapping(params = "quartzTask")
	public ModelAndView quartzTask(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/quartz/quartzTaskList");
	}

/**
 * 更新cron表达式，cron在线编辑器编辑更新
 * @param cronExpression
 * @return
 */

	@RequestMapping(params = "updateCronExpression")
	@ResponseBody
	public AjaxJson updateCronExpression(@RequestParam("cronExpression") String cronExpression,
                                         @RequestParam("jobName") String jobName,
                                         @RequestParam("jobGroup") String jobGroup,
                                         @RequestParam("scheName") String scheName,
                                         @RequestParam("triggerName") String triggerName,
                                         @RequestParam("triggerGroup") String triggerGroup,
                                         @RequestParam("jobClass") String jobClass,


                                         HttpServletRequest request){
//		String s=request.getParameter("cronExpression");
		AjaxJson ajaxJson=new AjaxJson();
		ajaxJson.setMsg("更新失败！");
		ajaxJson.setSuccess(false);

/*		if(cronExpression!=null&&!cronExpression.equals("")){
//			TSTimeTaskEntity timeTask = timeTaskService.get(TSTimeTaskEntity.class, cronid);
            QrtzJobDetailsPK qrtzJobDetailsPK=new QrtzJobDetailsPK();
            qrtzJobDetailsPK.setJobGroup(jobGroup);
            qrtzJobDetailsPK.setJobName(jobName);
            //区分分布式调度
            qrtzJobDetailsPK.setSchedName(scheName);
            QrtzJobDetails qrtzJobDetails=qrtzJobDetailsService.get(QrtzJobDetails.class,qrtzJobDetailsPK);
            //去除空格
			cronExpression=cronExpression.trim();
            //更新对应触发器的cron表达式
            Set<QrtzTriggers> qrtzTriggersSet=qrtzJobDetails.getTriggersSet();
            for(QrtzTriggers qrtzTriggers:qrtzTriggersSet){
                if(qrtzTriggers.getTriggerName().equals(triggerName)
                        &&qrtzTriggers.getTriggerGroup().equals(triggerGroup)){
                    QrtzCronTriggers qrtzCronTriggers=qrtzTriggers.getQrtzCronTriggers();
                    qrtzCronTriggers.setCronExpression(cronExpression);
                }
            }

            CronTriggerImpl cronTrigger=new CronTriggerImpl();
            Class aClass=null;
            try {
                aClass=Class.forName(jobClass);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            JobDetail jobDetail= JobBuilder.newJob(aClass).withIdentity(qrtzJobDetails.getId().getJobName(),qrtzJobDetails.getId().getJobGroup()).build();

            boolean isUpdate=false;
            //开启调度，API自动更新任务
            try{
                schedulerService.schedule(cronTrigger,jobDetail);
                isUpdate=true;

            }catch (Exception e){
//                e.printStackTrace();
            }



            //更新任务
//            qrtzJobDetailsService.updateEntitie(qrtzJobDetails);

			ajaxJson.setMsg(isUpdate?"定时任务管理更新成功":"定时任务管理更新失败");
			ajaxJson.setSuccess(true);
		}*/
		return ajaxJson;
	}


/**
 * 解析cron最近几次的表达式*/


	@RequestMapping(params = "preview")
	public void previewExpression(@RequestParam("CronExpression") String cronExpression,HttpServletResponse response) throws ParseException {
		JSONObject jsonObject=new JSONObject();
		cronExpression=cronExpression.trim();
		jsonObject= CronTest.getMoreTimesByCronExpression(cronExpression,4);

		ActivitiController.responseDatagrid(response,jsonObject);
	}





/**
 * easyui AJAX请求数据
 *
 * @param request
 * @param response
 * @param dataGrid
 * */



	@RequestMapping(params = "datagrid")
	public void datagrid(QrtzJobDetails qrtzJobDetails,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
//		CriteriaQuery cq = new CriteriaQuery(QrtzJobDetails.class, dataGrid);
		//查询条件组装器
//		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, qrtzJobDetails, request.getParameterMap());
//		this.qrtzJobDetailsService.getDataGridReturn(cq, true);
//		TagUtil.datagrid(response, dataGrid);

		String searchfield=request.getParameter("searchfield");


		List<QrtzJobDetails> list = null;
		if(searchfield!=null&&!searchfield.equals("")){
			String searchfieldValue=request.getParameter(searchfield);
			if(!searchfieldValue.equals("")){
				list=qrtzJobDetailsService.findHql("from QrtzJobDetails where "+searchfield+" like ?",searchfieldValue);

			}else {
				list=qrtzJobDetailsService.findByQueryString("from QrtzJobDetails");
			}

		}else{
//			qrtzJobDetailsService.findByQueryString("from TSNotice");

			list=qrtzJobDetailsService.findByQueryString("from QrtzJobDetails");
		}



		StringBuffer rows = new StringBuffer();
		int i = 0;
		for(QrtzJobDetails pi : list){
			i++;
			String id=pi.getSchedName()+"$"+pi.getJobName()+"$"+pi.getJobGroup();
			rows.append("{'id':'"+id//无主键改为序号
					+"','schedName':'"+pi.getSchedName()
					+"','jobName':'"+pi.getJobName()
					+"','jobGroup':'"+pi.getJobGroup()
					+"','jobClassName':'"+pi.getJobClassName()
					+"','isDurable':'"+pi.getIsDurable()
					+"','isNonconcurrent':'"+pi.getIsNonconcurrent()
					+"','isUpdateData':'"+pi.getIsUpdateData()
					+"','requestsRecovery':'"+pi.getRequestsRecovery()
					+"','jobData':'"+pi.getJobData()
					+"'},");
		}
		String rowStr = StringUtils.substringBeforeLast(rows.toString(), ",");

//		JSONObject jObject = JSONObject.fromObject("{'total':"+query.count()+",'rows':["+rowStr+"]}");
		JSONObject jObject = JSONObject.fromObject("{'total':"+list.size()+",'rows':["+rowStr+"]}");
		ActivitiController.responseDatagrid(response, jObject);


	}


/**
 * 删除定时任务管理
 *
 * @return*/


	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(@RequestParam("id") String id, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();

		String[] ids=id.split("\\$");

		boolean b=false;
		b=schedulerService.deleteJob(ids[1],ids[2]);

//        qrtzJobDetails = systemService.getEntity(QrtzJobDetails.class,qrtzJobDetailsPK);
//		qrtzJobDetailsService.delete(qrtzJobDetails);
		if(b){
			message = "定时任务删除成功！";
		}else{
			message="定时任务失败！";
		}

		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}

	/**
	 * 暂停任务
	 * @param id
	 * @param request
     * @return
     */
	@RequestMapping(params = "pauseJob")
	@ResponseBody
	public AjaxJson pauseJob(@RequestParam("id") String id,HttpServletRequest request){
		AjaxJson json=new AjaxJson();
		json.setSuccess(false);
		String message="任务暂停失败!";
		boolean b=false;

		String[] ids=id.split("\\$");

		b=schedulerService.pauseJob(ids[1],ids[2]);
		if(b){
			message="任务暂停成功！";
			json.setSuccess(true);
		}

		json.setMsg(message);

		return json;
	}

	/**
	 * 恢复任务
	 * @param id
	 * @param request
     * @return
     */
	@RequestMapping(params = "resumeJob")
	@ResponseBody
	public AjaxJson resumeJob(@RequestParam("id") String id,HttpServletRequest request){
		AjaxJson json=new AjaxJson();
		json.setSuccess(false);
		String message="任务恢复失败!";
		boolean b=false;

		String[] ids=id.split("\\$");

		b=schedulerService.resumeJob(ids[1],ids[2]);
		if(b){
			message="任务恢复成功！";
			json.setSuccess(true);
		}

		json.setMsg(message);

		return json;
	}


	/**
	 * 立即运行任务
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "triggeringJob")
	@ResponseBody
	public AjaxJson triggeringJob(@RequestParam("id") String id,HttpServletRequest request){
		AjaxJson json=new AjaxJson();
		json.setSuccess(false);
		String message="立即运行失败!";
		boolean b=false;

		String[] ids=id.split("\\$");

		b=schedulerService.triggeringJob(ids[1],ids[2]);
		if(b){
			message="立即运行成功！";
			json.setSuccess(true);
		}

		json.setMsg(message);

		return json;
	}



/**
 * 保存任务及关联触发器、触发表达式
 *
 * @return*/


	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(QrtzJobDetails qrtzJobDetails, QrtzTriggersList qrtzTriggersList, HttpServletRequest request) {
		AjaxJson j=new AjaxJson();
		String message = "更新失败！";
		j.setSuccess(false);
		try {
			String jobClassName=qrtzJobDetails.getJobClassName();

			JobDetail jobDetail=JobBuilder.newJob((Class<? extends Job>) Class.forName(jobClassName)).withIdentity(qrtzJobDetails.getSchedName())
					.withIdentity(qrtzJobDetails.getJobName(),qrtzJobDetails.getJobGroup())
					.withDescription(qrtzJobDetails.getDescription()).storeDurably().build();
			//必须要持久化，否则会报异常
			jobDetail.isDurable();

			//编辑前任务触发器列表
			String[] strings={qrtzJobDetails.getSchedName(),qrtzJobDetails.getJobName(),qrtzJobDetails.getJobGroup()};
			List<QrtzTriggers> beforeQrtzTriggeres=qrtzJobDetailsService.findHql("from QrtzTriggers where schedName=? and jobName=? and jobGroup=?",strings);

			//编辑后任务触发器列表
			List<QrtzTriggers> qrtzTriggerses =  qrtzTriggersList.getCronTaskList();

			//查出非当前任务的所有触发器来比较，避免修改出现串到别的触发器上
			List<QrtzTriggers> exceptSelfQrtzTriggeres=qrtzJobDetailsService.findHql("from QrtzTriggers where schedName!=? or jobName!=? or jobGroup!=?",strings);

			//判断现有job添加触发器是否存在非现有job其他触发器中,如存在则更新失败，否则继续更新过程

			for(QrtzTriggers qrtzTriggers:qrtzTriggerses){
				for(QrtzTriggers eqrtzTriggers:exceptSelfQrtzTriggeres){
					if(qrtzTriggers.getSchedName().equals(eqrtzTriggers.getSchedName())
							&&qrtzTriggers.getTriggerName().equals(eqrtzTriggers.getTriggerName())
							&&qrtzTriggers.getTriggerGroup().equals(eqrtzTriggers.getTriggerGroup())){
						j.setMsg("更新失败，触发器："+qrtzTriggers.getTriggerGroup()+"-"+qrtzTriggers.getTriggerName()+"已经在job："+
								eqrtzTriggers.getJobGroup()+"-"+eqrtzTriggers.getJobName()+"中定义！");
						j.setSuccess(false);
						return j;
					}


				}

			}

			//删除原有的触发器列表
			for(QrtzTriggers qrtzTriggers:beforeQrtzTriggeres) {
				schedulerService.removeTrigger(qrtzTriggers.getTriggerName(), qrtzTriggers.getTriggerGroup());
			}
			//添加job及关联触发器
			for(QrtzTriggers qrtzTriggers:qrtzTriggerses){
				CronTriggerImpl cronTrigger=new CronTriggerImpl();
				cronTrigger.setJobGroup(qrtzJobDetails.getJobGroup());
				cronTrigger.setJobName(qrtzJobDetails.getJobName());
				if(qrtzTriggers.getPriority()!=null){
					cronTrigger.setPriority(qrtzTriggers.getPriority());
				}
				cronTrigger.setName(qrtzTriggers.getTriggerName());
				cronTrigger.setGroup(qrtzTriggers.getTriggerGroup());
				cronTrigger.setDescription(qrtzTriggers.getDescription());
				cronTrigger.setCronExpression(qrtzTriggers.getQrtzCronTriggers().getCronExpression());
				//添加更新job
				schedulerService.schedule(cronTrigger,jobDetail);
			}


			j.setSuccess(true);
			message="更新成功！";
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch (Exception e){
			e.printStackTrace();
		}

		j.setMsg(message);
		return j;
	}

	@RequestMapping(params = "pauseTrigger")
	@ResponseBody
	public AjaxJson pauseTrigger(@RequestParam("ids") String ids,HttpServletRequest request){
		AjaxJson ajaxJson=new AjaxJson();
		ajaxJson.setSuccess(false);
		ajaxJson.setMsg("暂停触发器失败");

		String[] strings=ids.split(",");

		for(String s:strings){
			String[] s1=s.split("\\$");
			boolean b=schedulerService.pauseTrigger(s1[1],s1[2]);
			if(b){
				ajaxJson.setMsg("暂停触发器成功");
				ajaxJson.setSuccess(true);
			}
		}


		return ajaxJson;
	}

	@RequestMapping(params = "resumeTrigger")
	@ResponseBody
	public AjaxJson resumeTrigger(@RequestParam("ids") String ids,HttpServletRequest request){
		AjaxJson ajaxJson=new AjaxJson();
		ajaxJson.setSuccess(false);
		ajaxJson.setMsg("恢复触发器失败");

		String[] strings=ids.split(",");

		for(String s:strings){
			String[] s1=s.split("\\$");
			boolean b=schedulerService.resumeTrigger(s1[1],s1[2]);
			if(b){
				ajaxJson.setMsg("恢复触发器成功");
				ajaxJson.setSuccess(true);
			}
		}


		return ajaxJson;
	}


/**
 * 定时任务管理列表页面跳转
 *
 * @return*/


	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(QrtzJobDetails qrtzJobDetails, HttpServletRequest req) {
		String id=req.getParameter("id");
		QrtzJobDetailsPK qrtzJobDetailsPK= new QrtzJobDetailsPK();
		if(StringUtil.isNotEmpty(id)){//编辑或者查看
			String[] ids=id.split("\\$");
			qrtzJobDetailsPK.setSchedName(ids[0]);
			qrtzJobDetailsPK.setJobName(ids[1]);
			qrtzJobDetailsPK.setJobGroup(ids[2]);
		}else{//添加
			qrtzJobDetailsPK.setSchedName(qrtzJobDetails.getSchedName());
			qrtzJobDetailsPK.setJobName(qrtzJobDetails.getJobGroup());
			qrtzJobDetailsPK.setJobGroup(qrtzJobDetails.getJobGroup());
		}


		if (StringUtil.isNotEmpty(qrtzJobDetailsPK)) {
            qrtzJobDetails = qrtzJobDetailsService.getEntity(QrtzJobDetails.class, qrtzJobDetailsPK);
			req.setAttribute("timeTaskPage", qrtzJobDetails);
			req.setAttribute("id",id);
		}
		return new ModelAndView("com/jeecg/quartz/quartzTask");
	}



	/**
	 * 加载任务触发器列表页面
	 *
	 * @return
	 */
	@RequestMapping(params = "cronTaskList")
	public ModelAndView jeecgOrderProductList(QrtzJobDetails qrtzJobDetails, HttpServletRequest req) {
		String id=req.getParameter("id");
		String[] ids=id.split("\\$");

		if (StringUtil.isNotEmpty(id)) {
//			List<QrtzTriggers> jeecgOrderProductEntityList =  qrtzJobDetailsService.findByProperty(QrtzTriggers.class, "jobName", qrtzJobDetails.getJobName());

			String[] parames={ids[0],ids[1],ids[2]};

			List<QrtzTriggers> jeecgOrderProductEntityList =  qrtzJobDetailsService.findHql("from QrtzTriggers where schedName=? and jobName=? and jobGroup=?",parames);
			req.setAttribute("cronTaskList", jeecgOrderProductEntityList);
		}
		return new ModelAndView("com/jeecg/quartz/cronTaskList");
	}





/**
 * 更新任务时间使之生效*/


	@RequestMapping(params = "updateTime")
	@ResponseBody
	public AjaxJson updateTime(QrtzJobDetails qrtzJobDetails, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
        QrtzJobDetailsPK qrtzJobDetailsPK= new QrtzJobDetailsPK();
        qrtzJobDetailsPK.setSchedName(qrtzJobDetails.getSchedName());
        qrtzJobDetailsPK.setJobGroup(qrtzJobDetails.getJobGroup());
        qrtzJobDetailsPK.setJobGroup(qrtzJobDetails.getJobGroup());
        qrtzJobDetails = qrtzJobDetailsService.get(QrtzJobDetails.class, qrtzJobDetailsPK);



        CronTriggerImpl cronTrigger=new CronTriggerImpl();
        Class aClass=null;
        try {
            aClass=Class.forName(qrtzJobDetails.getJobClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        JobDetail jobDetail= JobBuilder.newJob(aClass).withIdentity(qrtzJobDetails.getJobName(),qrtzJobDetails.getJobGroup()).build();

        boolean isUpdate=false;
        //开启调度，API自动更新任务
        try{
            schedulerService.schedule(cronTrigger,jobDetail);
            isUpdate=true;

        }catch (Exception e){
//                e.printStackTrace();
        }

		j.setMsg(isUpdate?"定时任务管理更新成功":"定时任务管理更新失败");
		return j;
	}

/**
 * 启动或者停止任务*/


	@RequestMapping(params = "startOrStopTask")
	@ResponseBody
	public AjaxJson startOrStopTask(QrtzJobDetails qrtzJobDetails, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
        boolean isSuccess=false;
/*		boolean isStart = timeTask.getIsStart().equals("1");
		timeTask = timeTaskService.get(TSTimeTaskEntity.class, timeTask.getId());
		boolean isSuccess = false;
		try {
			isSuccess = dynamicTask.startOrStop(timeTask.getTaskId() ,isStart);
		} catch (Exception e) {
			j.setMsg(isSuccess?"定时任务管理更新成功":"定时任务管理更新失败");
		}
		if(isSuccess){
			timeTask.setIsStart(isStart?"1":"0");
			timeTaskService.updateEntitie(timeTask);
			systemService.addLog((isStart?"开启任务":"停止任务")+timeTask.getTaskId(),
					Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}*/
		j.setMsg(isSuccess?"定时任务管理更新成功":"定时任务管理更新失败");
		return j;
	}






}
