package cn.itcast.annotation;

import java.lang.reflect.Method;

@Pro(className = "cn.itcast.domain.Student",methodName = "sleep")


public class ReflectTest {
    public static void main(String[] args) throws Exception {
        //1.解析注解，获取该类的字节码对象
        Class<ReflectTest> reflectTstClass = ReflectTest.class;

        //2.获取注解对象
        Pro an = reflectTstClass.getAnnotation(Pro.class);

        //3.调用注解对象中定义的抽象方法，获取返回值
        String className = an.className();
        String methodName = an.methodName();

        //4.加载该类进内存
        Class<?> cls = Class.forName(className);
        //5.创建对象
        Object obj = cls.newInstance();
        //6.获取方法对象
        Method method = cls.getMethod(methodName);
        //7.执行方法
        method.invoke(obj);
    }
}
