<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>信号-门禁-设备行为</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="signalDoorInteractiveController.do?save">
			<input id="id" name="id" type="hidden" value="${signalDoorInteractivePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							信号设备id:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="signalProbeDeviceId" name="signalProbeDeviceId" 
							   value="${signalDoorInteractivePage.signalProbeDeviceId}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							门禁参数id:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="doorInterfaceParamId" name="doorInterfaceParamId" 
							   value="${signalDoorInteractivePage.doorInterfaceParamId}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							设备关联行为id:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="deviceActionId" name="deviceActionId" 
							   value="${signalDoorInteractivePage.deviceActionId}" datatype="*">
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
							   value="${signalDoorInteractivePage.note}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>