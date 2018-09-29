package cn.itcast.reflect;

import cn.itcast.domain.Person;

;import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
Class对象功能：
		* 获取功能：
			1. 获取成员变量们
				* Field[] getFields() ：获取所有public修饰的成员变量
				* Field getField(String name)   获取指定名称的 public修饰的成员变量

				* Field[] getDeclaredFields()  获取所有的成员变量，不考虑修饰符
				* Field getDeclaredField(String name)
			2. 获取构造方法们
				* Constructor<?>[] getConstructors()
				* Constructor<T> getConstructor(类<?>... parameterTypes)

				* Constructor<T> getDeclaredConstructor(类<?>... parameterTypes)
				* Constructor<?>[] getDeclaredConstructors()
			3. 获取成员方法们：
				* Method[] getMethods()
				* Method getMethod(String name, 类<?>... parameterTypes)

				* Method[] getDeclaredMethods()
				* Method getDeclaredMethod(String name, 类<?>... parameterTypes)

			4. 获取全类名
				* String getName()
 */
public class Reflectdemo2 {
    public static void main(String[] args)throws Exception {
        System.out.println("获取Clas的三种方式");
        // Class.forName(全类名)  将字节码加载进内存，返回Class对象
        Class<?> c1 = Class.forName("cn.itcast.domain.Person");
        //类名.class  多用于参数传递
        Class<Person> c2 = Person.class;
        //对象名.getClass   较少使用
        Person person = new Person();
        Class<? extends Person> c3 = person.getClass();
        //在一次程序运行时，字节码只加载一次，方法不同但获取的字节码是相同的
        System.out.println(c1==c2&&c2==c3);//true


        System.out.println("=============Class对象功能===============");
        //Class对象功能，创建对象
        Class<Person> personClass = Person.class;
        //获取所有成员变量 public修饰
        Field[] fields = personClass.getFields();
        for (Field field : fields) {
            System.out.println(field);//public java.lang.String cn.itcast.domain.Person.a
        }

        //获取指定名称成员变量
        Field a = personClass.getField("a");
        Object obj1 = a.get(person);
        System.out.println(obj1); // 获取变量值 未赋值 null
        //进行赋值
        a.set(person,"a是什么");
        Object obj2 = a.get(person);
        System.out.println(obj2); // a是什么

        System.out.println("============构造方法===============");
        // 获取构造对象
        Constructor<Person> constructor = personClass.getConstructor();
        System.out.println(constructor);//public cn.itcast.domain.Person()

        //构造对象方法 newInstance  创建对象
        Person p1 = constructor.newInstance();

        //空参构造方法创建对象可以省略获取构造对象 简化为 Class对象.newInstance

        Person p2 = personClass.newInstance();

        System.out.println("=============获取成员方法=================");
        //获取成员方法
        //较少使用  成员方法太多 一般使用获取指定成员方法
        Method[] methods = personClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        //获取指定成员方法
        Method method = personClass.getMethod("eat" , String.class);
        System.out.println(method);

        //获取全类名
        String name = personClass.getName();
        System.out.println(name);//cn.itcast.domain.Person

    }
}
