package cn.itcast.web.servlet;

import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取参数
        String currentPage = request.getParameter("currentPage"); //当前页数
        String rows = request.getParameter("rows");// 每页记录数

        //没有参数进行设置
        if(currentPage == null || "".equals(currentPage)){

            currentPage = "1";
        }
        if(rows == null || "".equals(rows)){
            rows = "5";
        }
        //获取查询条件参数
        Map<String, String[]> condition = request.getParameterMap();
        //3.调用 sevice    PageBean  pb  封装数据
        UserService service = new UserServiceImpl();
        PageBean<User> pb = service.findUserByPage(rows,currentPage,condition); //传入数据  返回一个 PageBean对象
        //4.储存 pb
        request.setAttribute("pb",pb);
        request.setAttribute("condition",condition);//储存查询条件
        request.getRequestDispatcher("/list.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
