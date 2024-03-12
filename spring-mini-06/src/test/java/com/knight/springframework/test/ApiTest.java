package com.knight.springframework.test;

import cn.hutool.core.io.IoUtil;
import com.knight.springframework.beans.PropertyValue;
import com.knight.springframework.beans.PropertyValues;
import com.knight.springframework.beans.factory.config.BeanDefinition;
import com.knight.springframework.beans.factory.config.BeanReference;
import com.knight.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.knight.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.knight.springframework.context.support.ClassPathXmlApplicationContext;
import com.knight.springframework.core.io.DefaultResourceLoader;
import com.knight.springframework.core.io.Resource;
import com.knight.springframework.test.bean.UserDao;
import com.knight.springframework.test.bean.UserService;
import com.knight.springframework.test.common.MyBeanFactoryPostProcessor;
import com.knight.springframework.test.common.MyBeanPostProcessor;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

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

        // (2) 读取配置文件&注册 Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");
        /** 这里不能获取, 获取之后这个 bean 对象和就创建在放在缓存中了,　下次就不能执行　BeanFactoryPostProcessor, BeanPostProcessor*/
//        UserService userServiceBefore = beanFactory.getBean("userService", UserService.class);
//        String resultBefore = userServiceBefore.findUserInfo();
//        System.out.println("test resultBefore: " + resultBefore);

        // (3) BeanDefinition 加载完成 & Bean 实例化之前, 修改 BeanDefinition 的属性值
        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        // (4) Bean 实例化之后, 修改 Bean 属性信息
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        // (5) 获取 bean 对象调用发放
        UserService userServiceAfter = beanFactory.getBean("userService", UserService.class);
        String resultAfter = userServiceAfter.findUserInfo();
        System.out.println("test resultAfter: " + resultAfter);
    }

    @Test
    public void test_xml() {
        // (1) 初始化 应用上下文
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springPostProcessor.xml");

        // (2) 获取 bean 对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.findUserInfo();
        System.out.println(result);
    }
}
