<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="actRuEventSubscrList" title="流程事件" actionUrl="actRuEventSubscrController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="rev" field="rev"   width="120"></t:dgCol>
   <t:dgCol title="eventType" field="eventType"  query="true"  width="120"></t:dgCol>
   <t:dgCol title="eventName" field="eventName"  query="true"  width="120"></t:dgCol>
   <t:dgCol title="executionId" field="executionId"   width="120"></t:dgCol>
   <t:dgCol title="procInstId" field="procInstId"   width="120"></t:dgCol>
   <t:dgCol title="activityId" field="activityId"   width="120"></t:dgCol>
   <t:dgCol title="configuration" field="configuration"   width="120"></t:dgCol>
   <t:dgCol title="created" field="created" formatter="yyyy-MM-dd hh:mm:ss"  width="120"></t:dgCol>
   <t:dgCol title="procDefId" field="procDefId"   width="120"></t:dgCol>
   <t:dgCol title="tenantId" field="tenantId"   width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="actRuEventSubscrController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgFunOpt title="激活" funname="activeEvent(eventType,eventName)" urlclass="ace_button" urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="actRuEventSubscrController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="actRuEventSubscrController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="actRuEventSubscrController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>

<script>
  function activeEvent(eventType,eventName) {
//    console.log(id);
   createdialog("激活事件","确定要激活吗？","actRuEventSubscrController.do?activeEvent&eventType="+eventType+"&eventName="+eventName,"actRuEventSubscrList");
  }
 function reloadTable() {
   $("#actRuEventSubscrList").datagrid("reload");
 }
</script>