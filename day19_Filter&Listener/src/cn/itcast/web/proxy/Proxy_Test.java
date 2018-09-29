package cn.itcast.web.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理练习
 */
public class Proxy_Test {
    public static void main(String[] args) {
        //1.创建真实对象
        Lenovo lenovo = new Lenovo();
        //2.创建代理对象所需三个参数 类加载器  接口数组   处理器  new In--
        ClassLoader classLoader = lenovo.getClass().getClassLoader();
        Class<?>[] interfaces = lenovo.getClass().getInterfaces();
        // 创建处理器  匿名内部类方式 重写方法
        Object proxy_obj = Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //对增强方法进行判断
                if("sale".equals(method.getName())){
                    //增强方法
                    //获取参数  args
                    Double money = (Double) args[0];
                    //增强参数列表
                    money = money*1.3;
                    //真实对象调用方法
                    String obj = (String) method.invoke(lenovo, money);
                    //增强返回值
                    return obj+"真实辣鸡";
                }else {
                    //不是增强方法
                    //真是对象调用方法
                    Object invoke = method.invoke(lenovo, args);
                    return invoke;
                }

            }
        });

        //代理对象类型转换
        SaleComputer proxy_lenovo = (SaleComputer) proxy_obj;
        //代理对象调用方法
        String sale = proxy_lenovo.sale(777);
        System.out.println(sale);


    }

}
