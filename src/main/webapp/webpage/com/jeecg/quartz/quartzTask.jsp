<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>定时任务</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<%--<body style="overflow-y: hidden" scroll="no">--%>

<script type="text/javascript">
	//初始化下标
	function resetTrNum(tableId) {
		$tbody = $("#"+tableId+"");
		$tbody.find('>tr').each(function(i){
			$(':input, select', this).each(function(){
				var $this = $(this), name = $this.attr('name'), val = $this.val();
				if(name!=null){
					if (name.indexOf("#index#") >= 0){
						$this.attr("name",name.replace('#index#',i));
					}else{
						var s = name.indexOf("[");
						var e = name.indexOf("]");
						var new_name = name.substring(s+1,e);
						$this.attr("name",name.replace(new_name,i));
					}
				}
			});
		});
	}




	$(document).ready(function () {




		$("#jobName").bind("change",function () {
//			alert($("#jobName").val());
			$("input[name='cronTaskList[#index#].jobName']").val($("#jobName").val());
			$("input[name='cronTaskList[0].jobName']").val($("#jobName").val());
		});

		$("select[name='jobGroup']").bind("change",function () {
//			alert($("#jobName").val());
			$("input[name='cronTaskList[#index#].jobGroup']").val($("select[name='jobGroup'] option:selected").val());
			$("input[name='cronTaskList[0].jobGroup']").val($("select[name='jobGroup'] option:selected").val());
		});


	});


</script>


<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="quartzTaskController.do?save">
	<input id="id" name="id" type="hidden" value="${id}">

	<table cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right"><label class="Validform_label"> 调度器名称:</label></td>
			<c:if test="${!empty timeTaskPage}">
			<td class="value"><input class="inputxt" id="schedName" name="schedName"  value="${timeTaskPage.schedName}"></td>
			</c:if>
			<c:if test="${empty timeTaskPage}">
			<td class="value"><input class="inputxt" id="schedName" name="schedName" value="schedulerFactory" readonly="readonly"></td>
			</c:if>


			<td align="right"><label class="Validform_label"> 任务组: </label></td>
			<td class="value">
				<c:if test="${!empty timeTaskPage}">
					<t:dictSelect field="jobGroup" typeGroupCode="jobGroup" hasLabel="false" defaultVal="${timeTaskPage.jobGroup}"></t:dictSelect></c:if>
				<c:if test="${empty timeTaskPage}">
					<t:dictSelect field="jobGroup" typeGroupCode="jobGroup" hasLabel="false" defaultVal="DEFAULT"></t:dictSelect></c:if>

			</td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 任务名称: </label></td>
			<td class="value" colspan="3"><input class="inputxt" style="width: 500px" id="jobName" name="jobName" value="${timeTaskPage.jobName}"></td>

		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 任务类路径:</label></td>
			<td class="value" colspan="3"><input nullmsg="任务类路径不能为空" style="width: 500px" class="inputxt" id="jobClassName" name="jobClassName" value="${timeTaskPage.jobClassName}" datatype="*"></td>

		</tr>
	</table>



	<div style="width: auto; height: 200px;" region="south"><%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
		<div style="width: 690px; height: 1px;"></div>
		<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
			<t:tab href="quartzTaskController.do?cronTaskList&id=${id}" icon="icon-search" title="任务触发器" id="cronTaskList"></t:tab>
			<%--<t:tab href="jeecgOrderMainController.do?jeecgOrderCustomList&goOrderCode=${jeecgOrderMainPage.goOrderCode}" icon="icon-search" title="客户明细" id="Custom"></t:tab>--%>
		</t:tabs></div>

</t:formvalid>


