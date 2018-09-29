package cn.itcast.reflect;

import cn.itcast.domain.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
获取Class对象的方式：
		1. Class.forName("全类名")：将字节码文件加载进内存，返回Class对象
			* 多用于配置文件，将类名定义在配置文件中。读取文件，加载类
		2. 类名.class：通过类名的属性class获取
			* 多用于参数的传递
		3. 对象.getClass()：getClass()方法在Object类中定义着。
			* 多用于对象的获取字节码的方式
 */
public class ReflectDemo1 {
    public static void main(String[] args)throws Exception {
        System.out.println("=========获取Class对象的方式============");
        //1. Class.forName("全类名")
        Class<?> c1 = Class.forName("cn.itcast.domain.Person");
        System.out.println(c1);

        //2. 类名.class：
        Class<Person> c2 = Person.class;
        System.out.println(c2);

        //3. 对象.getClass()：
        Person p = new Person();
        Class<? extends Person> c3 = p.getClass();
        System.out.println(c2);

//同一个字节码文件(*.class)在一次程序运行过程中，只会被加载一次，不论通过哪一种方式获取的Class对象都是同一个。
        System.out.println(c1==c2&&c2==c3);

        System.out.println("==========Class对象功能===========");

        Class<Person> personClass = Person.class;

        //Field[] getFields() ：获取所有public修饰的成员变量

        Field[] fields = personClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }


        //Field getField(String name)   获取指定名称的 public修饰的成员变量
        Field a = personClass.getField("a");
        Person person = new Person();
        //获取a的值
        Object value = a.get(person);
        System.out.println(a);
        //设置a的值
        a.set(person,"a是什么");
        System.out.println(person);


        System.out.println("=========Constructor:构造方法==========");
        /*
        Constructor:构造方法
                * 创建对象：
			* T newInstance(Object... initargs)

			* 如果使用空参数构造方法创建对象，操作可以简化：Class对象的newInstance方法


                * Method：方法对象
                * 执行方法：
			* Object invoke(Object obj, Object... args)

		* 获取方法名称：
			* String getName:获取方法名
         */
        Constructor<Person> constructor = personClass.getConstructor();
        System.out.println(constructor);
        Person person1 = constructor.newInstance();
        System.out.println(person1);

        //空参构造创建对象
        Person person2 = personClass.newInstance();
        System.out.println(person2);

        System.out.println("========Method：方法对象=============");

        //获取指定方法的对象
        Method method = personClass.getMethod("eat");
        System.out.println(method);



    }
}
