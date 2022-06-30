package com.ljn.demo.aop.aop_test;

import com.ljn.demo.aop.aop_test.service.ServiceAround;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo {
    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("com/ljn/demo/aop/resources/applicationContext.xml");
//        ServiceAnnotation serviceAnnotation = app.getBean(ServiceAnnotation.class);
//        serviceAnnotation.deleteAll();
//        ServiceExecution serviceExecution = app.getBean(ServiceExecution.class);
//        serviceExecution.deleteAll();
        ServiceAround serviceAround = app.getBean(ServiceAround.class);
        serviceAround.deleteAll();
    }
}
