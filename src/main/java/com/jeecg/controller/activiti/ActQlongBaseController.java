package com.jeecg.controller.activiti;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.activiti.rest.editor.ModuleController;
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

import com.jeecg.entity.activiti.ActQlongBaseEntity;
import com.jeecg.service.activiti.ActQlongBaseServiceI;

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
 * @Description: 流程定义基础功能
 * @author zhangdaihao
 * @date 2017-05-25 14:56:37
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/actQlongBaseController")
public class ActQlongBaseController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ActQlongBaseController.class);

	@Autowired
	private ActQlongBaseServiceI actQlongBaseService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private ManagementService managementService;

	


	/**
	 * 流程定义基础功能列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/activiti/actQlongBaseList");
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
	public void datagrid(ActQlongBaseEntity actQlongBase,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ActQlongBaseEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, actQlongBase, request.getParameterMap());
		this.actQlongBaseService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除流程定义基础功能
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ActQlongBaseEntity actQlongBase, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		actQlongBase = systemService.getEntity(ActQlongBaseEntity.class, actQlongBase.getId());
		message = "流程定义基础功能删除成功";
		actQlongBaseService.delete(actQlongBase);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加流程定义基础功能
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ActQlongBaseEntity actQlongBase, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();

		List<ActQlongBaseEntity> list=actQlongBaseService.findByProperty(ActQlongBaseEntity.class,"activitiKey",actQlongBase.getActivitiKey());

		if (StringUtil.isNotEmpty(actQlongBase.getId())) {
			message = "流程定义基础功能更新成功";
			ActQlongBaseEntity t = actQlongBaseService.get(ActQlongBaseEntity.class, actQlongBase.getId());

			if(list.size()>1){
				message="流程key定义重复，请重新命名！";
			}else{
				try {
					MyBeanUtils.copyBeanNotNull2Bean(actQlongBase, t);
					actQlongBaseService.saveOrUpdate(t);
					systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
				} catch (Exception e) {
					e.printStackTrace();
					message = "流程定义基础功能更新失败";
				}
			}

		} else {
			if(list.size()>0){
				message="流程key定义重复，请重新命名！";
			}else{
				message = "流程定义基础功能添加成功";
				actQlongBaseService.save(actQlongBase);
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}
		}



		j.setMsg(message);
		return j;
	}

	/**
	 * 流程定义基础功能列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ActQlongBaseEntity actQlongBase, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(actQlongBase.getId())) {
			actQlongBase = actQlongBaseService.getEntity(ActQlongBaseEntity.class, actQlongBase.getId());
			req.setAttribute("actQlongBasePage", actQlongBase);
		}
		return new ModelAndView("com/jeecg/activiti/actQlongBase");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ActQlongBaseEntity> list() {
		List<ActQlongBaseEntity> listActQlongBases=actQlongBaseService.getList(ActQlongBaseEntity.class);
		return listActQlongBases;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ActQlongBaseEntity task = actQlongBaseService.get(ActQlongBaseEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ActQlongBaseEntity actQlongBase, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ActQlongBaseEntity>> failures = validator.validate(actQlongBase);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		actQlongBaseService.save(actQlongBase);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = actQlongBase.getId();
		URI uri = uriBuilder.path("/rest/actQlongBaseController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ActQlongBaseEntity actQlongBase) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ActQlongBaseEntity>> failures = validator.validate(actQlongBase);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		actQlongBaseService.saveOrUpdate(actQlongBase);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		actQlongBaseService.deleteEntityById(ActQlongBaseEntity.class, id);
	}


	@RequestMapping(params = "checkModelKey")
	@ResponseBody
	public AjaxJson checkModelKey(HttpServletRequest request, HttpServletResponse response){
		String checkModelKey=request.getParameter("checkModelKey");
		String key=request.getParameter("key");
		AjaxJson ajaxJson=new AjaxJson();
		ajaxJson.setSuccess(true);
		ajaxJson.setMsg("检测通过！");
		//使用流程原生查询api执行查询
//		String tests=managementService.getTableName(Model.class);
		List<Model> models=repositoryService.createNativeModelQuery().sql("select ID_ from "+managementService.getTableName(Model.class)+
				" T where T.KEY_=#{key_param}").parameter("key_param",key).list();

		if(checkModelKey.equals("newkey")){
			if(models.size()>0){
				ajaxJson.setSuccess(false);
				ajaxJson.setMsg("流程key定义重复，无法创建，请重新命名流程key值！");
			}
		}else{//编辑
			if(models.size()==0){
				ajaxJson.setSuccess(false);
				ajaxJson.setMsg("流程未有定义，无法编辑！");
			}else{
				String id=models.get(0).getId();
				ajaxJson.setObj(id);
			}
		}

		return ajaxJson;
	}



	/**
	 * 增加流程设计器入口
	 * 1）如果已经保存流程，则打开现有流程编辑
	 * 2）如果没有保存流程，则新建
	 */
	@RequestMapping(params = "modelar")
	public void modelar(ActQlongBaseEntity actQlongBaseEntity,HttpServletRequest request, HttpServletResponse response){
//		String name=request.getParameter("name");
//		String key=request.getParameter("key");
//		String category=request.getParameter("category");
//		String description=request.getParameter("description");

//		String url="model/createCategory.do?name="+name+"&key="+key+"&category="+category+"&description="+description;



		String url="model/createCategory.do";



		try {
			request.getRequestDispatcher(url).forward(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}



}
