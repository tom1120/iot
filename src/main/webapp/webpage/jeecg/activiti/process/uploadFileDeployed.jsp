<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>文件列表</title>
    <t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" layout="div" dialog="true" beforeSubmit="upload" multipart="true">

    <fieldset class="step">
        <%--<div class="form">--%>
            <%--<label class="Validform_label"> 文件标题: </label>--%>
            <%--<input name="documentTitle" id="documentTitle" datatype="s3-50" value="${doc.documentTitle}" type="text">--%>
            <%--<span class="Validform_checktip">标题名称在3~50位字符,且不为空</span>--%>
        <%--</div>--%>
            <legend>部署新流程</legend>
            <div><b>支持文件格式：</b>zip、bar、bpmn、bpmn20.xml</div>
            <div><b>部署说明：</b>部署只生成流程定义，不生成流程模型</div>
        <div class="form">
            <%--<t:upload name="file" buttonText="上传文件" uploader="activitiController.do?deploy" extend="*.xml" multi="false" id="file"></t:upload>--%>
            <t:upload name="file" buttonText="上传文件" uploader="activitiController.do?deploy" extend="*.xml;*.bpmn;*.zip;*.bar;*.bpmn20.xml" multi="false" id="file" onUploadSuccess="uploadsuccess"></t:upload>
                <%--<form action="activitiController.do?deploy" method="post" enctype="multipart/form-data">--%>
                    <%--<input type="file" name="file" />--%>
                    <%--<input type="submit" value="Submit" />--%>
                <%--</form>--%>
        </div>
        <div class="form" id="filediv" style="height: 50px"></div>
    </fieldset>
</t:formvalid>

</body>
</html>
<script type="text/javascript">
    //上传成功
    function uploadsuccess(d,file,response) {
        console.debug(d);
        if(d.success){
            addOneTab("流程模板", "activitiController.do?processList");
        }
    }
</script>