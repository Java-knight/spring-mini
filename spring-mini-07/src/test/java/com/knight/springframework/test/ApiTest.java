package com.knight.springframework.test;

import com.knight.springframework.context.support.ClassPathXmlApplicationContext;
import com.knight.springframework.test.bean.UserDao;
import com.knight.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * @desc
 * @author knight
 * @date 2023/7/23
 */
public class ApiTest {

    @Test
    public void test_xml() {
        // (1) 初始化 应用上下文
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // (2) 获取 bean 对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.findUserInfo();

        System.out.println("test result: " + result);
    }

    @Test
    public void test_hook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("close: ")));
    }
}
