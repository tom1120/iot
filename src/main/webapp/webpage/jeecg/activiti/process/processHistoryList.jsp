<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding: 1px;">
        <%--<table id="processList" toolbar="#tb" style="width: 700px; height: 300px">--%>
        <table id="processHistoryList" style="width: 700px; height: 300px">
            <thead>
            <tr>
                <th field="id" hidden="hidden">编号</th>
                <th field="procInstId" width="50">流程实例id</th>
                <th field="businessKey" width="50">业务Key</th>
                <th field="processDefinitionName" width="50">流程名称</th>
                <th field="procDefId" width="50">流程定义Id</th>
                <th field="startTime" width="50">开始时间</th>
                <th field="endTime" width="50">结束时间</th>
                <th field="duration" width="50">时长</th>
                <th field="startUserId" width="50">发起人员Id</th>
                <th field="startActId" width="50">开始节点</th>
                <th field="endActId" width="50">结束节点</th>
                <th field="superProcessInstanceId" width="50">超级流程实例Id</th>
                <th field="deleteReason" width="50">删除理由</th>

                <th field="opt" width="50">操作</th>
            </tr>
            </thead>
        </table>
        <%--查询栏--%>
        <%--<div id="tb" style="padding:3px">--%>
        <%--<span>流程key:</span>--%>
        <%--<input id="processKey" style="line-height:26px;border:1px solid #ccc">--%>
        <%--<span>流程名称:</span>--%>
        <%--<input id="processName" style="line-height:26px;border:1px solid #ccc">--%>
        <%--<a href="#" class="easyui-linkbutton" plain="true" onclick="doSearch()">查询</a>--%>
        <%--</div>--%>

        <div id="processHistoryListtb" style="padding:3px; height: auto">







            <div style="height:30px;" class="datagrid-toolbar">

                <%--按钮--%>
                <span style="float:left">
                    <a id="deployedBtn1" href="#"
                       onclick="add('文件上传','activitiController.do?uploadFileDeployed','modelList',null,null)"
                       class="easyui-linkbutton" data-options="iconCls:'icon-add'">文件部署1</a>
                </span>

                <span style="float:right">
                    <input id="jeecgEasyUIListsearchbox" class="easyui-searchbox"></input>
                    <div id="jeecgEasyUIListmm" style="width:120px">
                        <div data-options="name:'processDefinitionName',iconCls:'icon-ok'">流程名称</div>
                        <div data-options="name:'proc_def_id_',iconCls:'icon-ok'">流程定义id</div>
                        <div data-options="name:'start_act_id_',iconCls:'icon-ok'">开始节点</div>
                    </div>
                </span>
            </div>


        </div>


    </div>


</div>


<%--<input id="jeecgEasyUIListsearchbox"></input>--%>

<%--<div id="jeecgEasyUIListmm" style="width:120px">--%>
<%--<div data-options="name:'key_',iconCls:'icon-ok'">流程key</div>--%>
<%--<div data-options="name:'name_',iconCls:'icon-ok'">流程名称</div>--%>
<%--</div>--%>


