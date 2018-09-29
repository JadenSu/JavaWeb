package cn.itcast.web.servlet;

import cn.itcast.service.ProvinceService;
import cn.itcast.service.impl.ProvinceServletImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/provinceServlet")
public class ProvinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取的异步请求没有参数  直接调用service 查找缓存  缓存没有查找数据库  因此dao需要创建两个方法
        ProvinceService service = new ProvinceServletImpl();
        //2. 调用service获取 json
        String json = service.findRedis();

        //3.响应结果
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
