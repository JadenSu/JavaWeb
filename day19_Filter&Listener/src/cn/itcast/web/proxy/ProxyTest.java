package cn.itcast.web.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理类   实现同一个接口
 * 1.创建真实对象
 * 2.创建代理对象    ClassLoader   Interfaces  Handler    new InvoacationHandler()
 *   执行方法对象    代理对象  代理方法  方法参数
 */
public class ProxyTest {
    public static void main(String[] args) {
        //1.创建真实对象
        Lenovo lenovo = new Lenovo();
        //2.创建代理对象   三个参数
        //类加载器
        ClassLoader classLoader = lenovo.getClass().getClassLoader();
        //接口数组
        Class<?>[] interfaces = lenovo.getClass().getInterfaces();
        //处理器
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //调用方法都会通都过该执行方法  真是对象调用方法
                //增强方式   参数列表  返回值类型   方法体执行逻辑
                //System.out.println(proxy);//????java.lang.StackOverflowError
                System.out.println(method.getName());
                //System.out.println(args[0]);
                if("sale".equals(method.getName())){
                    double money = (double)args[0];
                    //增强参数
                    money = money*0.1;
                    //真实对象调用方法
                    String obj = (String) method.invoke(lenovo,money);
                    //增强返回值
                    return (obj + "就是美国人民的爱");

                }else {
                    //不是增强方法
                    System.out.println("不是增强方法");
                    Object obj = method.invoke(lenovo, args);
                    return obj;
                }

            }
        };
        //代理对象
        Object obj_proxy = Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        SaleComputer proxy_lenovo = (SaleComputer) obj_proxy;//代理对象实现的是接口  所以不能转换为Lenovo  可以理解为两个不同的类怎么转换,子类转换父类


        //3.调用增强方法  sale是增强方法   show是普通方法  都调用invoke
        String sale = proxy_lenovo.sale(500);
        System.out.println(sale);
        proxy_lenovo.show();
    }
}
