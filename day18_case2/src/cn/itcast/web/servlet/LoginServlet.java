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
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取数据
        //2.1获取输入验证码
        String verifycode = request.getParameter("verifycode");
        //2.2获取服务器验证码  获取图片验证码是另外一次请求  session获取服务器验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String)session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//获取完后将储存的验证码移除,一次登录可能获取多个验证码,保证眼睁睁吗的唯一性
        //3.验证码校验
        if(!checkcode_server.equalsIgnoreCase(verifycode)){
            //验证码错误  储存登录错误信息,可以和密码错误储存相同信息"login_msg"
            request.setAttribute("login_msg","验证码错误");
            //转发到登录页面  登录页面会打印错误信息
            request.getRequestDispatcher("login.jsp").forward(request,response);
            //登录失败不再执行下面代码
            return;
        }
        //4.封装User对象
        //4.1 获取提交的username password信息
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        //4.2封装
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //5.判断是否登陆成功 需要调用方法进行判断 调用service -> dao-> login()
        UserService service = new UserServiceImpl();
        User loginUser = service.login(user);
        //6.进行判断
        if(loginUser!=null){
            //登陆成功  储存用户信息 跳转index.jsp   后续通过index页面对数据库进行正删改查
            session.setAttribute("user",loginUser);
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }else {
            //登录失败  储存登录错误信息 验证码错误一样  转发到login.jsp
            request.setAttribute("login_msg","账号或者密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
