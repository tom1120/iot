<%--
  Created by IntelliJ IDEA.
  User: zhaoyipc
  Date: 2017/6/30
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
    $('#addBtn').linkbutton({
        iconCls: 'icon-add'
    });
    $('#delBtn').linkbutton({
        iconCls: 'icon-remove'
    });
    $('#pauseBtn').linkbutton({
        iconCls: 'icon-pause'
    });
    $('#resumeBtn').linkbutton({
        iconCls: 'icon-resume'
    });


    $('#addBtn').bind('click', function(){
        var tr =  $("#add_cronTask_table_template tr").clone();
        $("#add_cronTask_table").append(tr);
        resetTrNum('add_cronTask_table');
    });
    $('#delBtn').bind('click', function(){
        $("#add_cronTask_table").find("input:checked").parent().parent().remove();
        resetTrNum('add_cronTask_table');
    });


    $("#pauseBtn").bind('click',function () {
//        var selectRow=$("#add_cronTask_table").find("input:checked").parent().parent();



        var ids = '';
        $('input[type="checkbox"]:checked').each(function(){
            ids += $(this).val() + ',';
        })
        ids = ids.substring(0,ids.length-1);

        $.ajax({
            url:'quartzTaskController.do?pauseTrigger',
            type:'post',
            data:{'ids':ids},
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

    })


    $("#resumeBtn").bind('click',function () {
//        var selectRow=$("#add_cronTask_table").find("input:checked").parent().parent();



        var ids = '';
        $('input[type="checkbox"]:checked').each(function(){
            ids += $(this).val() + ',';
        })
        ids = ids.substring(0,ids.length-1);

        $.ajax({
            url:'quartzTaskController.do?resumeTrigger',
            type:'post',
            data:{'ids':ids},
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

    })

    //easyUI刷新tab
    function refreshTabs(){
        var selectTab = $('#tt').tabs('getSelected');

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
        $("#cronTask_table").createhftable({
            height:'200px',
            width:'auto',
            fixFooter:false
        });
//        放在主页面内，捕捉不到，放到当前页面
        $("input[name='cronTaskList[#index#].jobName']").val($("#jobName").val());
        $("input[name='cronTaskList[0].jobName']").val($("#jobName").val());

        $("input[name='cronTaskList[#index#].jobGroup']").val($("select[name='jobGroup'] option:selected").val());
        $("input[name='cronTaskList[0].jobGroup']").val($("select[name='jobGroup'] option:selected").val());

    });
</script>

<div style="padding: 3px; height: 25px; width:900px;" class="datagrid-toolbar">
    <a id="addBtn" href="#">添加</a>
    <a id="delBtn" href="#">删除</a>
    <a id="pauseBtn" href="#">暂停</a>
    <a id="resumeBtn" href="#">恢复</a>
</div>
<table border="0" cellpadding="2" cellspacing="0" id="cronTask_table">
    <tr bgcolor="#E6E6E6">
        <td align="center" bgcolor="#EEEEEE">序号</td>
        <td align="left" bgcolor="#EEEEEE" style="display:none;">调度器名称</td>
        <td align="left" bgcolor="#EEEEEE">触发器名称</td>
        <td align="left" bgcolor="#EEEEEE">触发器组</td>
        <td align="left" bgcolor="#EEEEEE" style="display:none">任务名称</td>
        <td align="left" bgcolor="#EEEEEE" style="display:none">任务组</td>
        <td align="left" bgcolor="#EEEEEE">触发器说明</td>
        <td align="left" bgcolor="#EEEEEE">cron表达式</td>
        <td align="left" bgcolor="#EEEEEE">优先级</td>

        <td align="left" bgcolor="#EEEEEE">下次激活时间</td>
        <td align="left" bgcolor="#EEEEEE">上次激活时间</td>
        <td align="left" bgcolor="#EEEEEE">触发器状态</td>
        <td align="left" bgcolor="#EEEEEE">触发器类型</td>
        <td align="left" bgcolor="#EEEEEE">开始时间</td>
        <td align="left" bgcolor="#EEEEEE">结束时间</td>
        <td align="left" bgcolor="#EEEEEE">calender_name</td>
        <td align="left" bgcolor="#EEEEEE">misfire_instr</td>

        <%--<td align="left" bgcolor="#EEEEEE">job_data</td>--%>

    </tr>
    <tbody id="add_cronTask_table">
    <c:if test="${fn:length(cronTaskList)  <= 0 }">
        <tr>
            <td align="center"><input style="width: 20px;" type="checkbox" name="ck"/></td>
            <td align="left" style="display:none;"><input nullmsg="请输入任务触发器调度器名称！" datatype="s1-50" errormsg="任务触发器名称为1到50位" name="cronTaskList[0].schedName" maxlength="50" type="text"
                                    value="schedulerFactory"
                                    style="width: 220px;"></td>
            <td align="left"><input nullmsg="请输入任务触发器名称！" datatype="s1-50" errormsg="任务触发器名称为1到50位" name="cronTaskList[0].triggerName" maxlength="50" type="text"
                                    value="" style="width: 120px;"></td>

            <td align="left"><input nullmsg="请输入任务触发器组名称！" datatype="s1-50" errormsg="任务触发器名称为1到50位" name="cronTaskList[0].triggerGroup" maxlength="50" type="text"
                                    value=""
                                    style="width: 120px;"></td>
            <td align="left" style="display:none"><input  type="hidden"   name="cronTaskList[0].jobName" type="text"
                                                          value=""
                                                          style="width: 0px;"></td>
            <td align="left" style="display:none"><input  type="hidden"   name="cronTaskList[0].jobGroup"  type="text"
                                                          value=""
                                                          style="width: 0px;"></td>
            <td align="left"><input  name="cronTaskList[0].description" maxlength="50" type="text"
                                    value=""
                                    style="width: 120px;"></td>
            <td align="left"><input name="cronTaskList[0].qrtzCronTriggers.cronExpression" maxlength="200" type="text"
                                    value="" style="width: 120px;"></td>
            <td align="left"><input nullmsg="请输入任务触发器优先级！" datatype="n"  errormsg="优先级必须为数字" name="cronTaskList[0].priority" maxlength="10" type="text"
                                    value=""
                                    style="width: 120px;"></td>

            <td align="left"><input  name="cronTaskList[0].nextFireTime"   type="text" readonly="readonly"
                                     value=""
                                     style="width: 120px;"></td>
            <td align="left"><input  name="cronTaskList[0].prevFireTime"   type="text" readonly="readonly"
                                     value=""
                                     style="width: 120px;"></td>

            <td align="left"><input name="cronTaskList[0].triggerState"   type="text" readonly="readonly"
                                    value=""
                                    style="width: 120px;"></td>
            <td align="left"><input  name="cronTaskList[0].triggerType" type="text" readonly="readonly"
                                     value=""
                                     style="width: 120px;"></td>
            <%--<td align="left"><input class="Wdate"  name="cronTaskList[0].startTime"  type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" ignore="ignore"
                                     errormsg="日期格式不正确！"
                                     value=""
                                     style="width: 120px;"></td>--%>
            <td align="left"><input  name="cronTaskList[0].endTime"   type="text" readonly="readonly"
                                     value=""
                                     style="width: 120px;"></td>
            <td align="left"><input  name="cronTaskList[0].calendarName"  type="text" readonly="readonly"
                                     value=""
                                     style="width: 120px;"></td>
            <td align="left"><input  name="cronTaskList[0].misfireInstr"  type="text" readonly="readonly"
                                     value=""
                                     style="width: 120px;"></td>

<%--            <td align="left"><input name="cronTaskList[0].jobData" readonly="readonly" maxlength="200" type="text"
                                    value="" style="width: 120px;"></td>--%>

        </tr>
    </c:if>
    <c:if test="${fn:length(cronTaskList)  > 0 }">
        <c:forEach items="${cronTaskList}" var="poVal" varStatus="stuts">
            <tr>
                <td align="center"><input style="width: 20px;" type="checkbox" name="ck" value="${poVal.schedName}$${poVal.triggerName}$${poVal.triggerGroup}"/></td>
                <td align="left" style="display:none;"><input nullmsg="请输入任务触发器调度器名称！" datatype="s1-50" errormsg="任务触发器名称为1到50位" name="cronTaskList[${stuts.index }].schedName" maxlength="50" type="text"
                                        value="${poVal.schedName}"
                                        style="width: 220px;"></td>
                <td align="left"><input nullmsg="请输入任务触发器名称！" datatype="s1-50" errormsg="任务触发器名称为1到50位" name="cronTaskList[${stuts.index }].triggerName" maxlength="50" type="text"
                                        value="${poVal.triggerName}" style="width: 120px;"></td>

                <td align="left"><input nullmsg="请输入任务触发器组名称！" datatype="s1-50" errormsg="任务触发器名称为1到50位" name="cronTaskList[${stuts.index }].triggerGroup" maxlength="50" type="text"
                                        value="${poVal.triggerGroup}"
                                        style="width: 120px;"></td>
                <td align="left" style="display:none"><input  type="hidden"   name="cronTaskList[${stuts.index }].jobName" type="text"
                                        value="${poVal.jobName}"
                                        style="width: 0px;"></td>
                <td align="left" style="display:none"><input  type="hidden"   name="cronTaskList[${stuts.index }].jobGroup"  type="text"
                                        value="${poVal.jobGroup}"
                                        style="width: 0px;"></td>
                <td align="left"><input  name="cronTaskList[${stuts.index }].description" maxlength="50" type="text"
                                        value="${poVal.description}"
                                        style="width: 120px;"></td>
                <td align="left"><input name="cronTaskList[${stuts.index }].qrtzCronTriggers.cronExpression" maxlength="200" type="text"
                                        value="${poVal.qrtzCronTriggers.cronExpression}" style="width: 120px;"></td>
                <td align="left"><input nullmsg="请输入任务触发器优先级！" datatype="n"  errormsg="优先级必须为数字" name="cronTaskList[${stuts.index }].priority" maxlength="10" type="text"
                                        value="${poVal.priority}"
                                        style="width: 120px;"></td>

                <td align="left"><input  name="cronTaskList[${stuts.index }].nextFireTime"   type="text" readonly="readonly"
                                         value="${poVal.nextFireTime}"
                                         style="width: 120px;"></td>
                <td align="left"><input  name="cronTaskList[${stuts.index }].prevFireTime"   type="text" readonly="readonly"
                                         value="${poVal.prevFireTime}"
                                         style="width: 120px;"></td>

                <td align="left"><input name="cronTaskList[${stuts.index }].triggerState"   type="text" readonly="readonly"
                                        value="${poVal.triggerState}"
                                        style="width: 120px;"></td>
                <td align="left"><input  name="cronTaskList[${stuts.index }].triggerType" type="text" readonly="readonly"
                                         value="${poVal.triggerType}"
                                         style="width: 120px;"></td>
                <%--<td align="left"><input class="Wdate" name="cronTaskList[${stuts.index }].startTime"  type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" ignore="ignore"
                                         errormsg="日期格式不正确！"
                                         value="${poVal.startTime}"
                                         style="width: 120px;"></td>--%>
                <td align="left"><input  name="cronTaskList[${stuts.index }].endTime"   type="text" readonly="readonly"
                                         value="${poVal.endTime}"
                                         style="width: 120px;"></td>
                <td align="left"><input  name="cronTaskList[${stuts.index }].calendarName"  type="text" readonly="readonly"
                                         value="${poVal.calendarName}"
                                         style="width: 120px;"></td>
                <td align="left"><input  name="cronTaskList[${stuts.index }].misfireInstr"  type="text" readonly="readonly"
                                         value="${poVal.misfireInstr}"
                                         style="width: 120px;"></td>

<%--                <td align="left"><input name="cronTaskList[${stuts.index }].jobData" readonly="readonly" maxlength="200" type="text"
                                        value="${poVal.jobData}" style="width: 120px;"></td>--%>



            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>
