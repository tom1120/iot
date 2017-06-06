package org.jeecgframework.web.activiti.controller;

import net.sf.json.JSONObject;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.NativeProcessDefinitionQueryImpl;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.PropertiesUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.activiti.util.HistoryProcessInstanceDiagramCmd;
import org.jeecgframework.web.activiti.util.WorkflowUtils;
import org.jeecgframework.web.demo.controller.test.DemoController;
import org.jeecgframework.web.system.pojo.base.TSDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.ManagedProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;


/**
 * @Description: TODO(工作流程定义与实例等资源处理类)
 * @author liujinghua
 *
 */
@Controller
@RequestMapping("/activitiController")
public class ActivitiController extends BaseController {

	private static final Logger logger = Logger.getLogger(DemoController.class);
	
	@Autowired
	protected RepositoryService repositoryService;
	
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private ProcessEngine processEngine;

	@Autowired
	private ManagementService managementService;

	/**
	 * 流程定义列表
	 */
	@RequestMapping(params = "processList")
	public ModelAndView processList(HttpServletRequest request) {
		return new ModelAndView("jeecg/activiti/process/processlist");
	}
	
	
	/**
	 * 我的流程定义
	 */
	@RequestMapping(params = "myProcessList")
	public ModelAndView myProcessList(HttpServletRequest request) {
			return new ModelAndView("jeecg/activiti/my/myProcessList");
	}
	
	
	
	/**
	 * 流程启动表单选择
	 */
	@RequestMapping(params = "startPageSelect")
	public ModelAndView startPageSelect(@RequestParam("startPage") String startPage, HttpServletRequest request) {
		
			return new ModelAndView("jeecg/activiti/my/"+startPage.substring(0, startPage.lastIndexOf(".")));
	}
	
	
	/**
	 * easyui 运行中流程列表页面
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "runningProcessList")
	public ModelAndView runningProcessList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		return new ModelAndView("jeecg/activiti/process/runninglist");
	}
	
	/**
	 * easyui 运行中流程列表数据
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "runningProcessDataGrid")
	public void runningProcessDataGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
		/*List<HistoricProcessInstance> historicProcessInstances = historyService
                .createHistoricProcessInstanceQuery()
                .unfinished().list();*/
		 ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
	     List<ProcessInstance> list = processInstanceQuery.list();
		
		StringBuffer rows = new StringBuffer();
		for(ProcessInstance hi : list){
			rows.append("{'id':"+hi.getId()+",'processDefinitionId':'"+hi.getProcessDefinitionId() +"','processInstanceId':'"+hi.getProcessInstanceId()+"','activityId':'"+hi.getActivityId()+"'},");
		}
		
		
		String rowStr = StringUtils.substringBeforeLast(rows.toString(), ",");
		
