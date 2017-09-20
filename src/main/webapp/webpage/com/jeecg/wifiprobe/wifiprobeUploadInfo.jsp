<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>上报wifi探针信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="wifiprobeUploadInfoController.do?save">
			<input id="id" name="id" type="hidden" value="${wifiprobeUploadInfoPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							探针信息:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="probeInfo" name="probeInfo" 
							   value="${wifiprobeUploadInfoPage.probeInfo}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							mac地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="mac" name="mac" 
							   value="${wifiprobeUploadInfoPage.mac}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							强度值:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="rssi" name="rssi" 
							   value="${wifiprobeUploadInfoPage.rssi}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							上报时间:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="uploadTime" name="uploadTime" ignore="ignore"
							   value="${wifiprobeUploadInfoPage.uploadTime}">
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
							   value="${wifiprobeUploadInfoPage.note}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>