package com.ljn.demo.json_object.fastjson;

import com.alibaba.fastjson.JSON;
import com.ljn.demo.json_object.User;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        // 单个object与json的转换
        User user = new User();
        user.setName("ljn");
        user.setAge(25);
        System.out.println("原始对象: " + user);
        String userJson = JSON.toJSONString(user);
        System.out.println("object2json: " + userJson);
        User userObj = JSON.parseObject(userJson, User.class);
        System.out.println("json2object: " + userObj);

        // 多个object与json的转换
        User user1 = new User("xz", 45);
        User user2 = new User("bbe", 40);
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        System.out.println("原始数组: " + list);
        String listJson = JSON.toJSONString(list);
        System.out.println("object2json: " + listJson);
        List<User> userList = JSON.parseArray(listJson, User.class);
        System.out.println("json2objectList: " + userList);
    }
}
