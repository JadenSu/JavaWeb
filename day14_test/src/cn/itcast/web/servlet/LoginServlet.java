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
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //3.封装成user对象
        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);
        //4.调用login方法
        UserDao dao = new UserDao();
        User user = dao.login(loginUser);
        //5.进行判断  转发  successServlt  failServlet
        if(user==null){
            //转发失败
            request.getRequestDispatcher("/failServlet").forward(request,response);
        }else {
            //转发登录成功
            request.setAttribute("user",user);
            request.getRequestDispatcher("/successServlet").forward(request,response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}

