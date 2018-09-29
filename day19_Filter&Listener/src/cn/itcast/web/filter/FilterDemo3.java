package cn.itcast.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
//请求直接访问 或者通过转发访问  index 都会被拦截
@WebFilter(value = "/index.jsp",dispatcherTypes = {DispatcherType.FORWARD,DispatcherType.REQUEST})
public class FilterDemo3 implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("demo3----------");
        chain.doFilter(req, resp);
        System.out.println("demo3回来");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
