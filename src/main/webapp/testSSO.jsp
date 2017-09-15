<%--
  Created by IntelliJ IDEA.
  User: zhaoyipc
  Date: 2017/9/13
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
    <script>
        function formSubmit() {
            document.getElementById("fLogin").submit();
        }
    </script>
</head>
<body>
<form name="fLogin" id="fLogin" method="post" action="">
    <input type="hidden" name="accessuserid" value=""/>
    <input type="hidden" name="employeeid" value=""/>
    <input type="hidden" name="initpage" value=""/>
    <input type="hidden" name="appid" value=""/>
    <input type="hidden" name="token" value=""/>
    <script>
        formSubmit();
    </script>
</form>
</body>
</html>
