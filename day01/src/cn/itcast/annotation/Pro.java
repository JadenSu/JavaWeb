package cn.itcast.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述需要执行的类名，方法名
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)

public @interface Pro {
    String className();  // 抽象方法  属性值
    String methodName();
}
