<%--
  Created by IntelliJ IDEA.
  User: zhaoyipc
  Date: 2017/7/14
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>



<div style="padding: 3px; height: 25px; width:900px;" class="datagrid-toolbar">
    <a id="addBtn" href="#">添加</a>
    <a id="delBtn" href="#">删除</a>
    <a id="saveBtn" href="#">保存</a>
</div>

<t:formvalid formid="formobj" layout="table" dialog="true">
<table border="0" cellpadding="2" cellspacing="0" id="deviceInfo_table">
    <tr bgcolor="#E6E6E6">
        <td align="center" bgcolor="#EEEEEE">序号</td>
        <td align="left" bgcolor="#EEEEEE">设备ID</td>
        <td align="left" bgcolor="#EEEEEE">设备名称</td>
        <td align="left" bgcolor="#EEEEEE">设备密钥</td>
        <td align="left" bgcolor="#EEEEEE">创建时间</td>
        <td align="left" bgcolor="#EEEEEE">修改时间</td>
        <td align="left" bgcolor="#EEEEEE">产品key</td>

    </tr>
    <tbody id="add_deviceInfo_table">
    <c:if test="${fn:length(deviceInfoList)  <= 0 }">
        <tr>
            <td align="center"><input style="width: 20px;" type="checkbox" name="ck" /></td>
            <td align="left"><input name="deviceInfoList[0].deviceId" maxlength="100" type="text" value="" readonly="readonly"
                                    style="width: 220px;"></td>
            <td align="left"><input name="deviceInfoList[0].deviceName" maxlength="100" type="text" value="" style="width: 120px;"></td>
            <td align="left">	<input name="deviceInfoList[0].deviceSecret" maxlength="100" type="text" value=""  style="width:120px;" readonly="readonly" ></td>
            <td align="left"><input name="deviceInfoList[0].gmtCreate" maxlength="100" type="text" value="" readonly="readonly"
                                    style="width: 120px;"></td>
            <td align="left"><input name="deviceInfoList[0].gmtModified" maxlength="100" type="text" value="" readonly="readonly"
                                    style="width: 120px;"></td>
            <td align="left"><input name="deviceInfoList[0].productKey" maxlength="100" type="text" value="${productKey}" readonly="readonly"
                                    style="width: 120px;"></td>
        </tr>
    </c:if>
    <c:if test="${fn:length(deviceInfoList)  > 0 }">
        <c:forEach items="${deviceInfoList}" var="poVal" varStatus="stuts">
            <tr>
                <td align="center"><input style="width: 20px;" type="checkbox" name="ck" /></td>
                <td align="left"><input name="deviceInfoList[${stuts.index }].deviceId" maxlength="100" type="text" readonly="readonly"
                                        value="${poVal.deviceId}" style="width: 220px;"></td>
                <td align="left"><input  name="deviceInfoList[${stuts.index }].deviceName" maxlength="100" type="text"
                                        value="${poVal.deviceName }" style="width: 120px;"></td>
                <td align="left">	<input name="deviceInfoList[${stuts.index }].deviceSecret" maxlength="100" type="text" value="${poVal.deviceSecret }"  style="width:120px;" readonly="readonly"></td>
                <td align="left"><input name="deviceInfoList[${stuts.index }].gmtCreate" maxlength="100" type="text" readonly="readonly"
                                        value="${poVal.gmtCreate }" style="width: 120px;"></td>
                <td align="left"><input  name="deviceInfoList[${stuts.index }].gmtModified" maxlength="100" type="text" readonly="readonly"
                                        value="${poVal.gmtModified }" style="width: 120px;"></td>
                <td align="left"><input  name="deviceInfoList[${stuts.index }].productKey" maxlength="100" type="text" readonly="readonly"
                                        value="${poVal.productKey }" style="width: 120px;"></td>

            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>
</t:formvalid>

<script type="text/javascript">
    $('#addBtn').linkbutton({
        iconCls: 'icon-add'
    });
    $('#delBtn').linkbutton({
        iconCls: 'icon-remove'
    });
    $('#saveBtn').linkbutton({
        iconCls: 'icon-save'
    });



    $('#addBtn').bind('click', function(){
        var product_key='${productKey}';
        var tr =  $("#add_deviceInfo_table_template tr",parent.document).clone();
        $("input[name='deviceInfoList[#index#].productKey']",tr).val(product_key);
//        console.debug(product_key);
        $("#add_deviceInfo_table").append(tr);
        parent.window.resetTrNum('add_deviceInfo_table');
    });
    $('#delBtn').bind('click', function(){
        $("#add_deviceInfo_table").find("input:checked").parent().parent().remove();
        parent.window.resetTrNum('add_deviceInfo_table');
    });
    //保存设备信息
    $("#saveBtn").bind('click',function () {
       $.ajax({
           url:'productInfoController.do?saveDeviceInfo',
           type:'post',
           data:$("#formobj").serializeArray(),
           async:false,
           dataType:'json',
           success:function (data,textStatus) {
               var d=$.parseJSON(data);
               if(d==null||undefined==typeof data){d=data};

               if(d.success){
                   refreshTabs();
                   tip(d.msg);
               }else{
                   tip(d.msg);
               }
           },
           error:function (xmlHttpRequest,textStatus,errorThrow) {

           }
       });
    });


    //easyUI刷新tab
    function refreshTabs(){
        var selectTab = $('#devicesInfoTabs',parent.document).tabs('getSelected');

        var url =$(selectTab.panel('options').content).attr('src');

        $('#tt').tabs('update', {
            tab: selectTab,
            options: {
                href: url
            }
        })
    }




    $(document).ready(function(){
        $(".datagrid-toolbar").parent().css("width","auto");
        //将表格的表头固定
        $("#deviceInfo_table").createhftable({
            height:'200px',
            width:'auto',
            fixFooter:false
        });
    });


</script>