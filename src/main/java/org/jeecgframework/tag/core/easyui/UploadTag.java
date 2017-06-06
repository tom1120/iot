package org.jeecgframework.tag.core.easyui;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.jeecgframework.core.util.MutiLangUtil;

/**
 * 
 * 类描述：上传标签
 * 
 * 张代浩
 * @date： 日期：2012-12-7 时间：上午10:17:45
 * @version 1.0
 */
public class UploadTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	protected String id;// ID
	protected String uploader;//url
	protected String name;//控件名称
	protected String formData;//参数名称
	protected String extend="";//上传文件的扩展名	
	protected String buttonText="浏览";//按钮文本
	protected boolean multi=true;//是否多文件
	protected String queueID="filediv";//文件容器ID
	protected boolean dialog=true;//是否是弹出窗口模式
	protected String callback;
	protected boolean auto=false;//是否自动上传
	protected String onUploadSuccess;//上传成功处理函数
	protected boolean view=false;//生成查看删除链接
	protected String formId;//参数名称
	
	
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	
	private String fileSizeLimit = "15MB";//上传文件大小设置
	public String getFileSizeLimit() {
		return fileSizeLimit;
	}
	public void setFileSizeLimit(String fileSizeLimit) {
		this.fileSizeLimit = fileSizeLimit;
	}


	public void setView(boolean view) {
		this.view = view;
	}
	public void setOnUploadSuccess(String onUploadSuccess) {
		this.onUploadSuccess = onUploadSuccess;
	}
	public void setAuto(boolean auto) {
		this.auto = auto;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public void setDialog(boolean dialog) {
		this.dialog = dialog;
	}
	public void setQueueID(String queueID) {
		this.queueID = queueID;
	}
	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}
	public void setMulti(boolean multi) {
		this.multi = multi;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}
	public int doEndTag() throws JspTagException {
		JspWriter out = null;
		try {
			out = this.pageContext.getOut();
			out.print(end().toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				out.clear();
				out.close();
			} catch (Exception e2) {
			}
		}
		return EVAL_PAGE;
	}
	public StringBuffer end() {
		StringBuffer sb = new StringBuffer();
		if("pic".equals(extend))
		{
			extend="*.jpg;*,jpeg;*.png;*.gif;*.bmp;*.ico;*.tif";
		}
		if(extend.equals("office"))
		{
			extend="*.doc;*.docx;*.txt;*.ppt;*.xls;*.xlsx;*.html;*.htm";
		}
		sb.append("<link rel=\"stylesheet\" href=\"plug-in/uploadify/css/uploadify.css\" type=\"text/css\"></link>"+"\n");
		sb.append("<script type=\"text/javascript\" src=\"plug-in/uploadify/jquery.uploadify-3.1.js\"></script>"+"\n");
		sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/Map.js\"></script>"+"\n");
		sb.append("<script type=\"text/javascript\">"+"\n"
				+"var flag = false;"+"\n"
				+"var fileitem=\"\";"+"\n"
				+"var fileKey=\"\";"+"\n"
				+"var serverMsg=\"\";"+"\n"
				+"var m = new Map();"+"\n"
				+ "$(function(){"+"\n"
				+"$(\'#"+id+"\').uploadify({"+"\n"
				+"buttonText:\'"+MutiLangUtil.getMutiLangInstance().getLang(buttonText)+"\',"+"\n"
				+"auto:"+auto+","+"\n"
				+"progressData:\'speed\'," +"\n"
				+"multi:"+multi+","+"\n"
				+"height:25,"+"\n"
				+"overrideEvents:[\'onDialogClose\']," +"\n"
				+"fileTypeDesc:\'文件格式:\'," +"\n"
				+"queueID:\'"+queueID+"\',"+"\n"
				+"fileTypeExts:\'"+extend+"\',"+"\n"
				+"fileSizeLimit:\'"+fileSizeLimit+"\',"+"\n"
				+"swf:\'plug-in/uploadify/uploadify.swf\',"+"\n"
				+"uploader:\'"+getUploader()+"',\n"
				+"onUploadStart : function(file) { ");
				if (formData!=null) {
					String[] paramnames=formData.split(",");				
					for (int i = 0; i < paramnames.length; i++) {
						String paramname=paramnames[i];
						sb.append("var "+paramname+"=$(\'#"+paramname+"\').val();"+"\n");
					}				 
			        sb.append("$(\'#"+id+"\').uploadify(\"settings\", \"formData\", {"+"\n");
			        for (int i = 0; i < paramnames.length; i++) {
						String paramname=paramnames[i];
						if (i==paramnames.length-1) {
							sb.append("'"+paramname+"':"+paramname+""+"\n");	
						}else{
							sb.append("'"+paramname+"':"+paramname+","+"\n");
						}
					}
					//增加jsession sessionId
//					if(paramnames.length>0){
//						sb.append(",'sessionId':'"+pageContext.getSession().getId()+"'\n");
//					}else{
//						sb.append("'sessionId':'"+pageContext.getSession().getId()+"'\n");
//					}

			        sb.append("});"+"\n");
				}else if (formId!=null) {
					sb.append(" var o = {};"+"\n");
            		sb.append("    var _array = $('#"+formId+"').serializeArray();"+"\n");
            		sb.append("    $.each(_array, function() {"+"\n");
            		sb.append("        if (o[this.name]) {"+"\n");
            		sb.append("            if (!o[this.name].push) {"+"\n");
            		sb.append("                o[this.name] = [ o[this.name] ];"+"\n");
            		sb.append("            }"+"\n");
            		sb.append("            o[this.name].push(this.value || '');"+"\n");
            		sb.append("        } else {"+"\n");
            		sb.append("            o[this.name] = this.value || '';"+"\n");
            		sb.append("        }"+"\n");
            		sb.append("    });"+"\n");
            		sb.append("$(\'#"+id+"\').uploadify(\"settings\", \"formData\", o);"+"\n");
				};
		       sb.append("} ,"+"\n"
				+"onQueueComplete : function(queueData) { "+"\n");
				if(dialog)
				{
				sb.append("var win = frameElement.api.opener;"+"\n"
	            +"win.reloadTable();"+"\n"
	            +"win.tip(serverMsg);"+"\n"
	            +"frameElement.api.close();"+"\n");
				}
				else
				{
				  if(callback!=null)
				  sb.append(""+callback+"();"+"\n");
				}
				if(view)
				{
				sb.append("$(\"#viewmsg\").html(m.toString());"+"\n");
				sb.append("$(\"#fileKey\").val(fileKey);"+"\n");
				}
				sb.append("},"+"\n");


				//增加上传失败函数 add by zhaoyi
				sb.append("onUploadError : function(file,errorCode,errorMsg,errorString,swfuploadifyQueue) {\n" +
						"　　alert( 'id: ' + file.id\n" +
						"　　+ ' - 索引: ' + file.index\n" +
						"　　+ ' - 文件名: ' + file.name\n" +
						"　　+ ' - 文件大小: ' + file.size\n" +
						"　　+ ' - 类型: ' + file.type\n" +
						"　　+ ' - 创建日期: ' + file.creationdate\n" +
						"　　+ ' - 修改日期: ' + file.modificationdate\n" +
						"　　+ ' - 文件状态: ' + file.filestatus\n" +
						"　　+ ' - 错误代码: ' + errorCode\n" +
						"　　+ ' - 错误描述: ' + errorMsg\n" +
						"　　+ ' - 简要错误描述: ' + errorString\n" +
						"　　+ ' - 出错的文件数: ' + swfuploadifyQueue.filesErrored\n" +
						"　　+ ' - 错误信息: ' + swfuploadifyQueue.errorMsg\n" +
						"　　+ ' - 要添加至队列的数量: ' + swfuploadifyQueue.filesSelected\n" +
						"　　+ ' - 添加至对立的数量: ' + swfuploadifyQueue.filesQueued\n" +
						"　　+ ' - 队列长度: ' + swfuploadifyQueue.queueLength); },");

				//上传成功处理函数
				sb.append("onUploadSuccess : function(file, data, response) {"+"\n");
				sb.append("var d=$.parseJSON(data);"+"\n");
