package com.ljn.demo.aop.aop_test.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspectAroundPjp {

//    用Pointcut注解中的属性(切点表达式或者切点函数)来指定对哪些方法进行增强，该注解必须用在方法上
    @Pointcut("execution(* com.ljn.demo.aop.aop_test.service.ServiceAround.*(..))")
    public void pt(){}

//    用@Before注解来指定该方法中是增强的代码，并且是在被增强方法执行前执行的
//    @Before的属性写上加了@Pointcut注解的方法: 方法名()
    @Around("pt()")
    public Object func(ProceedingJoinPoint pjp){
        // 被增强方法的参数
        Object[] args = pjp.getArgs();
        // 被代理对象
        Object target = pjp.getTarget();
        // 被增强方法的签名，强转成子类，子类功能更强
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Object ret = null;
        try {
            // 执行被增强的方法，还有一个带参数的proceed方法
            ret = pjp.proceed();
        } catch (Throwable throwable) {
            System.out.println("出现异常后执行");
            throwable.printStackTrace();
        } finally {
            System.out.println("(目标方法)返回后执行，出现异常也执行");
        }
        return ret;
    }

}