<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>信号探测设备表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="signalProbeDeviceController.do?save">
			<input id="id" name="id" type="hidden" value="${signalProbeDevicePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							信号设备探测类型:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="signDeviceType" name="signDeviceType" 
							   value="${signalProbeDevicePage.signDeviceType}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							信号设备标识参数:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="signDeviceParam" name="signDeviceParam" 
							   value="${signalProbeDevicePage.signDeviceParam}" datatype="*">
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
							   value="${signalProbeDevicePage.note}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>