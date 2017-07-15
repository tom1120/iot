package com.jeecg.controller.aliyuniot;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aliyun.iot.InitSDK;
import com.aliyun.iot.ProductApi;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.iot.model.v20170620.ApplyDeviceWithNamesResponse;
import com.aliyuncs.iot.model.v20170620.QueryDeviceResponse;
import com.jeecg.entity.aliyuniot.DeviceInfoList;
import com.jeecg.entity.aliyuniot.Deviceinfo;
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

import com.jeecg.entity.aliyuniot.ProductInfoEntity;
import com.jeecg.service.aliyuniot.ProductInfoServiceI;

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
 * @Description: 阿里云iot产品表
 * @author zhangdaihao
 * @date 2017-07-14 08:53:07
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/productInfoController")
public class ProductInfoController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ProductInfoController.class);

	@Autowired
	private ProductInfoServiceI productInfoService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	@Autowired
	private ProductApi productApi;
	


	/**
	 * 阿里云iot产品表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/aliyuniot/productInfoList");
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
	public void datagrid(ProductInfoEntity productInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ProductInfoEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, productInfo, request.getParameterMap());
		this.productInfoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除阿里云iot产品表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ProductInfoEntity productInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		productInfo = systemService.getEntity(ProductInfoEntity.class, productInfo.getId());
		message = "阿里云iot产品表删除成功";
		productInfoService.delete(productInfo);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加阿里云iot产品表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ProductInfoEntity productInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(productInfo.getId())) {
			message = "阿里云iot产品表更新成功";
			ProductInfoEntity t = productInfoService.get(ProductInfoEntity.class, productInfo.getId());
			try {
				boolean b=productApi.updateProduct(t.getProductkey(),t.getProductname(),t.getCatid(),t.getProductdesc());
				if(b){
					MyBeanUtils.copyBeanNotNull2Bean(productInfo, t);
					productInfoService.saveOrUpdate(t);
				}else{
					message="阿里云iot产品表更新失败！";
				}
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "阿里云iot产品表更新失败";
			}
		} else {
			message = "阿里云iot产品表添加成功";
			//创建阿里云iot产品
			ProductInfoEntity productInfoEntity=productApi.createProduct(productInfo.getProductname(),productInfo.getCatid(),productInfo.getProductdesc());
			productInfoService.save(productInfoEntity);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 保存设备信息
	 * @param deviceInfoList
	 * @param request
     * @return
     */
	@RequestMapping(params = "saveDeviceInfo")
	@ResponseBody
	public AjaxJson saveDeviceInfo(DeviceInfoList deviceInfoList,HttpServletRequest request){
		AjaxJson ajaxJson=new AjaxJson();
		ajaxJson.setSuccess(false);
		String message="保存失败！阿里云接口批量添加设备api流程比较复杂及美元删除api，请到阿里云上去维护!";
		List<Deviceinfo> deviceinfoList=deviceInfoList.getDeviceInfoList();

/*		if(deviceinfoList.size()==0){
			message="目前api不支持删除设备！请到阿里云上去删除！";
			ajaxJson.setMsg(message);
			return ajaxJson;
		}


		//阿里云上批量设备申请
		String productKey=null;
		List<String> strings=new ArrayList<>();
		for(Deviceinfo deviceinfo:deviceinfoList){
			strings.add(deviceinfo.getDeviceName());
			productKey=deviceinfo.getProductKey();
		}
		ApplyDeviceWithNamesResponse response=productApi.applyBatchDevice(productKey,strings);
		if(response.getSuccess()){
			Long applyId=response.getApplyId();

		}*/




		ajaxJson.setMsg(message);
		return ajaxJson;
	}

	/**
	 * 同步对应产品的设备信息
	 * @param productkey
	 * @param request
     * @return
     */
	@RequestMapping(params = "syncDeviceInfo")
	@ResponseBody
	public AjaxJson syncDeviceInfo(@RequestParam("productkey") String productkey,HttpServletRequest request){
		AjaxJson ajaxJson=new AjaxJson();
		ajaxJson.setMsg("同步失败！");
		ajaxJson.setSuccess(false);

		//查出数据库中的已存在所有设备信息并删除
		List<Deviceinfo> deviceinfoListDataBase=productInfoService.findHql("from Deviceinfo where productKey=?",productkey);
		productInfoService.deleteAllEntitie(deviceinfoListDataBase);

		//查出对应产品的所有设备信息
		QueryDeviceResponse queryDeviceResponse=productApi.queryDeviceInfoList(1,productkey,10);
		List<QueryDeviceResponse.DeviceInfo> deviceInfos=queryDeviceResponse.getData();
		List<Deviceinfo> deviceinfoList=new ArrayList<>();
		for (int i=0;i<deviceInfos.size();i++
			 ) {
			String deviceId=deviceInfos.get(i).getDeviceId();

			Deviceinfo d=new Deviceinfo();
			d.setDeviceId(deviceId);
			d.setDeviceName(deviceInfos.get(i).getDeviceName());
			d.setDeviceSecret(deviceInfos.get(i).getDeviceSecret());
			d.setGmtCreate(deviceInfos.get(i).getGmtCreate());
			d.setGmtModified(deviceInfos.get(i).getGmtModified());
			d.setProductKey(productkey);
			deviceinfoList.add(d);
		}
		//批量保存到数据库
		try{
			productInfoService.batchSave(deviceinfoList);
			ajaxJson.setSuccess(true);
			ajaxJson.setMsg("保存成功！");
		}
		catch (Exception e){
			e.printStackTrace();

		}

		return ajaxJson;
	}



	@RequestMapping(params = "goInfo")
	public ModelAndView goinfo(ProductInfoEntity productInfoEntity,HttpServletRequest request){
		if (StringUtil.isNotEmpty(productInfoEntity.getId())) {
			productInfoEntity=productInfoService.get(ProductInfoEntity.class,productInfoEntity.getId());
			//获取产品对应的设备信息
			List<Deviceinfo> deviceInfoList=productInfoService.findHql("from Deviceinfo where productKey=?",productInfoEntity.getProductkey());
			request.setAttribute("deviceInfoList",deviceInfoList);
			request.setAttribute("productKey",productInfoEntity.getProductkey());
		}
		return new ModelAndView("com/jeecg/aliyuniot/deviceInfo");
	}


	/**
	 * 阿里云iot产品表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ProductInfoEntity productInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(productInfo.getId())) {
			productInfo = productInfoService.getEntity(ProductInfoEntity.class, productInfo.getId());
			req.setAttribute("productInfoPage", productInfo);
		}
		return new ModelAndView("com/jeecg/aliyuniot/productInfo");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ProductInfoEntity> list() {
		List<ProductInfoEntity> listProductInfos=productInfoService.getList(ProductInfoEntity.class);
		return listProductInfos;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ProductInfoEntity task = productInfoService.get(ProductInfoEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ProductInfoEntity productInfo, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ProductInfoEntity>> failures = validator.validate(productInfo);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		productInfoService.save(productInfo);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = productInfo.getId();
		URI uri = uriBuilder.path("/rest/productInfoController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ProductInfoEntity productInfo) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ProductInfoEntity>> failures = validator.validate(productInfo);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		productInfoService.saveOrUpdate(productInfo);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		productInfoService.deleteEntityById(ProductInfoEntity.class, id);
	}
}
