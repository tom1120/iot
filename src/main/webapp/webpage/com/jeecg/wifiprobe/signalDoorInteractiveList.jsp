<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="signalDoorInteractiveList" title="信号-门禁-设备行为" actionUrl="signalDoorInteractiveController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="信号设备id" field="signalProbeDeviceId"   width="120"></t:dgCol>
   <t:dgCol title="门禁参数id" field="doorInterfaceParamId"   width="120"></t:dgCol>
   <t:dgCol title="设备关联行为id" field="deviceActionId"   width="120"></t:dgCol>
   <t:dgCol title="备注" field="note"   width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="signalDoorInteractiveController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="signalDoorInteractiveController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="signalDoorInteractiveController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="signalDoorInteractiveController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>