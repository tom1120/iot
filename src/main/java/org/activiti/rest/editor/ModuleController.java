package org.activiti.rest.editor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.sf.json.JSONObject;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/model")
public class ModuleController {
	
	private Logger logger = LoggerFactory.getLogger(ModuleController.class);
	
	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private ManagementService managementService;



	/**
	 * 模型列表，入口在此
	 */
	@RequestMapping(value = "listold")
	public ModelAndView modelListold() {
		ModelAndView mav = new ModelAndView("com/jeecg/activiti/modellist");
		List<Model> list = repositoryService.createModelQuery().list();
		mav.addObject("list", list);
		return mav;
	}


	/**
	 * 模型列表，入口在此
	 */
	@RequestMapping(params = "list")
	public ModelAndView modelList() {
		ModelAndView mav = new ModelAndView("com/jeecg/activiti/modellist");
		return mav;
	}




	/**
	 * easyui AJAX请求数据
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {

		String searchfield=request.getParameter("searchfield");

		ModelQuery query=repositoryService.createModelQuery();
//		List<Model> list = query.list();
		List<Model> list = null;
		if(searchfield!=null&&!searchfield.equals("")){
			String searchfieldValue=request.getParameter(searchfield);
			if(!searchfieldValue.equals("")){

				list=repositoryService.createNativeModelQuery().
						sql("select * from "+managementService.getTableName(Model.class)+" where "+searchfield+" like #{searchfieldValue}").
						parameter("searchfieldValue","%"+searchfieldValue+"%").list();
			}else {
				list=query.list();
			}

		}else{

			list=query.list();
		}




		StringBuffer rows = new StringBuffer();
		int i = 0;
		for(Model m : list){
			i++;
			rows.append("{'id':"+m.getId()+
					",'name':'"+m.getName()+
					"','key':'"+m.getKey()+
					"','category':'"+m.getCategory()+
					"','createTime':'"+m.getCreateTime()+
					"','lastUpdateTime':'"+m.getLastUpdateTime()+
					"','version':'"+m.getVersion()+
					"','metaInfo':'"+m.getMetaInfo()+
					"','deploymentId':'"+m.getDeploymentId()+
					"','tenantId':'"+m.getTenantId()+
					"'},");
		}
		String rowStr = StringUtils.substringBeforeLast(rows.toString(), ",");

		JSONObject jObject = JSONObject.fromObject("{'total':"+query.count()+",'rows':["+rowStr+"]}");
		responseDatagrid(response, jObject);
	}


	// -----------------------------------------------------------------------------------
	// 以下各函数可以提成共用部件 (Add by Quainty)
	// -----------------------------------------------------------------------------------
	public void responseDatagrid(HttpServletResponse response, JSONObject jObject) {
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		try {
			PrintWriter pw=response.getWriter();
			pw.write(jObject.toString());
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	/**
	 * 创建流程设计图
	 * @param name 流程名称
	 * @param key 流程关键字key
	 * @param description 流程描述
	 * @param request
     * @param response
     */
	@RequestMapping(value = "create")
	  public void create(@RequestParam("name") String name, @RequestParam("key") String key,
						 @RequestParam("description") String description,
						 HttpServletRequest request, HttpServletResponse response) {
	    try {
	      ObjectMapper objectMapper = new ObjectMapper();
	      ObjectNode editorNode = objectMapper.createObjectNode();
	      editorNode.put("id", "canvas");
	      editorNode.put("resourceId", "canvas");
	      ObjectNode stencilSetNode = objectMapper.createObjectNode();
	      stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
	      editorNode.put("stencilset", stencilSetNode);
	      Model modelData = repositoryService.newModel();
	 
	      ObjectNode modelObjectNode = objectMapper.createObjectNode();
	      modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
	      modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
	      description = StringUtils.defaultString(description);
	      modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
	      modelData.setMetaInfo(modelObjectNode.toString());
	      modelData.setName(name);
	      modelData.setKey(StringUtils.defaultString(key));

	      repositoryService.saveModel(modelData);
	      repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
	 
	      response.sendRedirect(request.getContextPath() + "/modeler.html?modelId=" + modelData.getId());
	    } catch (Exception e) {
	      logger.error("创建模型失败：", e);
	    }
	  }

