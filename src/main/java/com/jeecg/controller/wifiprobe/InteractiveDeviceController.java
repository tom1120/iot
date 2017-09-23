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

import com.jeecg.entity.wifiprobe.InteractiveDeviceEntity;
import com.jeecg.service.wifiprobe.InteractiveDeviceServiceI;

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
 * @Description: 互动设备表
 * @author zhangdaihao
 * @date 2017-09-21 15:21:58
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/interactiveDeviceController")
public class InteractiveDeviceController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(InteractiveDeviceController.class);

	@Autowired
	private InteractiveDeviceServiceI interactiveDeviceService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 互动设备表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/wifiprobe/interactiveDeviceList");
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
	public void datagrid(InteractiveDeviceEntity interactiveDevice,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(InteractiveDeviceEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, interactiveDevice, request.getParameterMap());
		this.interactiveDeviceService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除互动设备表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(InteractiveDeviceEntity interactiveDevice, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		interactiveDevice = systemService.getEntity(InteractiveDeviceEntity.class, interactiveDevice.getId());
		message = "互动设备表删除成功";
		interactiveDeviceService.delete(interactiveDevice);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加互动设备表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(InteractiveDeviceEntity interactiveDevice, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(interactiveDevice.getId())) {
			message = "互动设备表更新成功";
			InteractiveDeviceEntity t = interactiveDeviceService.get(InteractiveDeviceEntity.class, interactiveDevice.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(interactiveDevice, t);
				interactiveDeviceService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "互动设备表更新失败";
			}
		} else {
			message = "互动设备表添加成功";
			interactiveDeviceService.save(interactiveDevice);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 互动设备表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(InteractiveDeviceEntity interactiveDevice, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(interactiveDevice.getId())) {
			interactiveDevice = interactiveDeviceService.getEntity(InteractiveDeviceEntity.class, interactiveDevice.getId());
			req.setAttribute("interactiveDevicePage", interactiveDevice);
		}
		return new ModelAndView("com/jeecg/wifiprobe/interactiveDevice");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<InteractiveDeviceEntity> list() {
		List<InteractiveDeviceEntity> listInteractiveDevices=interactiveDeviceService.getList(InteractiveDeviceEntity.class);
		return listInteractiveDevices;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		InteractiveDeviceEntity task = interactiveDeviceService.get(InteractiveDeviceEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody InteractiveDeviceEntity interactiveDevice, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<InteractiveDeviceEntity>> failures = validator.validate(interactiveDevice);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		interactiveDeviceService.save(interactiveDevice);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = interactiveDevice.getId();
		URI uri = uriBuilder.path("/rest/interactiveDeviceController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody InteractiveDeviceEntity interactiveDevice) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<InteractiveDeviceEntity>> failures = validator.validate(interactiveDevice);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		interactiveDeviceService.saveOrUpdate(interactiveDevice);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		interactiveDeviceService.deleteEntityById(InteractiveDeviceEntity.class, id);
	}
}
