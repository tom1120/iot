<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>wifi探针参数设置</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="wifiprobeSysParamController.do?save">
			<input id="id" name="id" type="hidden" value="${wifiprobeSysParamPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							缓存数:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="cacheNumber" name="cacheNumber" 
							   value="${wifiprobeSysParamPage.cacheNumber}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							临界强度值:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="rssi" name="rssi" 
							   value="${wifiprobeSysParamPage.rssi}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							打开客户端后延时:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="afterOpenClientDelayTime" name="afterOpenClientDelayTime" ignore="ignore"
							   value="${wifiprobeSysParamPage.afterOpenClientDelayTime}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							关闭客户端前延时:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="beforeCloseClientDelayTime" name="beforeCloseClientDelayTime" ignore="ignore"
							   value="${wifiprobeSysParamPage.beforeCloseClientDelayTime}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							系统参数刷新时间:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="refreshTime" name="refreshTime" ignore="ignore"
							   value="${wifiprobeSysParamPage.refreshTime}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							是否默认:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="isDefault" name="isDefault"
							   value="${wifiprobeSysParamPage.isDefault}" datatype="n">
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
							   value="${wifiprobeSysParamPage.note}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>