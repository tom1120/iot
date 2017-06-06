/**
 *用于调试查询
 * Created by zhaoyipc on 2017/6/6.
 */
<script type="text/javascript">
var flag = false;
var fileitem="";
var fileKey="";
var serverMsg="";
var m = new Map();
$(function(){
    $('#file_upload').uploadify({
        buttonText:'上传文件',
        auto:false,
        progressData:'speed',
        multi:true,
        height:25,
        overrideEvents:['onDialogClose'],
        fileTypeDesc:'文件格式:',
        queueID:'filediv',
        fileTypeExts:'*.xml',
        fileSizeLimit:'15MB',
        swf:'plug-in/uploadify/uploadify.swf',
        uploader:'activitiController.do?deploy;jsessionid=AE929DE7A592D80720BAB24380938CB6',
        onUploadStart : function(file) { } ,
        onQueueComplete : function(queueData) {
            var win = frameElement.api.opener;
            win.reloadTable();
            win.tip(serverMsg);
            frameElement.api.close();
        },
        onUploadError : function(file,errorCode,errorMsg,errorString,swfuploadifyQueue) {
            alert( 'id: ' + file.id
                + ' - 索引: ' + file.index
                + ' - 文件名: ' + file.name
                + ' - 文件大小: ' + file.size
                + ' - 类型: ' + file.type
                + ' - 创建日期: ' + file.creationdate
                + ' - 修改日期: ' + file.modificationdate
                + ' - 文件状态: ' + file.filestatus
                + ' - 错误代码: ' + errorCode
                + ' - 错误描述: ' + errorMsg
                + ' - 简要错误描述: ' + errorString
                + ' - 出错的文件数: ' + swfuploadifyQueue.filesErrored
                + ' - 错误信息: ' + swfuploadifyQueue.errorMsg
                + ' - 要添加至队列的数量: ' + swfuploadifyQueue.filesSelected
                + ' - 添加至对立的数量: ' + swfuploadifyQueue.filesQueued
                + ' - 队列长度: ' + swfuploadifyQueue.queueLength); },onUploadSuccess : function(file, data, response) {
            var d=$.parseJSON(data);
            if(d==null&&typeof d==undefined){d=data};
            if(d.success){
                var win = frameElement.api.opener;
                serverMsg = d.msg;
            }
        },
        onFallback : function(){tip("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试")},
        onSelectError : function(file, errorCode, errorMsg){
            switch(errorCode) {
                case -100:
                    tip("上传的文件数量已经超出系统限制的"+$('#file_upload').uploadify('settings','queueSizeLimit')+"个文件！");
                    break;
                case -110:tip("文件 ["+file.name+"] 大小超出系统限制的"+$('#file_upload').uploadify('settings','fileSizeLimit')+"大小！");break;case -120:tip("文件 ["+file.name+"] 大小异常！");break;case -130:tip("文件 ["+file.name+"] 类型不正确！");break;}},
        onUploadProgress : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {
        }
    });
});
function upload() {
    $('#file_upload').uploadify('upload', '*');
    return flag;
}
function cancel() {
    $('#file_upload').uploadify('cancel', '*');
}
</script>