<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>阿里云iot产品表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="productInfoController.do?save">
			<input id="id" name="id" type="hidden" value="${productInfoPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							产品名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="productname" name="productname" ignore="ignore"
							   value="${productInfoPage.productname}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							产品key:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="productkey" name="productkey" ignore="ignore" readonly="readonly"
							   value="${productInfoPage.productkey}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							产品类型id:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="catid" name="catid" ignore="ignore"
							   value="${productInfoPage.catid}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							产品描述:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="productdesc" name="productdesc" ignore="ignore"
							   value="${productInfoPage.productdesc}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<%--<tr>
					<td align="right">
						<label class="Validform_label">
							创建时间:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="gmtcreate" name="gmtcreate" ignore="ignore" readonly="readonly"
							   value="${productInfoPage.gmtcreate}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							修改时间:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="gmtmodified" name="gmtmodified" ignore="ignore" readonly="readonly"
							   value="${productInfoPage.gmtmodified}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							产品密钥:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="productsecret" name="productsecret" ignore="ignore" readonly="readonly"
							   value="${productInfoPage.productsecret}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>--%>
			</table>
		</t:formvalid>
 </body>