package cn.itcast.annotation;

/*
注解本质就是一个接口
  格式
  元注解
  public @interface 注解名称{
       属性列表;}
   属性就是接口中的抽象方法
       1.属性的返回值类型
           基础数据类型
           字符串
           枚举
           注解
           以上类型的组合
       2.定义属性，在使用时需要赋值
           1.使用default设置默认值
           2.只有一个属性需要赋值，且属性名为value，可省略，直接赋值
           3.数组赋值，只使用{}，只有一个值，{}可以省略
    元注解：用于描述注解的注解,主要用于自定义注解
        @Target  注解作用位置
            ElementType取值
                TYPE    类
                METHOD  方法
                FIELD   成员变量
        @Retention  描述注解被保留的阶段
            使用较多的是RUNTIME
            @Retention(RetentionPolicy.RUNTIME)：当前注解会保留到class文件字节码
        @Documented 描述注解是否被抽取到API文档中
        @Inherited  描述是否被集成
 */
public @interface MyAnno {

    int shwow();//基础数据类型
    int age()default 12;

    String show();//字符串

    MyAnno2 anno();//注解

    String[] str() default "222";//数组只是用{}，且只有一个值可以省略




}
