<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>流程定义基础功能</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="actQlongBaseController.do?save">
			<input id="id" name="id" type="hidden" value="${actQlongBasePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							流程名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="activitiName" name="activitiName"
							   value="${actQlongBasePage.activitiName}" datatype="*,s2-50" nullmsg="不能为空">
						<span class="Validform_checktip">字符范围为2~50</span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							流程key:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="activitiKey" name="activitiKey"
							   value="${actQlongBasePage.activitiKey}" datatype="*,s2-50" nullmsg="不能为空">
						<span class="Validform_checktip">字符范围为2~50</span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							流程类型:
						</label>
					</td>
					<td class="value">
						<%--<input class="inputxt" id="activitiType" name="activitiType" ignore="ignore"--%>
							   <%--value="${actQlongBasePage.activitiType}">--%>
						<%--<span class="Validform_checktip"></span>--%>
						<c:if test="${actQlongBasePage.activitiType!=null}">
						   <t:dictSelect field="activitiType" typeGroupCode="activitiType" hasLabel="false" id="activitiType" defaultVal="${actQlongBasePage.activitiType}" datatype="*"></t:dictSelect>
						</c:if>
							<c:if test="${actQlongBasePage.activitiType==null}">
								<t:dictSelect field="activitiType" typeGroupCode="activitiType" hasLabel="false" id="activitiType" datatype="*"></t:dictSelect>
							</c:if>
					</td>

				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否部署:
						</label>
					</td>
					<td class="value">
						<%--<input class="inputxt" id="isDeployed" name="isDeployed" ignore="ignore"--%>
							   <%--value="${actQlongBasePage.isDeployed}" datatype="n">--%>
						<%--<span class="Validform_checktip"></span>--%>
							<c:if test="${actQlongBasePage.isDeployed!=null}">
								<t:dictSelect field="isDeployed" typeGroupCode="isornot" hasLabel="false" id="isDeployed" defaultVal="${actQlongBasePage.isDeployed}"></t:dictSelect>
							</c:if>
							<c:if test="${actQlongBasePage.isDeployed==null}">
								<t:dictSelect field="isDeployed" typeGroupCode="isornot" hasLabel="false" id="isDeployed" defaultVal="1"></t:dictSelect>
							</c:if>

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
							   value="${actQlongBasePage.note}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>

<script>
//	$(function () {
//		$("#isDeployed").prop("disabled","true");//此操作后form提交后获取不到值，提交前设置不可用
//		$("#isDeployed").prop("readonly","readonly");//此操作无法实现，select元素不支持readonly


//	})








</script>