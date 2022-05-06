package com.ljn.demo.httpclient;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class TestHttpClient {
    public static void main(String[] args) {
        try {
//            testGetNoParams();
//            testGetHaveParams();
            testPost();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testGetNoParams() throws IOException {
        // 创建客户端对象
        HttpClient client = HttpClients.createDefault();
        // 创建请求地址和请求方式，如果是post，就是HttpPost
        HttpGet get = new HttpGet("http://localhost:80/test");
        // 发起请求，得到响应对象
        HttpResponse response = client.execute(get);
        // 从响应对象中得到响应体
        HttpEntity entity = response.getEntity();
        // 响应数据是基于http协议封装的对象，直接使用可能会出现乱码
        String entityString = EntityUtils.toString(entity, "utf-8");
        System.out.println("返回结果为："+entityString);
    }

    public static void testGetHaveParams() throws URISyntaxException, IOException {
        HttpClient client = HttpClients.createDefault();
        // 拼接请求路径和参数
        URIBuilder builder = new URIBuilder("http://localhost:80/params");
        builder.addParameter("username", "ljn");
        builder.addParameter("password", "123");
        URI uri = builder.build();
        HttpGet get = new HttpGet(uri);
        HttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        String entityString = EntityUtils.toString(entity, "utf-8");
        System.out.println("返回结果为："+entityString);
    }

    public static void testPost() throws IOException {
        HttpClient client = HttpClients.createDefault();
        // 传参可以直接拼接到url后面，也可以采用get传参的方式，但是这种参数会放到header中，会在地址栏汇中显示
        HttpPost post = new HttpPost("http://localhost:80/json");
        User u1 = new User();
        u1.setUsername("ljn");
        u1.setPassword("gtq");
        User u2 = new User();
        u2.setUsername("gtl");
        u2.setPassword("htl");
        List<User> users = new ArrayList<User>();
        users.add(u1);
        users.add(u2);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(users);
        // 将参数放到body中传递，默认是传递表单格式的参数
        HttpEntity requestEntity = new StringEntity(s, "application/json", "utf-8");
        post.setEntity(requestEntity);
        HttpResponse response = client.execute(post);
        HttpEntity responseEntity = response.getEntity();
        String responseEntityString = EntityUtils.toString(responseEntity);
        System.out.println("返回结果: " + responseEntityString);
        // 将返回的结果封装成对象
        // 对list中的对象分别封装
        String first = responseEntityString.substring(1, responseEntityString.indexOf("},")+1);
        User u3 = objectMapper.readValue(first, User.class);
        System.out.println("封装单个对象: "+u3);
        // 对list中的对象同时封装
        // 构建一个类型映射
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, User.class);
        // 将封装为单个对象用到的User.class替换为构建的javaType
        List<User> userList = objectMapper.readValue(responseEntityString, javaType);
        System.out.println("同时封装多个对象: "+userList.toString());
    }
}
