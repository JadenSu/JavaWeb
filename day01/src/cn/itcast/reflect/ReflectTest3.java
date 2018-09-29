package cn.itcast.reflect;

import cn.itcast.annotation.Pro;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

public class ReflectTest3 {
    public static void main(String[] args)throws Exception {

        //创建配置文件对象
        Properties pro = new Properties();
        //加载配置文件
        //类加载器
        ClassLoader classLoader = ReflectTest3.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("pro.properties");
        pro.load(is);
        //获取配置文件数据
        String className = pro.getProperty("className");
        String methodName = pro.getProperty("methodName");
        //加载对象
        Class<?> cls = Class.forName(className);
        //创建对象
        Object obj = cls.newInstance();
        //创建方法对象
        Method method = cls.getMethod(methodName);
        //执行方法
        method.invoke(obj);

    }
}
