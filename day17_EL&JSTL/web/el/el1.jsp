<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 49600
  Date: 2018/8/6
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  ${3>2}  <br>
  <%-- \${} 表示忽略当前表达式} --%>
  \${3>2}<br>
<%-- el表达式只能从 域对象获取值  ${域名.键名}--%>


  <%
      request.setAttribute("name","张三");
      request.setAttribute("gender","男");
      session.setAttribute("age","66");
      List list = new ArrayList();
      list.add("呵呵");
      list.add(66);
      request.setAttribute("list",list);
      request.setAttribute("??","??");
  %>
  ${requestScope.name}<br>
  ${sessionScope.age}<br>
  ${pageContext.request}<br>
  ${list[0]}<br>
  ${list[1]} <br>
  ${empty list}<br>
  ${not empty list}<br>


</body>
</html>
