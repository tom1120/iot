<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>设备关联行为表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="deviceActionController.do?save">
			<input id="id" name="id" type="hidden" value="${deviceActionPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							互动设备id:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="interactiveDeviceId" name="interactiveDeviceId" 
							   value="${deviceActionPage.interactiveDeviceId}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							绑定行为id:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="deviceProductActionInstructionId" name="deviceProductActionInstructionId" 
							   value="${deviceActionPage.deviceProductActionInstructionId}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="note" name="note" ignore="ignore"
							   value="${deviceActionPage.note}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>