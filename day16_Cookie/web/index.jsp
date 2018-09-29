<%@ page import="cn.itcast.utils.CookieUtils" %><%--
  Created by IntelliJ IDEA.
  User: 49600
  Date: 2018/8/3
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <%

          Cookie[] cookies = request.getCookies();
          Cookie myCookie = CookieUtils.judgeFirst(cookies,"rem");
          if(myCookie==null){//第一次访问
                out.write("you first visit mysite-----------");
          }else{
              //不是第一次访问
              String lastTime = myCookie.getValue();
              out.write("you visit mysite lastTime:"+lastTime);
          }
        %>
  </body>

</html>
