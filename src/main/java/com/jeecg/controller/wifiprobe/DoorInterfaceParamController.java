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

import com.jeecg.entity.wifiprobe.DoorInterfaceParamEntity;
import com.jeecg.service.wifiprobe.DoorInterfaceParamServiceI;

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
 * @Description: 门禁接口参数表
 * @author zhangdaihao
 * @date 2017-09-21 15:21:22
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/doorInterfaceParamController")
public class DoorInterfaceParamController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DoorInterfaceParamController.class);

	@Autowired
	private DoorInterfaceParamServiceI doorInterfaceParamService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 门禁接口参数表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/wifiprobe/doorInterfaceParamList");
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
	public void datagrid(DoorInterfaceParamEntity doorInterfaceParam,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(DoorInterfaceParamEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, doorInterfaceParam, request.getParameterMap());
		this.doorInterfaceParamService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除门禁接口参数表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(DoorInterfaceParamEntity doorInterfaceParam, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		doorInterfaceParam = systemService.getEntity(DoorInterfaceParamEntity.class, doorInterfaceParam.getId());
		message = "门禁接口参数表删除成功";
		doorInterfaceParamService.delete(doorInterfaceParam);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加门禁接口参数表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(DoorInterfaceParamEntity doorInterfaceParam, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(doorInterfaceParam.getId())) {
			message = "门禁接口参数表更新成功";
			DoorInterfaceParamEntity t = doorInterfaceParamService.get(DoorInterfaceParamEntity.class, doorInterfaceParam.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(doorInterfaceParam, t);
				doorInterfaceParamService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "门禁接口参数表更新失败";
			}
		} else {
			message = "门禁接口参数表添加成功";
			doorInterfaceParamService.save(doorInterfaceParam);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 门禁接口参数表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(DoorInterfaceParamEntity doorInterfaceParam, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(doorInterfaceParam.getId())) {
			doorInterfaceParam = doorInterfaceParamService.getEntity(DoorInterfaceParamEntity.class, doorInterfaceParam.getId());
			req.setAttribute("doorInterfaceParamPage", doorInterfaceParam);
		}
		return new ModelAndView("com/jeecg/wifiprobe/doorInterfaceParam");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<DoorInterfaceParamEntity> list() {
		List<DoorInterfaceParamEntity> listDoorInterfaceParams=doorInterfaceParamService.getList(DoorInterfaceParamEntity.class);
		return listDoorInterfaceParams;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		DoorInterfaceParamEntity task = doorInterfaceParamService.get(DoorInterfaceParamEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody DoorInterfaceParamEntity doorInterfaceParam, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<DoorInterfaceParamEntity>> failures = validator.validate(doorInterfaceParam);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		doorInterfaceParamService.save(doorInterfaceParam);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = doorInterfaceParam.getId();
		URI uri = uriBuilder.path("/rest/doorInterfaceParamController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody DoorInterfaceParamEntity doorInterfaceParam) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<DoorInterfaceParamEntity>> failures = validator.validate(doorInterfaceParam);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		doorInterfaceParamService.saveOrUpdate(doorInterfaceParam);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		doorInterfaceParamService.deleteEntityById(DoorInterfaceParamEntity.class, id);
	}
}
