package com.knight.springframework.test;

import com.knight.springframework.beans.factory.config.BeanDefinition;
import com.knight.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.knight.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * @desc
 * @author knight
 * @date 2023/7/23
 */
public class ApiTest {

    @Test
    public void test_BeanFactory() {
        // (1) 初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // (2) 注入 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // (3) 第一次获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.findUserInfo();

        // (3) 第一次获取 bean
        UserService userService_singleton = (UserService) beanFactory.getSingleton("userService");
        userService_singleton.findUserInfo();
    }
}