//		增加解析
				sb.append("if(d==null&&typeof d==undefined){d=data};"+"\n");

				if(view)
				{
				sb.append("var fileitem=\"<span id=\'\"+d.attributes.id+\"\'><a href=\'#\' onclick=openwindow(\'文件查看\',\'\"+d.attributes.viewhref+\"\',\'70%\',\'80%\') title=\'查看\'>\"+d.attributes.name+\"</a><img border=\'0\' onclick=confuploadify(\'\"+d.attributes.delurl+\"\',\'\"+d.attributes.id+\"\') title=\'删除\' src=\'plug-in/uploadify/img/uploadify-cancel.png\' widht=\'15\' height=\'15\'>&nbsp;&nbsp;</span>\";"+"\n");
				sb.append(" m=new Map(); "+"\n");
				sb.append("m.put(d.attributes.id,fileitem);"+"\n");
				sb.append("fileKey=d.attributes.fileKey;"+"\n");
				}
				if(onUploadSuccess!=null)
				{
				sb.append(onUploadSuccess+"(d,file,response);"+"\n");

				}
				sb.append("if(d.success){"+"\n");
				sb.append("var win = frameElement.api.opener;"+"\n");
//				sb.append("win.tip(d.msg);"+"\n");
				sb.append("serverMsg = d.msg;"+"\n");
				sb.append("}"+"\n");
				sb.append("},"+"\n");
				sb.append("onFallback : function(){tip(\"您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试\")},"+"\n");
				sb.append("onSelectError : function(file, errorCode, errorMsg){"+"\n");
				sb.append("switch(errorCode) {"+"\n");
				sb.append("case -100:"+"\n");
				sb.append("tip(\"上传的文件数量已经超出系统限制的\"+$(\'#"+id+"\').uploadify(\'settings\',\'queueSizeLimit\')+\"个文件！\");"+"\n");
				sb.append("break;"+"\n");
				sb.append("case -110:"
				+"tip(\"文件 [\"+file.name+\"] 大小超出系统限制的\"+$(\'#"+id+"\').uploadify(\'settings\',\'fileSizeLimit\')+\"大小！\");"
				+"break;"
				+"case -120:"
				+"tip(\"文件 [\"+file.name+\"] 大小异常！\");"
				+"break;"
				+"case -130:"
				+"tip(\"文件 [\"+file.name+\"] 类型不正确！\");"
				+"break;"
				+"}");
		       sb.append("},"+"\n"
				+"onUploadProgress : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) { "+"\n"
				//+"tip('<span>文件上传成功:'+totalBytesUploaded/1024 + ' KB 已上传 ,总数' + totalBytesTotal/1024 + ' KB.</span>');"  	  	             
				+"}"
	   			+"});"
				+"});"+"\n"
				+"function upload() {"+"\n"
				+"	$(\'#"+id+"\').uploadify('upload', '*');"+"\n"
				+"		return flag;"+"\n"
				+"}"+"\n"
				+"function cancel() {"+"\n"
				+"$(\'#"+id+"\').uploadify('cancel', '*');"+"\n"
				+"}"+"\n"
				+"</script>");	
		       sb.append("<span id=\""+id+"span\"><input type=\"file\" name=\""+name+"\" id=\""+id+"\" /></span>"+"\n");
		       if(view)
		       {
		       sb.append("<span id=\"viewmsg\"></span>"+"\n");
		       sb.append("<input type=\"hidden\" name=\"fileKey\" id=\"fileKey\" />"+"\n");
		       }
		        
		return sb;
	}
	
	/**
	 * 获取上传路径,修改jsessionid传不到后台的bug jueyue --- 20130916
	 * @return
	 */
	private String getUploader() {
		return uploader+"&sessionId="+pageContext.getSession().getId();
//		return uploader+";jsessionid="+pageContext.getSession().getId();

	}
	
	public void setId(String id) {
		this.id = id;
	}
	public void setFormData(String formData) {
		this.formData = formData;
	}
	public void setExtend(String extend) {
		this.extend = extend;
	}

	 
	
}
