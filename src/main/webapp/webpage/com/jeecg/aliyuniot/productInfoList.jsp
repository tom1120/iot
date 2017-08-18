<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<script>
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
</script>

<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="productInfoList" title="阿里云iot产品表" actionUrl="productInfoController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="产品名称" field="productname"   width="120"></t:dgCol>
   <t:dgCol title="产品key" field="productkey"   width="120"></t:dgCol>
   <t:dgCol title="产品类型id" field="catid"   width="120"></t:dgCol>
   <t:dgCol title="产品描述" field="productdesc"   width="120"></t:dgCol>
   <%--<t:dgCol title="创建时间" field="gmtcreate"   width="120"></t:dgCol>--%>
   <%--<t:dgCol title="修改时间" field="gmtmodified"   width="120"></t:dgCol>--%>
   <%--<t:dgCol title="产品密钥" field="productsecret"   width="120"></t:dgCol>--%>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="productInfoController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
      <t:dgFunOpt title="同步设备信息" funname="syncDeviceInfo(productkey)" urlclass="ace_button" urlfont="fa-save"></t:dgFunOpt>
   <t:dgToolBar title="录入" icon="icon-add" url="productInfoController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="productInfoController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="productInfoController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>

 </div>


<!-- 底部 -->
<div region="south" border="false" style="height: 300px; overflow: hidden;" id="southDiv">
    <t:tabs id="devicesInfoTabs" iframe="false" tabPosition="top" fit="false">
        <t:tab title="产品设备信息" id="deviceInfo"  heigth="100%" width="100%" icon="" iframe=""></t:tab>
    </t:tabs>
</div>

<!-- 添加 产品设备明细 模版 -->
<table style="display: none">
    <tbody id="add_deviceInfo_table_template">
    <tr>
        <td align="center"><input style="width: 20px;" type="checkbox" name="ck" /></td>
        <td align="left"><input name="deviceInfoList[#index#].deviceId" maxlength="100" type="text" value="" readonly="readonly"
                                style="width: 220px;"></td>
        <td align="left"><input name="deviceInfoList[#index#].deviceName" maxlength="100" type="text" value="" style="width: 120px;"></td>
        <td align="left">	<input name="deviceInfoList[#index#].deviceSecret" maxlength="100" type="text" value=""  style="width:120px;" readonly="readonly" ></td>
        <td align="left"><input name="deviceInfoList[#index#].gmtCreate" maxlength="100" type="text" value="" readonly="readonly"
                                style="width: 120px;"></td>
        <td align="left"><input name="deviceInfoList[#index#].gmtModified" maxlength="100" type="text" value="" readonly="readonly"
                                style="width: 120px;"></td>
        <td align="left"><input name="deviceInfoList[#index#].productKey" maxlength="100" type="text" value="" readonly="readonly"
                                style="width: 120px;"></td>

    </tr>
    </tbody>
</table>


<script>
    $(document).ready(function(){

        $('#productInfoList').datagrid({
            onClickRow: function(rowIndex, rowData){
                var url = "productInfoController.do?goInfo";
                if (rowData) {
                    url += '&id=' + rowData.id;
                    $("iframe").attr("src",url);
                    $("iframe").attr("scrolling","auto");
                }else{
                }
            }
        });
    });
    //同步设备信息
    function syncDeviceInfo(productkey) {
        $.ajax({
            url:"productInfoController.do?syncDeviceInfo",
            data:{"productkey":productkey},
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