package cn.itcast.web.servlet;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        步骤:
        1.设置编码
        2.获取参数 name password checkcode
        3.获取服务器生成的验证码   通过 session获取
        4.进行判断
        4.1相等登录
        4.2不相等 提示登录失败 重新获取验证码
         */
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //封装对象
        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);
        String checkCode = request.getParameter("checkCode");
        //获取验证码
        HttpSession session = request.getSession();
        String checkCode_session = (String)session.getAttribute("checkCode_session");

        //删除session中存储的验证码
        session.removeAttribute("checkCode_session");


        //将checkCode与checkCode_session进行对比 判断验证码是否正确
        if (checkCode_session!=null && checkCode_session.equalsIgnoreCase(checkCode)){
            //验证正确 进行登录验证
            UserDao dao = new UserDao();
            User user = dao.login(loginUser);
            //进行判断  转发
            if(user==null){
                //转发失败
                request.setAttribute("login_error","用户名或密码错误");
                request.getRequestDispatcher("/login.jsp").forward(request,response);

//                request.getRequestDispatcher("/failServlet").forward(request,response);
            }else {
                //使用session 重定向  浏览器会打开一个新的页面  而转发还是同一个页面
                session.setAttribute("username",username);
                response.sendRedirect(request.getContextPath()+"/success.jsp");
//                request.setAttribute("user",user);
//                request.getRequestDispatcher("/successServlet").forward(request,response);
            }
        }else {
            //验证码错误 转发到登录界面
            request.setAttribute("cc_error","验证码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
