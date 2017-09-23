<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>设备产品行为定义表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="deviceProductActionInstructionController.do?save">
			<input id="id" name="id" type="hidden" value="${deviceProductActionInstructionPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							产品设备类型:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="deviceProductType" name="deviceProductType" 
							   value="${deviceProductActionInstructionPage.deviceProductType}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							行动key:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="actionKey" name="actionKey" 
							   value="${deviceProductActionInstructionPage.actionKey}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							行动描述:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="actionDesc" name="actionDesc" 
							   value="${deviceProductActionInstructionPage.actionDesc}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							行动指令json:
						</label>
					</td>
					<td class="value">
<%--						<input class="inputxt" id="actionInstructionJson" name="actionInstructionJson"
							   value="${deviceProductActionInstructionPage.actionInstructionJson}" datatype="*">--%>
						<textarea class="" class="inputxt" id="actionInstructionJson" name="actionInstructionJson"
								  value="${deviceProductActionInstructionPage.actionInstructionJson}" datatype="*">
						</textarea>
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
							   value="${deviceProductActionInstructionPage.note}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>