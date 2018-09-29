<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/6
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" width="500" align="center">
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>密码</th>
        <th>地址</th>
    </tr>
    <%--数据行--%>
    <c:forEach items="${users}" var="user" varStatus="s">


            <tr bgcolor="red">
                <td>${s.count}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.address}</td>
            </tr>
    </c:forEach>

</table>

</body>
</html>
