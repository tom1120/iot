package com.jeecg.controller.person;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.web.system.pojo.base.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.jeecg.entity.person.PersonEntity;
import com.jeecg.service.person.PersonServiceI;

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

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**   
 * @Title: Controller
 * @Description: 员工信息
 * @author zhangdaihao
 * @date 2017-03-21 16:05:18
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/personController")
public class PersonController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PersonController.class);

	@Autowired
	private PersonServiceI personService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 员工信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/person/personList");
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
	public void datagrid(PersonEntity person,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(PersonEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, person, request.getParameterMap());
		this.personService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除员工信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(PersonEntity person, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		person = systemService.getEntity(PersonEntity.class, person.getId());
		message = "员工信息删除成功";
		personService.delete(person);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加员工信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(PersonEntity person, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(person.getId())) {
			message = "员工信息更新成功";
			PersonEntity t = personService.get(PersonEntity.class, person.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(person, t);
				personService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "员工信息更新失败";
			}
		} else {
			message = "员工信息添加成功";
			personService.save(person);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 员工信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(PersonEntity person, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(person.getId())) {
			person = personService.getEntity(PersonEntity.class, person.getId());
			req.setAttribute("personPage", person);
		}
		return new ModelAndView("com/jeecg/person/person");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<PersonEntity> list() {
		List<PersonEntity> listPersons=personService.getList(PersonEntity.class);
		return listPersons;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		PersonEntity task = personService.get(PersonEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody PersonEntity person, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<PersonEntity>> failures = validator.validate(person);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		personService.save(person);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = person.getId();
		URI uri = uriBuilder.path("/rest/personController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody PersonEntity person) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<PersonEntity>> failures = validator.validate(person);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		personService.saveOrUpdate(person);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		personService.deleteEntityById(PersonEntity.class, id);
	}

	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(PersonEntity personEntity, HttpServletRequest request, HttpServletResponse response,
							   DataGrid dataGrid, ModelMap modelMap){
			modelMap.put(NormalExcelConstants.FILE_NAME,"员工表");
			modelMap.put(NormalExcelConstants.CLASS,PersonEntity.class);
			modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("员工表列表", "导出人:"+ ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"));
			modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
			return NormalExcelConstants.JEECG_EXCEL_VIEW;


	}

	@RequestMapping(params = "exportXls")
	public String exportXls(PersonEntity personEntity,HttpServletRequest request,HttpServletResponse response,
							DataGrid dataGrid,ModelMap modelMap){
		Class personEntityClass=personEntity.getClass();
		Annotation annotation=personEntityClass.getDeclaredAnnotation(Excel.class);

		CriteriaQuery cq=new CriteriaQuery(PersonEntity.class,dataGrid);
		HqlGenerateUtil.installHql(cq,personEntity,request.getParameterMap());
		List<PersonEntity> personEntityList=this.personService.getListByCriteriaQuery(cq,false);


			modelMap.put(NormalExcelConstants.FILE_NAME,"员工表");
			modelMap.put(NormalExcelConstants.CLASS,PersonEntity.class);
			modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("员工表列表", "导出人:"+ ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"));
			modelMap.put(NormalExcelConstants.DATA_LIST,personEntityList);
			return NormalExcelConstants.JEECG_EXCEL_VIEW;

	}


	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<PersonEntity> personEntityList = ExcelImportUtil.importExcel(file.getInputStream(),PersonEntity.class,params);

				this.personService.batchSave(personEntityList);
				j.setMsg("文件导入成功！");

			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}


	/**
	 * 导入功能跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","personController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}







}
