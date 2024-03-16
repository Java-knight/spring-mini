package com.knight.springframework.test;

import com.knight.springframework.context.support.ClassPathXmlApplicationContext;
import com.knight.springframework.test.bean.UserService;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * @desc
 * @author knight
 * @date 2023/7/23
 */
public class ApiTest {

    @Test
    public void test_prototype() {
        // (1) 初始化 应用上下文
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // (2) 获取 bean 对象调用方法
        UserService userService1 = applicationContext.getBean("userService", UserService.class);
        UserService userService2 = applicationContext.getBean("userService", UserService.class);

        // (3) 配置 scope="prototype/singleton"
        System.out.println(userService1);
        System.out.println(userService2);

        // (4) 打印十六进制hash
        System.out.println(userService1 + " 十六进制hash: " + Integer.toHexString(userService1.hashCode()));
        System.out.println(ClassLayout.parseInstance(userService1).toPrintable());
    }

    @Test
    public void test_factory_bean() {
        // (1) 初始化 应用上下文
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // (2) 调用代理方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println("test result: " + userService.findUserInfo());
    }
}
