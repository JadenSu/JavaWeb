package cn.itcast.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1. 请求转换
        HttpServletRequest request = (HttpServletRequest) req;
        //2.获取请求资源路径
        String uri = request.getRequestURI();
        //3.判断是否包含登录相关资源路径,css js 图片  验证码等
        if(uri.contains("/login.jsp") || uri.contains("/loginServlet") || uri.contains("/css/")
                || uri.contains("/js/") || uri.contains("/fonts/") || uri.contains("/checkCodeServlet") ){
            //包含登录相关路径,放
            chain.doFilter(req,resp);
        }else {
            //不包含,直接访问,验证session是否有储存user
            Object user = request.getSession().getAttribute("user");
            //进行判断
            if(user!=null){
                //登录,放
                chain.doFilter(req,resp);
            }else{
                //跳转登录
                request.setAttribute("login_msg","没登录,请登录");
                request.getRequestDispatcher("/login.jsp").forward(request,resp);
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
