package com.ljn.demo.json_object.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljn.demo.json_object.User;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws JsonProcessingException {
        // 单个object与json的转换
        User user = new User();
        user.setName("ljn");
        user.setAge(25);
        System.out.println("原始对象: " + user);
        String userJson = object2json(user);
        System.out.println("object2json: " + userJson);
        User userObj = json2object(userJson, User.class);
        System.out.println("json2object: " + userObj);

        // 多个object与json的转换
        User user1 = new User("xz", 45);
        User user2 = new User("bbe", 40);
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        System.out.println("原始数组: " + list);
        String listJson = object2json(list);
        System.out.println("object2json: " + listJson);
        List<User> userList = json2objectList(listJson, User.class);
        System.out.println("json2objectList: " + userList);
    }

    public static String object2json(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> T json2object(String json, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(json, clazz);
    }

    public static <T> List<T> json2objectList(String listJson, Class<T> beanType) throws JsonProcessingException {
        // 构建一个类型参数
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, beanType);
        return objectMapper.readValue(listJson, javaType);
    }

}
