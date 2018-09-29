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

/**
 * 根据分页查找User
 * 思路分析:
 * 1.需要获取的不是user数据 而是页面数据 因此我们需要一个PageBean获取页面数据,封装成对象
 *      PageBean类局部变量
 *      1.rows 页面行数 从页面直接获取
 *      2.List 页面数据 从数据库获取  dao需要创建获取页面数据的方法  findByPage()
 *      3.currentPage   当前页面    从页面获取
 *      4.totalPage 页面数 totalCount/rows 通过计算获取
 *      5.totalCount 总记录数   从数据库获取  dao创建获取记录数方法 findTotalCount()
 * 2.创建findUserByPageServlet
 *      1.设置编码
 *      2.获取参数 rows currentPage
 *      3.调用service
 *          PageBean pb = service.findUserByPage(rows,currentPage)(其实就是查询剩下的三个参数)
 *      4.储存pb
 *      5.转发list.jsp
 * 3.serviceImpl
 *      1.dao.findTotalCount
 *      2.dao.getList(start,rows) select*from user limit?,?
 *      3.totalPage 计算
 * 4.daoImpl
 *      1.findByPage()  list    页面数据
 *      2.findTotalCount()  totalCount 总记录数
 *
 */
@WebServlet("findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取页面参数 rows&currentPage
        String rows = request.getParameter("rows");
        String currentPage = request.getParameter("currentPage");
        //3.调用service  通过findUserByPage() 获取另外三个参数
        UserService service = new UserServiceImpl();
        PageBean<User> pb = service.findUserByPage(rows,currentPage);
        request.setAttribute("pb",pb);
        //4.转发list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
