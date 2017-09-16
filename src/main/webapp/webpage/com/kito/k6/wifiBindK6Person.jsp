<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>mac地址绑定</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="wifiBindK6PersonController.do?save">
    <input id="staffId" name="staffId" type="hidden" value="${hrStaffInfo.staffId }">
    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
        <tr>
            <td align="right">
                <label class="Validform_label">
                    员工编码:
                </label>
            </td>
            <td class="value">
                <input class="inputxt" id="staffCode" name="staffCode" ignore="ignore" readonly="readonly"
                       value="${hrStaffInfo.staffCode}">
                <span class="Validform_checktip"></span>
            </td>
        </tr>
        <tr>
            <td align="right">
                <label class="Validform_label">
                    员工姓名:
                </label>
            </td>
            <td class="value">
                <input class="inputxt" id="name" name="name" ignore="ignore" readonly="readonly"
                       value="${hrStaffInfo.name}">
                <span class="Validform_checktip"></span>
            </td>
        </tr>
        <tr>
            <td align="right">
                <label class="Validform_label">
                    手机mac地址:
                </label>
            </td>
            <td class="value">
                <input class="inputxt" id="mobileWifiMac" name="mobileWifiMac" ignore="ignore"
                       value="${hrStaffInfo.mobileWifiMac}">
                <span class="Validform_checktip"></span>
            </td>
        </tr>
        <tr>
            <td align="right">
                <label class="Validform_label">
                    绑定标志:
                </label>
            </td>
            <td class="value">
                <input class="inputxt" id="mobileWifiMacFlag" name="mobileWifiMacFlag" ignore="ignore"
                       value="${hrStaffInfo.mobileWifiMacFlag}">
                <span class="Validform_checktip"></span>
            </td>
        </tr>
    </table>
</t:formvalid>
</body>