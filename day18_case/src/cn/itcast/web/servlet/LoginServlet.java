package cn.itcast.web.servlet;

import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //编码
        request.setCharacterEncoding("utf-8");
        //获取验证码
        String verifycode = request.getParameter("verifycode");
        //两次 request 通过 session 获取服务器验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String)session.getAttribute("CHECKCODE_SERVER");
        //验证码校验
        if(!verifycode.equalsIgnoreCase(checkcode_server)){
            //验证错误
            request.setAttribute("login_msg","验证码错误");
            //转发 login.jsp
            request.getRequestDispatcher("login.jsp").forward(request,response);
            return;
        }

        Map<String, String[]> map = request.getParameterMap();
        //验证通过 封装User
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //service 查询
        UserService service = new UserServiceImpl();
        User loginUser = service.login(user);
        //对 user 进行判断
        if(loginUser != null){
            //登陆成功  保存user信息  转发登录成功
            session.setAttribute("user",loginUser);
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }else {
            //登录失败  提示错误信息 转发跳转页面 和验证码错误一样
            request.setAttribute("login_msg","用户名或者账号错误");
            request.getRequestDispatcher("login/jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
