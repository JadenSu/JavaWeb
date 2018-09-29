package cn.itcast.web.filter;

import javax.servlet.*;
import java.io.IOException;

//@WebFilter("/index.jsp")
public class FilterDemo2 implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("demo2----------");
        chain.doFilter(req, resp);
        System.out.println("demo2回来");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
