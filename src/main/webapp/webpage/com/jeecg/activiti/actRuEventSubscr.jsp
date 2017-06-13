<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>流程事件</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="actRuEventSubscrController.do?save">
			<input id="id" name="id" type="hidden" value="${actRuEventSubscrPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							rev:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="rev" name="rev" ignore="ignore"
							   value="${actRuEventSubscrPage.rev}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							eventType:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="eventType" name="eventType" 
							   value="${actRuEventSubscrPage.eventType}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							eventName:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="eventName" name="eventName" ignore="ignore"
							   value="${actRuEventSubscrPage.eventName}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							executionId:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="executionId" name="executionId" ignore="ignore"
							   value="${actRuEventSubscrPage.executionId}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							procInstId:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="procInstId" name="procInstId" ignore="ignore"
							   value="${actRuEventSubscrPage.procInstId}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							activityId:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="activityId" name="activityId" ignore="ignore"
							   value="${actRuEventSubscrPage.activityId}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							configuration:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="configuration" name="configuration" ignore="ignore"
							   value="${actRuEventSubscrPage.configuration}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							created:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="created" name="created" 
							     value="<fmt:formatDate value='${actRuEventSubscrPage.created}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							procDefId:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="procDefId" name="procDefId" ignore="ignore"
							   value="${actRuEventSubscrPage.procDefId}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							tenantId:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="tenantId" name="tenantId" ignore="ignore"
							   value="${actRuEventSubscrPage.tenantId}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>