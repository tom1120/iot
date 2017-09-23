<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="deviceProductActionInstructionList" title="设备产品行为定义表" actionUrl="deviceProductActionInstructionController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="产品设备类型" field="deviceProductType"   width="120"></t:dgCol>
   <t:dgCol title="行动key" field="actionKey"   width="120"></t:dgCol>
   <t:dgCol title="行动描述" field="actionDesc"   width="120"></t:dgCol>
   <t:dgCol title="行动指令json" field="actionInstructionJson"   width="120"></t:dgCol>
   <t:dgCol title="备注" field="note"   width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="deviceProductActionInstructionController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="deviceProductActionInstructionController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="deviceProductActionInstructionController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="deviceProductActionInstructionController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>