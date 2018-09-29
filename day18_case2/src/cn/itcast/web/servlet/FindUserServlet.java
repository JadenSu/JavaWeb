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

/**
 * 修改用户数据功能的数显需要由两个部分实现
 * 1.查找用户数据 finduser  将数据库中的user信息回写到update.jsp
 * 2.由update.jsp将填写好的数据提交到updateservlet将数据储存到数据库
 */
@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取id
        String id = request.getParameter("id");
        //3.调用service
        UserService service = new UserServiceImpl();
        User user = service.findUserById(id);
        //4.将返回的user储存
        request.setAttribute("user",user);
        //5.一次请求 转发 update.jsp
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
