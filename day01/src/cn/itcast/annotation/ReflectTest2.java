package cn.itcast.annotation;

import java.lang.reflect.Method;
@Pro2(className = "cn.itcast.domain.Student",methodName = "sleep")
public class ReflectTest2 {
    public static void main(String[] args) throws Exception {
        //获取类字节码对象
        Class<ReflectTest2> reflectTest2Class = ReflectTest2.class;
        //获取注解对象
        Pro2 an = reflectTest2Class.getAnnotation(Pro2.class);
        //调用注解对象中的抽象方法
        String className = an.className();
        String methodName = an.methodName();
        //加载对象
        Class<?> cls = Class.forName(className);
        //创建对象
        Object obj = cls.newInstance();
        //创建方法对象
        Method method = cls.getMethod(methodName);
        //执行方法



    }
}
