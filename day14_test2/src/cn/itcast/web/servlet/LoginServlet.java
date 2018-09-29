package cn.itcast.web.servlet;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        //获取参数]
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //封装成分对象  调用login方法   再对login方法的返回值进行判断
        //返回值为null转发 failServlet  else  (再进行一次非空判断) 转发successServlet
        User loginUser= new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);
        //调用方法
        UserDao dao = new UserDao();
        User user = dao.login(loginUser);
        //进行判断
        if(user == null){
            //登录失败转发 failServlet
            request.getRequestDispatcher("/failServlet").forward(request,response);
        }else {
            //登陆成功
            request.setAttribute("user",user);
            //转发 successServlet
            request.getRequestDispatcher("/successServlet").forward(request,response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
