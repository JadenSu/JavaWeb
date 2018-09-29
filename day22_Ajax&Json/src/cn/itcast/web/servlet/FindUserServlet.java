package cn.itcast.web.servlet;

import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        //request.setCharacterEncoding("utf-8");
        //2.获取参数
        String username = request.getParameter("username");
        System.out.println("request获取"+username);
        //3.调用service
        UserService service = new UserServiceImpl();
        User user = service.findUser(username);
        System.out.println("user:"+user);
        //4.对获取到的user进行判断
        if(user == null){
            //未使用过 响应设置可以使用
            response.getWriter().write("用户名未被使用");
        }else{
            //已使用  响应提示已被占用  !!!!注意设置响应编码
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("该用户名已被使用,请重新输入!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
