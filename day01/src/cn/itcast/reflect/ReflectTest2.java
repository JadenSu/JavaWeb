package cn.itcast.reflect;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/*
框架案例
1. 将需要创建的对象的全类名和需要执行的方法定义在配置文件中
2. 在程序中加载读取配置文件
3. 使用反射技术来加载类文件进内存
4. 创建对象
5. 执行方法
 */
public class ReflectTest2 {
    public static void main(String[] args) throws Exception{
        //1.1加载配置文件，创建Properties对象
        Properties pro = new Properties();
        //1.2转换为一个集合 使用  类加载器
        //1.2.1获取class目录下的配置文件ClassLoader
        //使用类加载器 将pro对象转换为集合
        ClassLoader classLoader = ReflectTest2.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("pro.properties");
        pro.load(is);
        //pro.load(new FileInputStream("pro.properties"));


        //2.获取配置文件中定义的数据
        String className = pro.getProperty("className");
        String methodName = pro.getProperty("methodName");
        //3.加载该类进内存
        Class<?> cls = Class.forName(className);
        //4.创建对象
        //空参构造  有参需要
        Object obj = cls.newInstance();
        //5.获取对象方法
        Method method = cls.getMethod(methodName);
        //6.调用方法
        method.invoke(obj);

        //需要多熟悉类对象得到各种方法
    }
}
