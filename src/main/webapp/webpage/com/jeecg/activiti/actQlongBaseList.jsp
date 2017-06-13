<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="actQlongBaseList" title="基础信息" actionUrl="actQlongBaseController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="流程名称" field="activitiName"  width="120"></t:dgCol>
   <t:dgCol title="流程key" field="activitiKey"   width="120"></t:dgCol>
   <t:dgCol title="流程类型" field="activitiType"  dictionary="activitiType"  width="120"></t:dgCol>
   <t:dgCol title="是否部署" field="isDeployed"  dictionary="isornot" width="120"></t:dgCol>
   <t:dgCol title="备注" field="note"   width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="actQlongBaseController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="actQlongBaseController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="actQlongBaseController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="actQlongBaseController.do?addorupdate" funname="detail"></t:dgToolBar>
      <t:dgToolBar title="创建流程" url="actQlongBaseController.do?modelar" funname="modelar"></t:dgToolBar>
      <t:dgToolBar title="编辑流程" url="modeler.html?modelId" funname="editmodelar"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>

<script>
    <%--'流程设计器','actQlongBaseController.do?modelar','actQlongBaseList',null,null--%>
    function modelar(operation_name,url,id,width,height) {
        gridname=id;
        var rowsData = $('#'+id).datagrid('getSelections');
        if (!rowsData || rowsData.length==0) {
            tip('请选择编辑项目');
            return;
        }
        if (rowsData.length>1) {
            tip('请选择一条记录再编辑');
            return;
        }
        var data= "key="+rowsData[0].activitiKey;

        $.ajax({
            url:"actQlongBaseController.do?checkModelKey=newkey",
            type:"post",
            data:data,
            async:false,
            dataType:"json",
            success:function (data, textStatus) {
                var d=$.parseJSON(data);
                if(d==null||"undefined"==typeof d){d=data}
                if(d.success){
                    window.open(url+"&name="+rowsData[0].activitiName+"&key="+rowsData[0].activitiKey+"&description="+rowsData[0].note+"&category="+rowsData[0].activitiType);
                }else {
                    tip(d.msg);
                }

            },
            error:function (XMLHttpRequest, textStatus, errorThrown) {

            }

        }
        )




    }

    function editmodelar(operation_name,url,id,width,height) {
        gridname=id;
        var rowsData = $('#'+id).datagrid('getSelections');
        if (!rowsData || rowsData.length==0) {
            tip('请选择编辑项目');
            return;
        }
        if (rowsData.length>1) {
            tip('请选择一条记录再编辑');
            return;
        }

        $.ajax({
            url:"actQlongBaseController.do?checkModelKey=editkey",
            type:"post",
            data:"key="+rowsData[0].activitiKey,
            async:false,
            dataType:"json",
            success:function (data,textStatus) {
                var d=$.parseJSON(data);
                if(d==null||"undefined"==typeof d){d=data}
                if(d.success){
                    window.open(url+"="+d.obj);
                }else {
                    tip(d.msg);
                }

            },
            error:function (XMLHttpRequest, textStatus, errorThrown) {

            }

        })


    }



</script>