package com.ljn.demo.aop.aop_app;

import com.ljn.demo.aop.aop_app.controller.AIController;
import com.ljn.demo.aop.aop_app.util.CryptUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo {
    public static void main(String[] args) {
        //创建容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/ljn/demo/aop/resources/applicationContext.xml");
        //获取对象
        AIController aiController = applicationContext.getBean(AIController.class);
        //调用方法
//        String answer = aiController.getAnswer("看视频应该三连吗？");

        String name = CryptUtil.AESencode("张三");
        String result = aiController.fortuneTelling(name);
//        System.out.println(answer);


        System.out.println(CryptUtil.AESdecode(result));
        System.out.println(result);



    }
}
