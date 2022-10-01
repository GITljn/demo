package com.ljn.demo.test.test2;

import java.util.*;

public class Demo01 {
    public static void main(String[] args) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("a", "A");
        Set<String> strings = map.keySet();
        Iterator<String> iterator = strings.iterator();
        System.out.println(iterator.next());
        HashMap<String, String> hashMap = new HashMap<>();
        Set<String> strings1 = hashMap.keySet();
    }
}
