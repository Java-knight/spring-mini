package com.knight.springframework.test.common;

import com.knight.springframework.beans.BeansException;
import com.knight.springframework.beans.factory.config.BeanPostProcessor;
import com.knight.springframework.test.bean.UserService;

/**
 * @author knight
 * @desc
 * @date 2024/3/13
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("shanghai");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
