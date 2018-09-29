package cn.itcast.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 重定向练习
 * 相关方法
 * 设置状态码为302  response.setState(302)
 * 设置响应头       response.setHead("location","/day15/responseDemo2")
 */
@WebServlet("/responseDemo1")
public class ResponseDemo1 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("demo1------------------");
        response.setStatus(302);
//        response.setHeader("location","/day15/responseDemo2");
        //简单重定向方法
        response.sendRedirect(request.getContextPath()+"/responseDemo3");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
