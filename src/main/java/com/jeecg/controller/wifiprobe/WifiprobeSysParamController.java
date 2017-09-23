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

import com.jeecg.entity.wifiprobe.WifiprobeSysParamEntity;
import com.jeecg.service.wifiprobe.WifiprobeSysParamServiceI;

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
 * @Description: wifi探针参数设置
 * @author zhangdaihao
 * @date 2017-09-21 10:10:17
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/wifiprobeSysParamController")
public class WifiprobeSysParamController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WifiprobeSysParamController.class);

	@Autowired
	private WifiprobeSysParamServiceI wifiprobeSysParamService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * wifi探针参数设置列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/wifiprobe/wifiprobeSysParamList");
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
	public void datagrid(WifiprobeSysParamEntity wifiprobeSysParam,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WifiprobeSysParamEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, wifiprobeSysParam, request.getParameterMap());
		this.wifiprobeSysParamService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除wifi探针参数设置
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(WifiprobeSysParamEntity wifiprobeSysParam, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		wifiprobeSysParam = systemService.getEntity(WifiprobeSysParamEntity.class, wifiprobeSysParam.getId());
		message = "wifi探针参数设置删除成功";
		wifiprobeSysParamService.delete(wifiprobeSysParam);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 刷新wifi探针参数设置
	 *
	 * @return
	 */
	@RequestMapping(params = "refresh")
	@ResponseBody
	public AjaxJson refresh(WifiprobeSysParamEntity wifiprobeSysParam, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		wifiprobeSysParam = systemService.getEntity(WifiprobeSysParamEntity.class, wifiprobeSysParam.getId());
		message = "wifi探针参数设置刷新成功";
//		wifiprobeSysParamService.delete(wifiprobeSysParam);
		WifiprobeController wifiprobeController=new WifiprobeController();
		wifiprobeController.refreshCache(wifiprobeSysParam);
		systemService.addLog(message, Globals.Log_Leavel_INFO, Globals.Log_Leavel_INFO);

		j.setMsg(message);
		return j;
	}




	/**
	 * 添加wifi探针参数设置
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(WifiprobeSysParamEntity wifiprobeSysParam, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(wifiprobeSysParam.getId())) {
			message = "wifi探针参数设置更新成功";
			WifiprobeSysParamEntity t = wifiprobeSysParamService.get(WifiprobeSysParamEntity.class, wifiprobeSysParam.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(wifiprobeSysParam, t);
				wifiprobeSysParamService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "wifi探针参数设置更新失败";
			}
		} else {
			message = "wifi探针参数设置添加成功";
			wifiprobeSysParamService.save(wifiprobeSysParam);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * wifi探针参数设置列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(WifiprobeSysParamEntity wifiprobeSysParam, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(wifiprobeSysParam.getId())) {
			wifiprobeSysParam = wifiprobeSysParamService.getEntity(WifiprobeSysParamEntity.class, wifiprobeSysParam.getId());
			req.setAttribute("wifiprobeSysParamPage", wifiprobeSysParam);
		}
		return new ModelAndView("com/jeecg/wifiprobe/wifiprobeSysParam");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<WifiprobeSysParamEntity> list() {
		List<WifiprobeSysParamEntity> listWifiprobeSysParams=wifiprobeSysParamService.getList(WifiprobeSysParamEntity.class);
		return listWifiprobeSysParams;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		WifiprobeSysParamEntity task = wifiprobeSysParamService.get(WifiprobeSysParamEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody WifiprobeSysParamEntity wifiprobeSysParam, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<WifiprobeSysParamEntity>> failures = validator.validate(wifiprobeSysParam);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		wifiprobeSysParamService.save(wifiprobeSysParam);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = wifiprobeSysParam.getId();
		URI uri = uriBuilder.path("/rest/wifiprobeSysParamController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody WifiprobeSysParamEntity wifiprobeSysParam) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<WifiprobeSysParamEntity>> failures = validator.validate(wifiprobeSysParam);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		wifiprobeSysParamService.saveOrUpdate(wifiprobeSysParam);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		wifiprobeSysParamService.deleteEntityById(WifiprobeSysParamEntity.class, id);
	}
}
