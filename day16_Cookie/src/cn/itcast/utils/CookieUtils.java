package cn.itcast.utils;

import javax.servlet.http.Cookie;

/**
 * 工具类：
 * 1. 判断用户第一次访问
 * 2. 获取用户之前响应的cookie对象
 */
public class CookieUtils {

    public static Cookie judgeFirst(Cookie[] cookies, String cookieName){
        if(cookies==null){//是第一次访问
            return null;
        }else{
            //不是第一次访问，获取上次响应到浏览器端 的cookie
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if(cookieName.equals(name)){//找到了相同名称的cookie
                    return cookie;
                }
            }
        }

        return null;
    }
}
