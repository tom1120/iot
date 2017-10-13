<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="wifiprobeSysParamList" title="wifi探针参数设置" actionUrl="wifiprobeSysParamController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="缓存数" field="cacheNumber"   width="120"></t:dgCol>
   <t:dgCol title="临界强度值" field="rssi"   width="120"></t:dgCol>
      <t:dgCol title="最小临界值" field="rssiMin"   width="120"></t:dgCol>
   <t:dgCol title="打开客户端后延时" field="afterOpenClientDelayTime"   width="120"></t:dgCol>
   <t:dgCol title="关闭客户端前延时" field="beforeCloseClientDelayTime"   width="120"></t:dgCol>
   <t:dgCol title="系统参数刷新时间" field="refreshTime"   width="120"></t:dgCol>
      <t:dgCol title="是否默认参数" field="isDefault" width="120"></t:dgCol>
   <t:dgCol title="备注" field="note"   width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="wifiprobeSysParamController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgFunOpt title="刷新" funname="refresh(id)"  urlclass="ace_button"  urlfont="fa-save"/>
   <t:dgToolBar title="录入" icon="icon-add" url="wifiprobeSysParamController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="wifiprobeSysParamController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="wifiprobeSysParamController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>

<script>
    function refresh(id) {
//        console.debug(id);
        $.ajax({
            url:"wifiprobeSysParamController.do?refresh",
            data:{"id":id},
            type:"post",
            dataType:"json",
            async:false,
            success:function (data,textStatus) {
                var d=$.parseJSON(data);
                if(d==null||undefined==typeof data){d=data};

                if(d.success){
                    tip(d.msg);
                }else{
                    tip(d.msg);
                }
            },
            error:function (xmlHttpRequest,textStatus,errorThrow) {

            }

        });

    }
</script>