package com.knight.springframework.test;

import com.knight.springframework.BeanDefinition;
import com.knight.springframework.BeanFactory;
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
        BeanFactory beanFactory = new BeanFactory();

        // (2) 注入 bean
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // (3) 获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.findUserInfo();
    }
}
