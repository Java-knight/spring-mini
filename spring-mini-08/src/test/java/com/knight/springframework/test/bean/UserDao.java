package com.knight.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author knight
 * @desc
 * @date 2023/7/29
 */
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    public void initDataMethod() {
        System.out.println("execute init-method");
        hashMap.put("10001", "jingdong");
        hashMap.put("10002", "shopee");
        hashMap.put("10003", "alibaba");
    }

    public void destroyDataMethod() {
        System.out.println("execute destroy-method");
        hashMap.clear();
    }

    public String findUserName(String uid) {
        return hashMap.get(uid);
    }
}