		JSONObject jObject = JSONObject.fromObject("{'total':"+list.size()+",'rows':["+rowStr+"]}");
		responseDatagrid(response, jObject);
	}
	
	
	/**
	 * 读取工作流定义的图片或xml
	 * @throws Exception
	 */
	@RequestMapping(params = "resourceRead")
    public void resourceRead(@RequestParam("processDefinitionId") String processDefinitionId, @RequestParam("resourceType") String resourceType,
							 HttpServletResponse response) throws Exception {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        String resourceName = "";
        if (resourceType.equals("image")) {
            resourceName = processDefinition.getDiagramResourceName();
        } else if (resourceType.equals("xml")) {
            resourceName = processDefinition.getResourceName();
        }
        InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
        byte[] b = new byte[1024];
        int len = -1;
        
        while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }
	
	/**
	 * 读取带跟踪的流程图片
	 * @throws Exception
	 */
	@RequestMapping(params = "traceImage")
    public void traceImage(@RequestParam("processInstanceId") String processInstanceId,
    		HttpServletResponse response) throws Exception {
    	
		Command<InputStream> cmd = new HistoryProcessInstanceDiagramCmd(
                processInstanceId);

		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        InputStream is = processEngine.getManagementService().executeCommand(
                cmd);
        
        int len = 0;
        byte[] b = new byte[1024];

        while ((len = is.read(b, 0, 1024)) != -1) {
        	response.getOutputStream().write(b, 0, len);
        }
    }
	
	/**
	 * easyui 流程历史页面
	 * @param request
	 */

	@RequestMapping(params = "viewProcessInstanceHistory")
	public ModelAndView viewProcessInstanceHistory(@RequestParam("processInstanceId") String processInstanceId,
												   HttpServletRequest request, HttpServletResponse respone, Model model) {
		
		model.addAttribute("processInstanceId", processInstanceId);
		
		return new ModelAndView("jeecg/activiti/process/viewProcessInstanceHistory");
	}
	
	/**
	 * easyui 流程历史数据获取
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "taskHistoryList")
	public void taskHistoryList(@RequestParam("processInstanceId") String processInstanceId,
								HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
        List<HistoricTaskInstance> historicTasks = historyService
                .createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId).list();
        
        StringBuffer rows = new StringBuffer();
        for(HistoricTaskInstance hi : historicTasks){
			rows.append("{'name':'"+hi.getName()+"','processInstanceId':'"+hi.getProcessInstanceId() +"','startTime':'"+hi.getStartTime()+"','endTime':'"+hi.getEndTime()+"','assignee':'"+hi.getAssignee()+"','deleteReason':'"+hi.getDeleteReason()+"'},");
        	//System.out.println(hi.getName()+"@"+hi.getAssignee()+"@"+hi.getStartTime()+"@"+hi.getEndTime());
        }
		
		String rowStr = StringUtils.substringBeforeLast(rows.toString(), ",");
		
		JSONObject jObject = JSONObject.fromObject("{'total':"+historicTasks.size()+",'rows':["+rowStr+"]}");
		responseDatagrid(response, jObject);
	}
	
	
	/**
     * 删除部署的流程，级联删除流程实例
     * @param deploymentId 流程部署ID
     */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(@RequestParam("deploymentId") String deploymentId, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		repositoryService.deleteDeployment(deploymentId, true);
		
		String message = "删除成功";
		j.setMsg(message);
		return j;
	}
	
	
	/**
	 * easyui AJAX请求数据
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
//		String processKey=request.getParameter("processKey");
//		String processName=request.getParameter("processName");

		String searchfield=request.getParameter("searchfield");

		ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
		List<ProcessDefinition> list = null;
		if(searchfield!=null&&!searchfield.equals("")){
			String searchfieldValue=request.getParameter(searchfield);
			if(!searchfieldValue.equals("")){

				list=repositoryService.createNativeProcessDefinitionQuery().
						sql("select * from "+managementService.getTableName(ProcessDefinition.class)+" where "+searchfield+" like #{searchfieldValue}").
						parameter("searchfieldValue","%"+searchfieldValue+"%").list();
			}else {
				list=query.list();
			}

		}else{

			list=query.list();
		}


		
		StringBuffer rows = new StringBuffer();
		int i = 0;
		for(ProcessDefinition pi : list){
			i++;
			rows.append("{'id':"+i+",'processDefinitionId':'"+pi.getId() +"','startPage':'"+pi.getDescription()+"','resourceName':'"+pi.getResourceName()+"','deploymentId':'"+pi.getDeploymentId()+"','key':'"+pi.getKey()+"','name':'"+pi.getName()+"','version':'"+pi.getVersion()+"','isSuspended':'"+pi.isSuspended()+"'},");
		}
		String rowStr = StringUtils.substringBeforeLast(rows.toString(), ",");
		
//		JSONObject jObject = JSONObject.fromObject("{'total':"+query.count()+",'rows':["+rowStr+"]}");
		JSONObject jObject = JSONObject.fromObject("{'total':"+list.size()+",'rows':["+rowStr+"]}");
		responseDatagrid(response, jObject);
	}



	/**
	 * 上传文件进行部署
	 */
	@RequestMapping(params = "uploadFileDeployed")
	public ModelAndView uploadFileDeployed(HttpServletRequest request) {

		return new ModelAndView("jeecg/activiti/process/uploadFileDeployed");


	}


	/**
	 * 部署上传的流程文件到流程定义中，不会生成流程模型
	 * @param request
	 * @return
     */
	@RequestMapping(params = "deploy",method = RequestMethod.POST)
	public String deploy(HttpServletRequest request) {

		PropertiesUtil util = new PropertiesUtil("sysConfig.properties");
		String exportDir=util.readProperty("export.diagram.path");

		DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest=null;
		if(request instanceof DefaultMultipartHttpServletRequest){
			defaultMultipartHttpServletRequest=(DefaultMultipartHttpServletRequest)request;
		}else{
			try {
				throw new Exception("非DefaultMultipartHttpServletRequest请求");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Map<String, MultipartFile> fileMap= defaultMultipartHttpServletRequest.getFileMap();
		MultipartFile file=null;
		if(fileMap.size()>0){
			file=fileMap.get("Filedata");
		}else{
			try {
				throw new Exception("未有上传文件存在");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}




		String fileName = file.getOriginalFilename();

		try {
			InputStream fileInputStream = file.getInputStream();
			Deployment deployment = null;

			String extension = FilenameUtils.getExtension(fileName);
			if (extension.equals("zip") || extension.equals("bar")) {
				ZipInputStream zip = new ZipInputStream(fileInputStream);
				deployment = repositoryService.createDeployment().addZipInputStream(zip).deploy();
			} else {
				deployment = repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
			}

			List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).list();

			for (ProcessDefinition processDefinition : list) {
				WorkflowUtils.exportDiagramToFile(repositoryService, processDefinition, exportDir);
			}

		} catch (Exception e) {
			logger.error("error on deploy process, because of file input stream", e);
		}

		return "redirect:activitiController.do?processList";
	}



	
	
	/**
	 * easyui 待领任务页面
	 */
	@RequestMapping(params = "waitingClaimTask")
	public ModelAndView waitingClaimTask() {
		
		return new ModelAndView("jeecg/activiti/process/waitingClaimTask");
	}
	
	/**
	 * easyui AJAX请求数据 待领任务
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "waitingClaimTaskDataGrid")
	public void waitingClaimTaskDataGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
		String userId = "hruser";
		TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskCandidateUser(userId).active().list();//.taskCandidateGroup("hr").active().list();
		
		StringBuffer rows = new StringBuffer();
		for(Task t : tasks){
			rows.append("{'name':'"+t.getName() +"','taskId':'"+t.getId()+"','processDefinitionId':'"+t.getProcessDefinitionId()+"'},");
		}
		String rowStr = StringUtils.substringBeforeLast(rows.toString(), ",");
		
		JSONObject jObject = JSONObject.fromObject("{'total':"+tasks.size()+",'rows':["+rowStr+"]}");
		responseDatagrid(response, jObject);
	}



	/**
	 * 挂起、激活流程实例
	 */
	@RequestMapping(value = "processdefinition/update/{state}/{processDefinitionId}")
	@ResponseBody
	public AjaxJson updateState(@PathVariable("state") String state, @PathVariable("processDefinitionId") String processDefinitionId,
							  RedirectAttributes redirectAttributes) {
		AjaxJson ajaxJson=new AjaxJson();
		ajaxJson.setMsg("操作失败！");
		ajaxJson.setSuccess(false);

		if (state.equals("active")) {
//			redirectAttributes.addFlashAttribute("message", "已激活ID为[" + processDefinitionId + "]的流程定义。");
			repositoryService.activateProcessDefinitionById(processDefinitionId, true, null);
			ajaxJson.setMsg("已经激活ID为["+processDefinitionId+"]的流程定义");
			ajaxJson.setSuccess(true);
		} else if (state.equals("suspend")) {
			repositoryService.suspendProcessDefinitionById(processDefinitionId, true, null);
//			redirectAttributes.addFlashAttribute("message", "已挂起ID为[" + processDefinitionId + "]的流程定义。");
			ajaxJson.setMsg("已经挂起ID为["+processDefinitionId+"]的流程定义");
			ajaxJson.setSuccess(true);
		}

		return ajaxJson;
	}


	/**
	 * 将已经部署流程转为流程模型
	 * @param processDefinitionId
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws XMLStreamException
     */
	@RequestMapping(value = "/process/convert-to-model/{processDefinitionId}")
	@ResponseBody
	public AjaxJson convertToModel(@PathVariable("processDefinitionId") String processDefinitionId)
			throws UnsupportedEncodingException, XMLStreamException {
		AjaxJson ajaxJson=new AjaxJson();
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult();
		InputStream bpmnStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
				processDefinition.getResourceName());
		XMLInputFactory xif = XMLInputFactory.newInstance();
		InputStreamReader in = new InputStreamReader(bpmnStream, "UTF-8");
		XMLStreamReader xtr = xif.createXMLStreamReader(in);
		BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);

		BpmnJsonConverter converter = new BpmnJsonConverter();
		com.fasterxml.jackson.databind.node.ObjectNode modelNode = converter.convertToJson(bpmnModel);
		org.activiti.engine.repository.Model modelData = repositoryService.newModel();
		modelData.setKey(processDefinition.getKey());
		modelData.setName(processDefinition.getResourceName());
		modelData.setCategory(processDefinition.getDeploymentId());

		ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
		modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, processDefinition.getName());
		modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
		modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, processDefinition.getDescription());
		modelData.setMetaInfo(modelObjectNode.toString());

		repositoryService.saveModel(modelData);

		repositoryService.addModelEditorSource(modelData.getId(), modelNode.toString().getBytes("utf-8"));
		ajaxJson.setMsg("转换成功！");
		ajaxJson.setSuccess(true);

