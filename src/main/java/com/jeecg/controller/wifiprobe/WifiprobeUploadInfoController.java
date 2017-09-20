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

import com.jeecg.entity.wifiprobe.WifiprobeUploadInfoEntity;
import com.jeecg.service.wifiprobe.WifiprobeUploadInfoServiceI;

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
 * @Description: 上报wifi探针信息
 * @author zhangdaihao
 * @date 2017-09-19 17:54:37
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/wifiprobeUploadInfoController")
public class WifiprobeUploadInfoController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WifiprobeUploadInfoController.class);

	@Autowired
	private WifiprobeUploadInfoServiceI wifiprobeUploadInfoService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 上报wifi探针信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/wifiprobe/wifiprobeUploadInfoList");
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
	public void datagrid(WifiprobeUploadInfoEntity wifiprobeUploadInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WifiprobeUploadInfoEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, wifiprobeUploadInfo, request.getParameterMap());
		this.wifiprobeUploadInfoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除上报wifi探针信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(WifiprobeUploadInfoEntity wifiprobeUploadInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		wifiprobeUploadInfo = systemService.getEntity(WifiprobeUploadInfoEntity.class, wifiprobeUploadInfo.getId());
		message = "上报wifi探针信息删除成功";
		wifiprobeUploadInfoService.delete(wifiprobeUploadInfo);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加上报wifi探针信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(WifiprobeUploadInfoEntity wifiprobeUploadInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(wifiprobeUploadInfo.getId())) {
			message = "上报wifi探针信息更新成功";
			WifiprobeUploadInfoEntity t = wifiprobeUploadInfoService.get(WifiprobeUploadInfoEntity.class, wifiprobeUploadInfo.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(wifiprobeUploadInfo, t);
				wifiprobeUploadInfoService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "上报wifi探针信息更新失败";
			}
		} else {
			message = "上报wifi探针信息添加成功";
			wifiprobeUploadInfoService.save(wifiprobeUploadInfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 上报wifi探针信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(WifiprobeUploadInfoEntity wifiprobeUploadInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(wifiprobeUploadInfo.getId())) {
			wifiprobeUploadInfo = wifiprobeUploadInfoService.getEntity(WifiprobeUploadInfoEntity.class, wifiprobeUploadInfo.getId());
			req.setAttribute("wifiprobeUploadInfoPage", wifiprobeUploadInfo);
		}
		return new ModelAndView("com/jeecg/wifiprobe/wifiprobeUploadInfo");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<WifiprobeUploadInfoEntity> list() {
		List<WifiprobeUploadInfoEntity> listWifiprobeUploadInfos=wifiprobeUploadInfoService.getList(WifiprobeUploadInfoEntity.class);
		return listWifiprobeUploadInfos;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		WifiprobeUploadInfoEntity task = wifiprobeUploadInfoService.get(WifiprobeUploadInfoEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody WifiprobeUploadInfoEntity wifiprobeUploadInfo, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<WifiprobeUploadInfoEntity>> failures = validator.validate(wifiprobeUploadInfo);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		wifiprobeUploadInfoService.save(wifiprobeUploadInfo);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = wifiprobeUploadInfo.getId();
		URI uri = uriBuilder.path("/rest/wifiprobeUploadInfoController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody WifiprobeUploadInfoEntity wifiprobeUploadInfo) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<WifiprobeUploadInfoEntity>> failures = validator.validate(wifiprobeUploadInfo);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		wifiprobeUploadInfoService.saveOrUpdate(wifiprobeUploadInfo);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		wifiprobeUploadInfoService.deleteEntityById(WifiprobeUploadInfoEntity.class, id);
	}
}
