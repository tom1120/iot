package com.jeecg.controller.wifiprobe;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

import com.jeecg.entity.wifiprobe.SignalDoorInteractiveEntity;
import com.jeecg.service.wifiprobe.SignalDoorInteractiveServiceI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
 * @Description: 信号-门禁-设备行为
 * @author zhangdaihao
 * @date 2017-09-22 08:25:43
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/signalDoorInteractiveController")
public class SignalDoorInteractiveController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SignalDoorInteractiveController.class);

	@Autowired
	private SignalDoorInteractiveServiceI signalDoorInteractiveService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 信号-门禁-设备行为列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/wifiprobe/signalDoorInteractiveList");
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
	public void datagrid(SignalDoorInteractiveEntity signalDoorInteractive,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SignalDoorInteractiveEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, signalDoorInteractive, request.getParameterMap());
		this.signalDoorInteractiveService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除信号-门禁-设备行为
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(SignalDoorInteractiveEntity signalDoorInteractive, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		signalDoorInteractive = systemService.getEntity(SignalDoorInteractiveEntity.class, signalDoorInteractive.getId());
		message = "信号-门禁-设备行为删除成功";
		signalDoorInteractiveService.delete(signalDoorInteractive);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加信号-门禁-设备行为
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(SignalDoorInteractiveEntity signalDoorInteractive, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(signalDoorInteractive.getId())) {
			message = "信号-门禁-设备行为更新成功";
			SignalDoorInteractiveEntity t = signalDoorInteractiveService.get(SignalDoorInteractiveEntity.class, signalDoorInteractive.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(signalDoorInteractive, t);
				signalDoorInteractiveService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "信号-门禁-设备行为更新失败";
			}
		} else {
			message = "信号-门禁-设备行为添加成功";
			signalDoorInteractiveService.save(signalDoorInteractive);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 信号-门禁-设备行为列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(SignalDoorInteractiveEntity signalDoorInteractive, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(signalDoorInteractive.getId())) {
			signalDoorInteractive = signalDoorInteractiveService.getEntity(SignalDoorInteractiveEntity.class, signalDoorInteractive.getId());
			req.setAttribute("signalDoorInteractivePage", signalDoorInteractive);
		}
		return new ModelAndView("com/jeecg/wifiprobe/signalDoorInteractive");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<SignalDoorInteractiveEntity> list() {
		List<SignalDoorInteractiveEntity> listSignalDoorInteractives=signalDoorInteractiveService.getList(SignalDoorInteractiveEntity.class);
		return listSignalDoorInteractives;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		SignalDoorInteractiveEntity task = signalDoorInteractiveService.get(SignalDoorInteractiveEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody SignalDoorInteractiveEntity signalDoorInteractive, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<SignalDoorInteractiveEntity>> failures = validator.validate(signalDoorInteractive);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		signalDoorInteractiveService.save(signalDoorInteractive);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = signalDoorInteractive.getId();
		URI uri = uriBuilder.path("/rest/signalDoorInteractiveController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody SignalDoorInteractiveEntity signalDoorInteractive) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<SignalDoorInteractiveEntity>> failures = validator.validate(signalDoorInteractive);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		signalDoorInteractiveService.saveOrUpdate(signalDoorInteractive);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		signalDoorInteractiveService.deleteEntityById(SignalDoorInteractiveEntity.class, id);
	}
}
