<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>欢迎访客界面</title>
  <meta charset="UTF-8"/>  
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="vistor page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=path%>/dwr/engine.js"></script>  
	<script type="text/javascript" src="<%=path%>/dwr/util.js"></script>  
	<script type="text/javascript" src="<%=path%>/dwr/interface/MessagePush.js"></script>
	<script src="<%=path%>/plug-in/jquery/jquery-1.8.0.min.js"></script>
		<script type="text/javascript">  
        //通过该方法与后台交互，确保推送时能找到指定用户
         function onPageLoad(){  
            var userId = "userId";  
            MessagePush.onPageLoad(userId);
         }
         //推送信息  
         function showVisitorName(autoMessage){  
//            alert(autoMessage);
        	 //var strs= new Array(); //定义一数组 
        	 //strs=autoMessage.split("#"); //字符分割 
        	 var jsonstring=JSON.parse(autoMessage);
        	 var visistorname=jsonstring.visitorname;
//        	 var headimgurl=jsonstring.headimgurl;
//        	 var sex=jsonstring.sex;
//      	 alert(headimgurl);
//        	 var controllerNum=jsonstring.controllerNum;
        	 
//					 $("#img1").attr("src",headimgurl);
        	 $("#visitorname").text(visistorname);

//        	 $("#visitorname").html(visistorname);

//        	 $("#sex").html(sex);
        	 
         }  
  	    </script>
				<style type="text/css">
					body{
						margin-left: 0px;
						margin-top: 0px;
						margin-right: 0px;
						margin-bottom: 0px;
						
					}
/*					#visitorname{
						position:fixed;left:60%;top:75%;margin-left:width/2;margin-top:height/2;
						font-family: "Microsoft YaHei" ! important;
						color:#4b1413;
						font-size: 150px;
						
						
					}*/
					table{
						position:fixed;left:50%;top:55%;margin-left:width/2;margin-top:height/2;
						font-family: "Microsoft YaHei" ! important;
						color:#4b1413;
						font-size: 150px;
					}
					
/*					#headurl{
						position:fixed;left:63%;top:50%;margin-left:width/2;margin-top:height/2;
						
					}*/
					#img1{
									width:200px;
									height:200px;
									border-radius:300px;
					}
				</style>
  	   
  	   
  </head>
  
  <body onload="onPageLoad();dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);;">
  		<div id="Layer1" style="position:absolute; width:100%; height:100%; z-index:-1">    
			<img src="plug-in/weixin/visitor/images/index.jpg" height="100%" width="100%"/></div>
<!--			<div id="headurl"><img id="img1"/></div>-->
      
			<table border="0" cellspacing="" cellpadding="">
				<tr>
					<td></td>
					<td><div id="headurl"><img id="img1"/></div></td>
					
				</tr>
				<tr>
					<td><div id="visitorname"></div></td>
					<td><p>&nbsp;</p></td>
					<td><div id="sex"></div></td>
				</tr>
			</table>
			
			
      
  </body>
</html>
