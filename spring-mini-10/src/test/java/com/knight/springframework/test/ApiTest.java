package com.knight.springframework.test;

import com.knight.springframework.context.support.ClassPathXmlApplicationContext;
import com.knight.springframework.test.event.CustomEvent;
import org.junit.Test;

/**
 * @desc
 * @author knight
 * @date 2023/7/23
 */
public class ApiTest {

    @Test
    public void test_event() {
        // (1) 初始化 应用上下文
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        applicationContext.publishEvent(new CustomEvent(applicationContext, 2290707867L, "event machine success"));
    }
}
