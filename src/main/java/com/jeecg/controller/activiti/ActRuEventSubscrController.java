package com.jeecg.controller.activiti;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RuntimeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.jeecg.entity.activiti.ActRuEventSubscrEntity;
import com.jeecg.service.activiti.ActRuEventSubscrServiceI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**   
 * @Title: Controller
 * @Description: 流程事件
 * @author zhangdaihao
 * @date 2017-06-12 11:33:40
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/actRuEventSubscrController")
public class ActRuEventSubscrController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ActRuEventSubscrController.class);

	@Autowired
	private ActRuEventSubscrServiceI actRuEventSubscrService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	@Autowired
	RuntimeService runtimeService;
	


	/**
	 * 流程事件列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/activiti/actRuEventSubscrList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(ActRuEventSubscrEntity actRuEventSubscr,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ActRuEventSubscrEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, actRuEventSubscr, request.getParameterMap());
		this.actRuEventSubscrService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除流程事件
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ActRuEventSubscrEntity actRuEventSubscr, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		actRuEventSubscr = systemService.getEntity(ActRuEventSubscrEntity.class, actRuEventSubscr.getId());
		message = "流程事件删除成功";
		actRuEventSubscrService.delete(actRuEventSubscr);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加流程事件
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ActRuEventSubscrEntity actRuEventSubscr, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(actRuEventSubscr.getId())) {
			message = "流程事件更新成功";
			ActRuEventSubscrEntity t = actRuEventSubscrService.get(ActRuEventSubscrEntity.class, actRuEventSubscr.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(actRuEventSubscr, t);
				actRuEventSubscrService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "流程事件更新失败";
			}
		} else {
			message = "流程事件添加成功";
			actRuEventSubscrService.save(actRuEventSubscr);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 流程事件列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ActRuEventSubscrEntity actRuEventSubscr, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(actRuEventSubscr.getId())) {
			actRuEventSubscr = actRuEventSubscrService.getEntity(ActRuEventSubscrEntity.class, actRuEventSubscr.getId());
			req.setAttribute("actRuEventSubscrPage", actRuEventSubscr);
		}
		return new ModelAndView("com/jeecg/activiti/actRuEventSubscr");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ActRuEventSubscrEntity> list() {
		List<ActRuEventSubscrEntity> listActRuEventSubscrs=actRuEventSubscrService.getList(ActRuEventSubscrEntity.class);
		return listActRuEventSubscrs;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ActRuEventSubscrEntity task = actRuEventSubscrService.get(ActRuEventSubscrEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ActRuEventSubscrEntity actRuEventSubscr, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ActRuEventSubscrEntity>> failures = validator.validate(actRuEventSubscr);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		actRuEventSubscrService.save(actRuEventSubscr);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = actRuEventSubscr.getId();
		URI uri = uriBuilder.path("/rest/actRuEventSubscrController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ActRuEventSubscrEntity actRuEventSubscr) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ActRuEventSubscrEntity>> failures = validator.validate(actRuEventSubscr);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		actRuEventSubscrService.saveOrUpdate(actRuEventSubscr);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		actRuEventSubscrService.deleteEntityById(ActRuEventSubscrEntity.class, id);
	}


	@RequestMapping(params = "activeEvent")
	public AjaxJson activeEvent(HttpServletRequest request, HttpServletResponse response, ActRuEventSubscrEntity actRuEventSubscrEntity
								, @RequestParam("eventType") String eventType,@RequestParam("eventName") String eventName){
		AjaxJson ajaxJson=new AjaxJson();
		ajaxJson.setMsg("激活失败");
		ajaxJson.setSuccess(false);

		if(eventType.equals("signal")){
//			runtimeService.createExecutionQuery().signalEventSubscriptionName(eventName);
			runtimeService.signalEventReceived(eventName);
			ajaxJson.setMsg("激活"+eventType+":"+eventName+"成功！");
			ajaxJson.setSuccess(true);

		}else if(eventType.equals("message")){

		}else {

		}

		return  ajaxJson;
	}


}
