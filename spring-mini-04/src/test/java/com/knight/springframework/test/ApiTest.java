package com.knight.springframework.test;

import com.knight.springframework.beans.PropertyValue;
import com.knight.springframework.beans.PropertyValues;
import com.knight.springframework.beans.factory.config.BeanDefinition;
import com.knight.springframework.beans.factory.config.BeanReference;
import com.knight.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.knight.springframework.test.bean.UserDao;
import com.knight.springframework.test.bean.UserService;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // (3) userService 设置属性[uid, userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uid", "10002"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        // (4) UserService 注入 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // (5) 获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.findUserInfo();
    }

}
