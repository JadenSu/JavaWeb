package cn.itcast.web.filter;

import javax.servlet.*;
import java.io.IOException;

//@WebFilter("*.jsp")
public class FilterDemo1 implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("demo1----------");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        String site = config.getInitParameter("Site");
        System.out.println(site);//null
        String filterName = config.getFilterName();
        System.out.println(filterName);//cn.itcast.web.filter.FilterDemo1

    }

}
