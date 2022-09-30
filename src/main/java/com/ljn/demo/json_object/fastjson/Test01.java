package com.ljn.demo.json_object.fastjson;

import com.alibaba.fastjson.JSON;
import com.ljn.demo.utils.PageInfo;

/**
 * 测试fastjson在将对象转换成字符串时是否是根据get方法来对对象的属性进行转换
 * 结论：fastjson在将对象转换成字符串时只关注get方法，不关注属性，
 * 即使类中重写了toString也不受影响
 */
public class Test01 {
    public static void main(String[] args) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setCurrent(1);
        pageInfo.setCurrent(10);
        pageInfo.setTotalRows(100);
        String s = JSON.toJSONString(pageInfo);
        System.out.println(s);
    }
}
