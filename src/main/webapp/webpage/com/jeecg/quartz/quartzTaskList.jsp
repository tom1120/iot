<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
		<table id="jobList" style="width: 700px;height: 300px">
			<thead>
			<tr>
				<th field="id" hidden="hidden">编号</th>
				<th field="schedName" width="50">调度器名称</th>
				<th field="jobName" width="50">任务名称</th>
				<th field="jobGroup" width="50">任务组</th>
				<th field="jobClassName" width="20">任务类路径</th>
				<th field="isDurable" width="20">是否持久</th>
				<th field="isNonconcurrent" width="50">是否并发</th>
				<th field="isUpdateData" width="50">是否更新</th>
				<th field="requestsRecovery" width="50">请求恢复</th>
				<th field="jobData" width="50">任务数据</th>
				<th field="opt" width="50">操作</th>
			</tr>
			</thead>
		</table>
		<%--添加工具栏--%>
		<div id="jobListtb" style="padding:3px; height: auto">
			<div style="height:30px;" class="datagrid-toolbar">
				<%--按钮--%>
                <span style="float:left">
                    <a id="addJob" href="#" onclick="add('添加任务','quartzTaskController.do?addorupdate','jobList',1000,500)" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加任务</a>
                </span>
				<span style="float:left">
                    <a id="editJob" href="#" onclick="editJob()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">编辑任务</a>
                </span>
				<%--单值动态查询--%>
                <span style="float:right">
                    <input id="jeecgEasyUIListsearchbox" class="easyui-searchbox"></input>
                    <div id="jeecgEasyUIListmm" style="width:120px">
                        <div data-options="name:'jobName',iconCls:'icon-ok'">任务名称</div>
                        <div data-options="name:'jobGroup',iconCls:'icon-ok'">任务组</div>
                    </div>
                </span>
			</div>


		</div>


	</div>

	<%--触发器设置--%>
<%--	<div region="east" style="width: 600px;" split="true">
		<div tools="#tt1" class="easyui-panel" title='触发器设置' style="padding: 10px;" fit="true" border="false" id="function-panel"></div>
	</div>
	<div id="tt1"></div>
	</div>--%>


</div>





