package cn.itcast.web.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理模式练习
 */
public class ProxyTest2 {
    public static void main(String[] args) {
        //1.创建真实对象
        Lenovo lenovo = new Lenovo();
        //2.创建代理对象参数
        //类加载器
        ClassLoader classLoader = lenovo.getClass().getClassLoader();
        //接口数组
        Class<?>[] interfaces = lenovo.getClass().getInterfaces();
        //处理器
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //sale方法判断    方法加强三种方式 参数加强 方法体逻辑加强  返回值加强
                if("sale".equals(method.getName())){
                    //获取参数  进行加强
                    Double money = (double)args[0];
                    money = 998.0;
                    //真是对象调用方法 获取返回值  对返回值进行加强
                    String obj = (String)method.invoke(lenovo, money);

                    return  obj +"赚钱给辣鸡柳传志";
                }else {
                    //普通方法
                    Object invoke = method.invoke(lenovo, args);
                    return invoke;
                }
            }
        };

        //4.创建代理对象
        Object o = Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        //代理对象类型转换
        SaleComputer proxy_lenovo = (SaleComputer)o;

        //5.代理对象调用方法
        proxy_lenovo.show();
        String sale = proxy_lenovo.sale(666);
        System.out.println(sale);
    }
}
