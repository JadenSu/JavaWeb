package cn.itcast.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

@WebFilter("/*")
public class SensitiveWordFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.创建代理对象  真实对象req在参数中不需要创建
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //对getParameter方法进行增强  增强返回值  对返回值进行判断
            if("getParameter".equals(method.getName())){
                //增强返回值
                //获取返回值
                String value =(String)method.invoke(req,args);
                if(value!= null){
                    //进行遍历对比
                    for (String str : list) {
                        if(value.contains(str)){
                            //有敏感词 进行替换
                            value = value.replace(str, "???");
                        }
                    }
                }
                return value;
            }
                return method.invoke(req,args);
            }
        });
        chain.doFilter(proxy_req, resp);
    }
    //过滤器创建时  读取 敏感词文档
    ArrayList<String> list = new ArrayList<>();
    public void init(FilterConfig config) throws ServletException {
        try {
            //获取文件真实路径  文件在src目录下  服务器路径在 WEB-INF/classes
            ServletContext servletContext = config.getServletContext();
            String realPath = servletContext.getRealPath("/WEB-INF/classes/敏感词汇.txt");
            //读取文件 储存到list
            BufferedReader bufr = new BufferedReader(new FileReader(realPath));
            String line = null;
            while((line = bufr.readLine())!=null){
                list.add(line);
            }
            bufr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
