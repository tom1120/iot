<%--
  Created by IntelliJ IDEA.
  User: zhaoyipc
  Date: 2017/9/13
  Time: 13:50
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
        <input type="hidden" name="AppKey" value=""/>
        <input type="hidden" name="Ticket" value=""/>
        <input type="hidden" name="EmployeeID" value=""/>
        <input type="hidden" name="TA" value=""/>
        <input type="hidden" name="Cost1" value=""/>
        <input type="hidden" name="Cost2" value=""/>
        <input type="hidden" name="Cost3" value=""/>
        <script>
            formSubmit();
        </script>
    </form>




</body>
</html>