<script type="text/javascript">

    //	function doSearch(){
    //		$('#processList').datagrid('load',{
    //			processKey: $('#processKey').val(),
    //			processName: $('#processName').val()
    //		});
    //	}


    //查看流程xml或流程图片
    function readProcessResouce(processDefinitionId, resourceType) {
        var url = "";
        var title = "";
        if (resourceType == "xml") {
            title = "查看流程文件";
            url = "activitiController.do?resourceRead&processDefinitionId=" + processDefinitionId + "&resourceType=xml&isIframe"
            //url = "activitiController.do?resourceRead&processDefinitionId=vacation:1:10&resourceType=image&isIframe"
        }

        if (resourceType == "image") {
            title = "查看流程图片";
            url = "activitiController.do?resourceRead&processDefinitionId=" + processDefinitionId + "&resourceType=image&isIframe"
        }
        addOneTab(title, url);
    }

    // 编辑初始化数据
    function getData(data) {
        var rows = [];
        var total = data.total;
        for (var i = 0; i < data.rows.length; i++) {
            rows.push({
                id: data.rows[i].id,
                procInstId: data.rows[i].procInstId,
                businessKey: data.rows[i].businessKey,
                processDefinitionName: data.rows[i].processDefinitionName,
                procDefId: data.rows[i].procDefId,
                startTime: data.rows[i].startTime,
                endTime: data.rows[i].endTime,
                duration: data.rows[i].duration,
                startUserId: data.rows[i].startUserId,
                startActId: data.rows[i].startActId,
                endActId: data.rows[i].endActId,
                superProcessInstanceId: data.rows[i].superProcessInstanceId,
                deleteReason: data.rows[i].deleteReason,


/*                xml: "[<a href=\"#\" onclick=\"readProcessResouce('" + data.rows[i].processDefinitionId + "','xml')\">查看流程xml</a>]",
                image: "[<a href=\"#\" onclick=\"readProcessResouce('" + data.rows[i].processDefinitionId + "','image')\">查看流程图片</a>]",
                isSuspended: data.rows[i].isSuspended,*/



                opt: "[<a href=\"#\" onclick=\"delObj('service/processController/history/historic-process-instances/" + data.rows[i].procInstId + "','processHistoryList')\">删除</a>]"
/*                + function activeOrSuspend() {
                    var state = data.rows[i].isSuspended;
                    var s = "";
                    if (state == "true") {
                        s = "[<a href=\"#\" onclick=\"suspendActiviti('service/activitiController/processdefinition/update/active/" + data.rows[i].processDefinitionId + "','processHistoryList')\">激活</a>]";
                    } else {
                        s = "[<a href=\"#\" onclick=\"activeActiviti('service/activitiController/processdefinition/update/suspend/" + data.rows[i].processDefinitionId + "','processHistoryList')\">挂起</a>]";
                    }


                    return s;
                }() +
                "[<a href=\"#\" onclick=\"convertToModel('service/activitiController/process/convert-to-model/" + data.rows[i].processDefinitionId + "','processHistoryList')\">转为model</a>]"*/

            });
        }
        var newData = {"total": total, "rows": rows};
        return newData;
    }




    //挂起
    function suspendActiviti(url, datagirdId) {
        var datagird = $("#" + datagirdId);
        $.ajax({
            url: url,
            dataType: "json",
            type: "post",
            async: false,
            success: function (data, textStatus) {
                var d = $.parseJSON(data);
                if (d == null || undefined == typeof d) {
                    d = data;
                }
                if (d.success) {
                    datagird.datagrid("reload");
                    tip(d.msg);
                } else {
                    tip(d.msg);
                }
            },
            error: function (xmlHttpRequest, textStatus, errorThrow) {

            }

        });
    }
    //激活，使用通用方法实现
    function activeActiviti(url, datagirdId) {
        doSubmit(url, datagirdId);
    }
    //已部署流程转为model
    function convertToModel(url, datagirdId) {
        doSubmitNew(url, datagirdId, null, convertToModelSuccess, null, true);
    }
    //转换成功调用函数
    function convertToModelSuccess() {
        addOneTab("流程模型查看", "model.do?list");
    }


    // 刷新
    function reloadTable() {
        $('#processHistoryList').datagrid('reload');
    }

    // 设置datagrid属性
    $('#processHistoryList').datagrid({
        title: '流程历史列表',
        idField: 'id',
        fit: true,
        loadMsg: '数据加载中...',
        pageSize: 10,
        pagination: true,
        height: $("#processHistoryList").height() - $("#jeecgEasyUIListsearchbox").height(),
        sortOrder: 'asc',
        rownumbers: true,
        singleSelect: true,
        fitColumns: true,
        showFooter: true,
        url: 'service/processController/history/datagrid',
        loadFilter: function (data) {
            return getData(data);
        },
//			toolbar:"#jeecgEasyUIListsearchbox"
        toolbar: "#processHistoryListtb"

    });
    //设置分页控件
    $('#processHistoryList').datagrid('getPager').pagination({
        pageSize: 10,
        pageList: [10, 20, 30],
        beforePageText: '',
//			fit:true,
        afterPageText: '/{pages}',
        displayMsg: '{from}-{to}共{total}条',
        showPageList: true,
        showRefresh: true,
        onBeforeRefresh: function (pageNumber, pageSize) {
            $(this).pagination('loading');
            $(this).pagination('loaded');
        }
    });
    // 设置筛选
    $('#jeecgEasyUIListsearchbox').searchbox({
        searcher: function (value, name) {
            jeecgEasyUIListsearchbox(value, name);
        },
        menu: '#jeecgEasyUIListmm',
        prompt: '请输入查询关键字'
    });

    // 筛选
    function jeecgEasyUIListsearchbox(value, name) {
        var queryParams = $('#processHistoryList').datagrid('options').queryParams;
        queryParams[name] = value;
        queryParams.searchfield = name;
        $('#processHistoryList').datagrid('load');
    }

</script>