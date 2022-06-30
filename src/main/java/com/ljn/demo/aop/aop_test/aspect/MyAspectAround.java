package com.ljn.demo.aop.aop_test.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

//@Component
//@Aspect
public class MyAspectAround {

//    用Pointcut注解中的属性(切点表达式或者切点函数)来指定对哪些方法进行增强，该注解必须用在方法上
    @Pointcut("execution(* com.ljn.demo.aop.aop_test.service.ServiceAround.*(..))")
    public void pt(){}

//    用@Before注解来指定该方法中是增强的代码，并且是在被增强方法执行前执行的
//    @Before的属性写上加了@Pointcut注解的方法: 方法名()
    @Around("pt()")
    public void func(ProceedingJoinPoint pjp){
        System.out.println("目标方法前执行");
        try {
            pjp.proceed();
            System.out.println("(目标方法)返回后执行，出现异常不执行");
        } catch (Throwable throwable) {
            System.out.println("出现异常后执行");
            throwable.printStackTrace();
        } finally {
            System.out.println("(目标方法)返回后执行，出现异常也执行");
        }
    }

}