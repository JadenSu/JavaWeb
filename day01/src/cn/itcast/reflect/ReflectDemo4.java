package cn.itcast.reflect;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/*

框架类
* 实现：
    1. 配置文件
    2. 反射
* 步骤：
    1. 将需要创建的对象的全类名和需要执行的方法定义在配置文件中
    2. 在程序中加载读取配置文件
    3. 使用反射技术来加载类文件进内存
    4. 创建对象
    5. 执行方法
 */
public class ReflectDemo4 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //配置文件 className = 全类名  methodName = 方法名
        //创建配置文件
        Properties pro = new Properties();
        //加载配置文件
        //获取类的字节码文件 类名.Class  对象. getCalss  Class.forName(全类名)
        ClassLoader classLoader = ReflectDemo4.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("pro.properties");
        pro.load(is);
        //获取配置文件数据
        String className = pro.getProperty("className");
        String methodName = pro.getProperty("methodName");

        //加载进内存  获取字节码文件 ClassForName(全类名)
        Class<?> cls = Class.forName(className);
        //获取对象
        Object obj = cls.newInstance();
        //获取方法对象
        Method method = cls.getMethod(methodName);
        //执行方法
        method.invoke(obj);
        //
    }


}