	/**
	 * 创建流程设计图
	 * @param name 流程名称
	 * @param key 流程关键字key
	 * @param description 流程描述
	 * @param request
     * @param response
     */
	@RequestMapping(value = "createCategory")
	  public void create(@RequestParam("name") String name, @RequestParam("key") String key,
						 @RequestParam("description") String description,
						 @RequestParam("category") String category,
						 HttpServletRequest request, HttpServletResponse response) {

		if(key==null){
			logger.error("流程key为空，请检查！");
			return;
		}



	    try {
	      ObjectMapper objectMapper = new ObjectMapper();
	      ObjectNode editorNode = objectMapper.createObjectNode();
	      editorNode.put("id", "canvas");
	      editorNode.put("resourceId", "canvas");
	      ObjectNode stencilSetNode = objectMapper.createObjectNode();
	      stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
	      editorNode.put("stencilset", stencilSetNode);
	      Model modelData = repositoryService.newModel();

	      ObjectNode modelObjectNode = objectMapper.createObjectNode();
	      modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
	      modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
	      description = StringUtils.defaultString(description);
	      modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
	      modelData.setMetaInfo(modelObjectNode.toString());
	      modelData.setName(name);
	      modelData.setKey(StringUtils.defaultString(key));
//			增加分类
			modelData.setCategory(category);

	      repositoryService.saveModel(modelData);
	      repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));

	      response.sendRedirect(request.getContextPath() + "/modeler.html?modelId=" + modelData.getId());
	    } catch (Exception e) {
	      logger.error("创建模型失败：", e);
	    }
	  }

	/**
	 * 根据Model部署流程
	 */
	@RequestMapping(value = "deploy/{modelId}")
	@ResponseBody
	public AjaxJson deploy(@PathVariable("modelId") String modelId, RedirectAttributes redirectAttributes) {
		AjaxJson ajaxJson=new AjaxJson();
		try {
			Model modelData = repositoryService.getModel(modelId);
			byte[] bytes=repositoryService.getModelEditorSource(modelData.getId());
			ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(bytes);
			byte[] bpmnBytes = null;

			BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
			bpmnBytes = new BpmnXMLConverter().convertToXML(model);
//			if(bpmnBytes.length==0){
//				logger.error("无流程细节，部署流程失败：modelId", modelId);
//				ajaxJson.setMsg("无流程细节，部署流程失败：modelId"+modelId);
//				ajaxJson.setSuccess(false);
//			}

			String processName = modelData.getName() + ".bpmn20.xml";
			Deployment deployment = repositoryService.createDeployment().name(modelData.getName()).addString(processName, new String(bpmnBytes)).deploy();

			ajaxJson.setMsg("部署成功,部署id="+deployment.getId());
			ajaxJson.setSuccess(true);

//			redirectAttributes.addFlashAttribute("message", "部署成功，部署ID=" + deployment.getId());
		} catch (Exception e) {
			logger.error("根据模型部署流程失败：modelId={}", modelId, e);
			ajaxJson.setMsg("根据模型部署流程失败:modelId"+modelId);
			ajaxJson.setSuccess(false);
		}
//		return "redirect:/model/list";

		return ajaxJson;
	}



	/**
	 * 导出model的xml文件
	 */
	@RequestMapping(value = "export/{modelId}")
	public void export(@PathVariable("modelId") String modelId, HttpServletResponse response) {
		try {
			Model modelData = repositoryService.getModel(modelId);
			BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
			JsonNode editorNode = new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
			BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);
			BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
			byte[] bpmnBytes = xmlConverter.convertToXML(bpmnModel);

			ByteArrayInputStream in = new ByteArrayInputStream(bpmnBytes);
			IOUtils.copy(in, response.getOutputStream());
			String filename = bpmnModel.getMainProcess().getId() + ".bpmn20.xml";
			response.setHeader("Content-Disposition", "attachment; filename=" + filename);
			response.flushBuffer();
		} catch (Exception e) {
			logger.error("导出model的xml文件失败：modelId={}", modelId, e);
		}
	}

	@RequestMapping(value = "delete/{modelId}")
	@ResponseBody
	public AjaxJson delete(@PathVariable("modelId") String modelId) {
		AjaxJson ajaxJson=new AjaxJson();
		ajaxJson.setMsg("删除成功！");
		ajaxJson.setSuccess(true);
		try {
			repositoryService.deleteModel(modelId);
		}catch (Exception e){
			ajaxJson.setMsg("删除失败！");
			ajaxJson.setSuccess(false);
		}
//		return "redirect:/model.do?list";

		return ajaxJson;
	}


}
