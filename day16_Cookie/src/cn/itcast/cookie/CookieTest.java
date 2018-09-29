package cn.itcast.cookie;

import cn.itcast.utils.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 案例:记录上一次访问时间
 * 需求:
 * 1. 访问一个Servlet，如果是第一次访问，则提示：您好，欢迎您首次访问。
 * 2. 如果不是第一次访问，则提示：欢迎回来，您上次访问时间为:显示时间字符串
 */
@WebServlet("/cookieTest")
public class CookieTest extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取所有Cookie
        Cookie[] myCookies = request.getCookies();
        Cookie myCookie = CookieUtils.judgeFirst(myCookies,"rem");
        if(myCookie == null){
            //第一次访问
            //获取当前时间  设置cooKie值
            Date date = new Date();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String sd = sdf.format(date);
            String time = String.format("%tF %<tT",date);
            time = time.replace(" ","/");
            Cookie cookie = new Cookie("rem",time);
            response.addCookie(cookie);

        }else {
            Date date = new Date();
            String time =  String.format("%tF %<tT",date);
            time = time.replace(" ","/");
            Cookie  cookie  = new Cookie("rem",time);
            //响应cookie到浏览器端
            response.addCookie(cookie);
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
