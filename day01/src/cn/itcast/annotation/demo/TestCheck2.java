package cn.itcast.annotation.demo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
框架测试
 */
public class TestCheck2{
    public static void main(String[] args) throws IOException{
        //1.创建计算器对象
        Calculator calculator = new Calculator();
        //2.获取字节码文件对象
        Class<? extends Calculator> cls = calculator.getClass();
        //3.获取所有方法
        Method[] methods = cls.getMethods();

        int number = 0;//记录异常数据
        //创建记录异常数据的流
        BufferedWriter bufw = new BufferedWriter(new FileWriter("bug2.txt"));

        for (Method method : methods) {
            //判断方法上是否有注解
            if(method.isAnnotationPresent(Check.class)){
                //执行方法
                try {
                    method.invoke(calculator);
                } catch (Exception e) {
                    //记录异常次数
                    number++;
                    //处理异常，异常信息存储到bug.txt

                    bufw.write(method.getName()+"方法出现异常");
                    bufw.newLine();
                    bufw.write("异常名称："+e.getCause().getClass().getName());
                    //java.lang.NullPointerException
                    bufw.newLine();
                    bufw.write("异常信息："+e.getCause().getMessage());
                    //异常名称：java.lang.ArithmeticException
                    bufw.newLine();


                }
            }
        }
        bufw.write("异常次数"+number);
        bufw.flush();
        bufw.close();

    }

}
