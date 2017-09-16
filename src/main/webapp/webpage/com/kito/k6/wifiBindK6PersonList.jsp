<%--
  Created by IntelliJ IDEA.
  User: zhaoyipc
  Date: 2017/9/15
  Time: 9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<t:datagrid name="wifiBindList" title="mac地址绑定" actionUrl="wifiBindK6PersonController.do?datagrid" idField="staffId" queryMode="group">
    <t:dgCol title="id" field="id" hidden="true"></t:dgCol>
    <t:dgCol title="staffid" field="staffId" hidden="true"></t:dgCol>
    <t:dgCol title="员工编码" field="staffCode" width="20" query="true"></t:dgCol>
    <t:dgCol title="员工名称" field="name" query="true"></t:dgCol>
    <t:dgCol title="手机mac地址" field="mobileWifiMac" query="true"></t:dgCol>
    <t:dgCol title="绑定标志" field="mobileWifiMacFlag" query="true"></t:dgCol>
    <t:dgCol title="common.operation" field="opt" width="100"></t:dgCol>
<%--    <t:dgDelOpt url="functionController.do?delop&id={id}" title="common.delete" urlclass="ace_button"  urlfont="fa-trash-o"></t:dgDelOpt>
    <t:dgFunOpt funname="editoperation(id,operationname)" title="common.edit" urlclass="ace_button"  urlfont="fa-edit"></t:dgFunOpt>--%>
     <t:dgToolBar title="操作编辑" icon="icon-edit" url="wifiBindK6PersonController.do?addorupdate" funname="updateByIdField"></t:dgToolBar>
</t:datagrid>