<script>
	<%--设置触发器--%>
	function setCriggerByJob(id,roleName) {
		$("#function-panel").panel(
				{
					title :roleName+ ':' + '<t:mutiLang langKey="current.permission"/>',
					href:"roleController.do?fun&roleId=" + id
				}
		);
		//$('#function-panel').panel("refresh" );

	}


	function editJob() {
		var row=$("#jobList").datagrid("getSelected");
		if(row==null){
			tip("请选择一行记录后再点编辑按钮");
		}
		var id=row.id;
		add('编辑任务','quartzTaskController.do?addorupdate&id='+id,'jobList',1000,500)
	}


	function editCron(cronExpression,id,SPECIALCHAR) {
//		console.log(unescape(cronExpression));
		add("cron表达式","cron.html?cronExpression="+unescape(cronExpression)+"&id="+id,"timeTaskList",'1000','800');
	}
	function reloadTable() {
		$("#jobList").datagrid("reload");
	}
	//暂停job
	function pauseJob(id,datagrid) {
//		console.debug(id);
		datagrid=$("#"+datagrid);
		$.ajax({
			url:"quartzTaskController.do?pauseJob",
			data:{ "id": id },
			type:"post",
			async:false,
			dataType:"json",
			success:function (data,textStatus) {
				var d=$.parseJSON(data);
				if(d==null||undefined==typeof data){d=data};

				if(d.success){
					datagrid.datagrid('reload');
					tip(d.msg);
				}else{
					tip(d.msg);
				}

			},
			error:function (xmlHttpRequest,textStatus,errorThrow) {

			}

		})
	}
	//恢复job
	function resumeJob(id,datagrid) {
//		console.debug(id);
		datagrid=$("#"+datagrid);
		$.ajax({
			url:"quartzTaskController.do?resumeJob",
			data:{ "id": id },
			type:"post",
			async:false,
			dataType:"json",
			success:function (data,textStatus) {
				var d=$.parseJSON(data);
				if(d==null||undefined==typeof data){d=data};

				if(d.success){
					datagrid.datagrid('reload');
					tip(d.msg);
				}else{
					tip(d.msg);
				}

			},
			error:function (xmlHttpRequest,textStatus,errorThrow) {

			}

		})
	}

	//立即运行job
	function triggeringJob(id,datagrid) {
//		console.debug(id);
		datagrid=$("#"+datagrid);
		$.ajax({
			url:"quartzTaskController.do?triggeringJob",
			data:{ "id": id },
			type:"post",
			async:false,
			dataType:"json",
			success:function (data,textStatus) {
				var d=$.parseJSON(data);
				if(d==null||undefined==typeof data){d=data};

				if(d.success){
					datagrid.datagrid('reload');
					tip(d.msg);
				}else{
					tip(d.msg);
				}

			},
			error:function (xmlHttpRequest,textStatus,errorThrow) {

			}

		})
	}

	// 编辑初始化数据
	function getData(data){
		var rows = [];
		var total = data.total;
		for(var i=0; i<data.rows.length; i++){
			rows.push({
				id: data.rows[i].id,
				schedName: data.rows[i].schedName,
				jobName: data.rows[i].jobName,
				jobGroup: data.rows[i].jobGroup,
				jobClassName: data.rows[i].jobClassName,
				isDurable: data.rows[i].isDurable,
				isNonconcurrent: data.rows[i].isNonconcurrent,
				isUpdateData: data.rows[i].isUpdateData,
				requestsRecovery: data.rows[i].requestsRecovery,
				jobData: data.rows[i].jobData,
				opt:"[<a href=\"#\" onclick=\"delObj('quartzTaskController.do?del&id="+data.rows[i].id+"','modelList')\">删除</a>]"+
				"[<a href=\"#\" onclick=\"pauseJob('" +data.rows[i].id+ "','modelList')\">暂停</a>]"+
				"[<a href=\"#\" onclick=\"resumeJob('"+data.rows[i].id+"','modelList')\">恢复</a>]"+
				"[<a href=\"#\" onclick=\"triggeringJob('"+data.rows[i].id+"','modelList')\">立即运行</a>]"
//                opt:"[<a href=\"#\" onclick=\"alert('ok!')\">删除流程</a>]"

			});
		}
		var newData={"total":total,"rows":rows};
		return newData;
	}

	// 筛选
	function jeecgEasyUIListsearchbox(value,name){
		var queryParams=$('#jobList').datagrid('options').queryParams;
		queryParams[name]=value;
		queryParams.searchfield=name;
		$('#jobList').datagrid('load');
	}
	// 刷新
	function reloadTable(){
		$('#jobList').datagrid('reload');
	}

	// 设置datagrid属性
	$('#jobList').datagrid({
		title: '任务细节',
		idField: 'id',
		fit:true,
		loadMsg: '数据加载中...',
		pageSize: 10,
		pagination:true,
		sortOrder:'asc',
		rownumbers:true,
		singleSelect:true,
		fitColumns:true,
		showFooter:true,
		url:'quartzTaskController.do?datagrid',
		loadFilter: function(data){
			return getData(data);
		},
		toolbar:"#jobListtb"

	});
	//设置分页控件
	$('#jobList').datagrid('getPager').pagination({
		pageSize: 10,
		pageList: [10,20,30],
		beforePageText: '',
		afterPageText: '/{pages}',
		displayMsg: '{from}-{to}共{total}条',
		showPageList:true,
		showRefresh:true,
		onBeforeRefresh:function(pageNumber, pageSize){
			$(this).pagination('loading');
			$(this).pagination('loaded');
		}
	});
	// 设置筛选
	$('#jeecgEasyUIListsearchbox').searchbox({
		searcher:function(value,name){
			jeecgEasyUIListsearchbox(value,name);
		},
		menu:'#jeecgEasyUIListmm',
		prompt:'请输入查询关键字'
	});



</script>