<!-- 添加 任务触发器 模版 -->
<table style="display: none">
	<tbody id="add_cronTask_table_template">
	<tr>
		<td align="center"><input style="width: 20px;" type="checkbox" name="ck" /></td>
		<td align="left" style="display:none;"><input nullmsg="请输入任务触发器调度器名称！" datatype="s1-50" errormsg="任务触发器名称为1到50位" name="cronTaskList[#index#].schedName" maxlength="50" type="text" value="schedulerFactory"
								style="width: 220px;"></td>
		<td align="left"><input nullmsg="请输入任务触发器名称！" datatype="s1-50" errormsg="任务触发器名称为1到50位" name="cronTaskList[#index#].triggerName" maxlength="50" type="text" value="" style="width: 120px;"></td>

		<td align="left"><input nullmsg="请输入任务触发器组名称！" datatype="s1-50" errormsg="任务触发器名称为1到50位" name="cronTaskList[#index#].triggerGroup" maxlength="50" type="text" value=""
								style="width: 120px;"></td>
		<td align="left" style="display: none"><input  type="hidden" name="cronTaskList[#index#].jobName" maxlength="50" type="text" value="${timeTaskPage.jobName}" readonly="readonly"
								style="width: 0px;"></td>
		<td align="left" style="display: none"><input  type="hidden" name="cronTaskList[#index#].jobGroup" maxlength="50" type="text" value="${timeTaskPage.jobGroup}" readonly="readonly"
								style="width: 0px;"></td>
		<td align="left"><input name="cronTaskList[#index#].description" maxlength="50" type="text" value=""
								style="width: 120px;"></td>
		<td align="left"><input name="cronTaskList[#index#].qrtzCronTriggers.cronExpression" maxlength="200" type="text" value="" style="width: 120px;"></td>
		<td align="left"><input nullmsg="请输入任务触发器优先级！" datatype="n" errormsg="优先级必须为数字" name="cronTaskList[#index#].priority"  type="text" value=""
								style="width: 120px;"></td>

		<td align="left"><input  name="cronTaskList[#index#].nextFireTime"  type="text" value="" readonly="readonly"
								 style="width: 120px;"></td>
		<td align="left"><input  name="cronTaskList[#index#].prevFireTime"  type="text" value="" readonly="readonly"
								 style="width: 120px;"></td>

		<td align="left"><input name="cronTaskList[#index#].triggerState"  type="text" value="" readonly="readonly"
								style="width: 120px;"></td>
		<td align="left"><input  name="cronTaskList[#index#].triggerType"   type="text" value="" readonly="readonly"
								 style="width: 120px;"></td>
		<%--<td align="left"><input class="Wdate" name="cronTaskList[#index#].startTime"   type="text" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" ignore="ignore"
								errormsg="日期格式不正确！"
								 style="width: 120px;"></td>--%>
		<td align="left"><input  name="cronTaskList[#index#].endTime"  type="text" value="" readonly="readonly"
								 style="width: 120px;"></td>
		<td align="left"><input  name="cronTaskList[#index#].calendarName"  type="text" value="" readonly="readonly"
								 style="width: 120px;"></td>
		<td align="left"><input  name="cronTaskList[#index#].misfireInstr"   type="text" value="" readonly="readonly"
								 style="width: 120px;"></td>

		<%--<td align="left"><input name="cronTaskList[#index#].jobData" readonly="readonly" maxlength="200" type="text" value="" style="width: 120px;"></td>--%>

	</tr>
	</tbody>
	<%--<tbody id="add_jeecgOrderCustom_table_template">--%>
	<%--<tr>--%>
		<%--<td align="center"><input style="width: 20px;" type="checkbox" name="ck" /></td>--%>
		<%--<td align="left"><input name="jeecgOrderCustomList[#index#].gocCusName" maxlength="50" type="text" style="width: 220px;"></td>--%>
		<%--<td align="left">&lt;%&ndash;				<input name="jeecgOrderCustomList[#index#].gocSex" maxlength="2" type="text"  style="width:120px;"/>&ndash;%&gt; <t:dictSelect field="jeecgOrderCustomList[#index#].gocSex"--%>
																																									<%--typeGroupCode="sex" hasLabel="false"></t:dictSelect></td>--%>
		<%--<td align="left"><input name="jeecgOrderCustomList[#index#].gocIdcard" maxlength="32" type="text" style="width: 120px;"></td>--%>
		<%--<td align="left"><input name="jeecgOrderCustomList[#index#].gocPassportCode" maxlength="32" type="text" style="width: 120px;"></td>--%>
		<%--<td align="left"><input name="jeecgOrderCustomList[#index#].gocBussContent" maxlength="100" type="text" style="width: 120px;"></td>--%>
		<%--<td align="left"><input name="jeecgOrderCustomList[#index#].gocContent" maxlength="200" type="text" style="width: 120px;"></td>--%>
	<%--</tr>--%>
	<%--</tbody>--%>
</table>


</body>