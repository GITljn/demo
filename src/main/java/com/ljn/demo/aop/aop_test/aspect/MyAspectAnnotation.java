package com.ljn.demo.aop.aop_test.aspect;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

//@Component
//@Aspect
public class MyAspectAnnotation {

//    用Pointcut注解中的属性(切点表达式或者切点函数)来指定对哪些方法进行增强，该注解必须用在方法上
    @Pointcut("@annotation(com.ljn.demo.aop.aop_test.aspect.InvokeLog)")
    public void pt(){}

//    用@Before注解来指定该方法中是增强的代码，并且是在被增强方法执行前执行的
//    @Before的属性写上加了@Pointcut注解的方法: 方法名()
    @Before("pt()")
    public void func(){
        System.out.println("切点函数");
    }

}