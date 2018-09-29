package cn.itcast.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginServlet2")
public class LoginServlet2 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取参数  username  password checkCode
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");
        //3.获取验证码
        HttpSession session = request.getSession();
        String checkCode_session = (String) session.getAttribute("checkCode_session");
        //4.删除储存的验证码
        session.removeAttribute("checkCode_session");
        //5.验证码判断
        if(checkCode_session!=null && checkCode_session.equals(checkCode)){
            //进行用户名密码验证
            if ("jack".equals(username)&&"666".equals(password)){
                //登录成功  储存用户信息  重定向 success.jsp   进行重定向而不是转发
                session.setAttribute("user",username);
                response.sendRedirect(request.getContextPath()+"/success.jsp");
            }else {
                //登录失败  储存失败信息  转发到 登录页面  login/jsp
                request.setAttribute("login_error","用户名或者密码错误");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        }else {
            //验证码错误  储存验证错误信息  转发到  登录页面  login/jsp
            request.setAttribute("cc_error","验证码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            //转发是在同一个资源内    不需要项目名 而 重定向给浏览器使用则需要获取虚拟目录
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
