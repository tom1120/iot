<%--
  Created by IntelliJ IDEA.
  User: zhaoyipc
  Date: 2017/5/28
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding: 1px;">
    <table id="modelList" style="width: 700px;height: 300px">
        <thead>
            <tr>
                <th field="id" hidden="hidden">编号</th>
                <th field="name" width="50">模型名称</th>
                <th field="key" width="50">关键字</th>
                <th field="category" width="50">模型分类</th>
                <th field="createTime" width="20">创建时间</th>
                <th field="lastUpdateTime" width="50">最后更新时间</th>
                <th field="version" width="50">版本</th>
                <th field="metaInfo" width="50">数据源信息</th>
                <th field="deploymentId" width="50">部署id</th>
                <th field="tenantId" width="50">tenantId</th>
                <th field="opt" width="50">操作</th>
            </tr>
        </thead>
    </table>
        <%--添加工具栏--%>
        <div id="modelListtb" style="padding:3px; height: auto">
            <div style="height:30px;" class="datagrid-toolbar">
                <%--按钮--%>
                <span style="float:left">
                    <a id="deployedBtn" href="#" onclick="add('文件上传','activitiController.do?uploadFileDeployed','modelList',null,null)" class="easyui-linkbutton" data-options="iconCls:'icon-add'">文件部署</a>
                </span>
                <%--单值动态查询--%>
                <span style="float:right">
                    <input id="jeecgEasyUIListsearchbox" class="easyui-searchbox"></input>
                    <div id="jeecgEasyUIListmm" style="width:120px">
                        <div data-options="name:'key_',iconCls:'icon-ok'">关键字</div>
                        <div data-options="name:'name_',iconCls:'icon-ok'">模型名称</div>
                    </div>
                </span>
            </div>


        </div>


    </div>
</div>

<script type="text/javascript">
    // 编辑初始化数据
    function getData(data){
        var rows = [];
        var total = data.total;
        for(var i=0; i<data.rows.length; i++){
            rows.push({
                id: data.rows[i].id,
                revision: data.rows[i].revision,
                name: data.rows[i].name,
                key: data.rows[i].key,
                category: data.rows[i].category,
                createTime: data.rows[i].createTime,
                lastUpdateTime: data.rows[i].lastUpdateTime,
                version: data.rows[i].version,
                metaInfo: data.rows[i].metaInfo,
                deploymentId: data.rows[i].deploymentId,

                tenantId: data.rows[i].tenantId,
                opt:"[<a href=\"#\" onclick=\"delObj('service/model/delete/"+data.rows[i].id+"','modelList')\">删除</a>]"+
                "[<a href=\"#\" onclick=\"editModel("+data.rows[i].id+")\">编辑</a>]"+
                "[<a href=\"#\" onclick=\"deployModel("+data.rows[i].id+",'modelList')\">部署</a>]"+
                "[<a href=\"#\" onclick=\"exportModel("+data.rows[i].id+",'modelList')\">导出</a>]"
//                opt:"[<a href=\"#\" onclick=\"alert('ok!')\">删除流程</a>]"

            });
        }
        var newData={"total":total,"rows":rows};
        return newData;
    }

    //编辑流程模型
    function editModel(rowid) {
        window.open("modeler.html?modelId="+rowid);
    }
    //部署流程模型
    function deployModel(rowid,datagridId) {
        var datagrid=$("#"+datagridId);
        $.ajax({
            url:"service/model/deploy/"+rowid,
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
    //导出流程xml
    function exportModel(rowid,datagridId) {
        window.location.href="service/model/export/"+rowid;
    }


    // 筛选
    function jeecgEasyUIListsearchbox(value,name){
        var queryParams=$('#modelList').datagrid('options').queryParams;
        queryParams[name]=value;
        queryParams.searchfield=name;
        $('#modelList').datagrid('load');
    }
    // 刷新
    function reloadTable(){
        $('#modelList').datagrid('reload');
    }

    // 设置datagrid属性
    $('#modelList').datagrid({
        title: '流程模型管理',
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
        url:'model.do?datagrid',
        loadFilter: function(data){
            return getData(data);
        },
        toolbar:"#modelListtb"

    });
    //设置分页控件
    $('#modelList').datagrid('getPager').pagination({
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