//		ajaxJson.setObj("model.do?list");

		return ajaxJson;
	}






	
	/**
	 * easyui 待办任务页面
	 */
	@RequestMapping(params = "claimedTask")
	public ModelAndView claimedTask() {
		
		return new ModelAndView("jeecg/activiti/process/claimedTask");
	}
	
	/**
	 * easyui AJAX请求数据 待办任务
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "claimedTaskDataGrid")
	public void claimedTaskDataGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
		String userId = "leaderuser";
		TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId).list();
		
		StringBuffer rows = new StringBuffer();
		for(Task t : tasks){
			rows.append("{'name':'"+t.getName() +"','description':'"+t.getDescription()+"','taskId':'"+t.getId()+"','processDefinitionId':'"+t.getProcessDefinitionId()+"','processInstanceId':'"+t.getProcessInstanceId()+"'},");
		}
		String rowStr = StringUtils.substringBeforeLast(rows.toString(), ",");
		
		JSONObject jObject = JSONObject.fromObject("{'total':"+tasks.size()+",'rows':["+rowStr+"]}");
		responseDatagrid(response, jObject);
	}
	
	/**
	 * easyui 已办任务页面
	 */
	@RequestMapping(params = "finishedTask")
	public ModelAndView finishedTask() {
		
		return new ModelAndView("jeecg/activiti/process/finishedTask");
	}
	
	/**
	 * easyui AJAX请求数据 已办任务
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "finishedTaskDataGrid")
	public void finishedTask(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
		String userId = "leaderuser";
		List<HistoricTaskInstance> historicTasks = historyService
                .createHistoricTaskInstanceQuery().taskAssignee(userId)
                .finished().list();
		
		StringBuffer rows = new StringBuffer();
		for(HistoricTaskInstance t : historicTasks){
			rows.append("{'name':'"+t.getName() +"','description':'"+t.getDescription()+"','taskId':'"+t.getId()+"','processDefinitionId':'"+t.getProcessDefinitionId()+"','processInstanceId':'"+t.getProcessInstanceId()+"'},");
		}
		String rowStr = StringUtils.substringBeforeLast(rows.toString(), ",");
		
		JSONObject jObject = JSONObject.fromObject("{'total':"+historicTasks.size()+",'rows':["+rowStr+"]}");
		responseDatagrid(response, jObject);
	}

	/**
     * 签收任务
     * @param taskId
     */
	@RequestMapping(params = "claimTask")
	@ResponseBody
	public AjaxJson claimTask(@RequestParam("taskId") String taskId, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		String userId = "leaderuser";
		
		TaskService taskService = processEngine.getTaskService();
        taskService.claim(taskId, userId);
		
		String message = "签收成功";
		j.setMsg(message);
		return j;
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
}
