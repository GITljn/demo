package com.ljn.demo.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Demo {
    public static void main(String[] args)
            throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        // 得到的一定是User类
        Class<User> userClass1 = User.class;

        User user = new User();
        // user中可能是User类也肯能是User类的子类
        Class<? extends User> userClass2 = user.getClass();

        // 得到的类型不确定
        Class<?> userClass3 = Class.forName("com.ljn.demo.reflection.User");

        // 获取类的方法
        // getDeclaredMethods得到的是自身类中所有的方法，getMethods得到的是自身类和所有父类中的public修饰的方法
        Method[] declaredMethods = userClass3.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println(method.getName());
        }

        // 获取类的属性
        Field[] declaredFields = userClass3.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println(field.getName());
        }

        User userTest = (User) userClass3.newInstance();
        System.out.println("使用反射设置属性值前: "+userTest);
        // 通过方法设置属性值
        // 需要指定参数类型而不是对象的类型
        Method setUsername = userClass3.getDeclaredMethod("setUsername", String.class);
        setUsername.invoke(userTest, "ljn");
        // 直接设置属性值
        Field password = userClass3.getDeclaredField("password");
        // 设置是否允许访问，调用私有方法前也需要设置
        password.setAccessible(true);
        password.set(userTest, "123");
        System.out.println("使用反射设置属性值后: "+userTest);
        // 同一个类型的类对象只有一个
        System.out.println(userClass1 == userClass3);
    }
}
