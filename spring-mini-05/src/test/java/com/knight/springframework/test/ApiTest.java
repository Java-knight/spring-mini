package com.knight.springframework.test;

import cn.hutool.core.io.IoUtil;
import com.knight.springframework.beans.PropertyValue;
import com.knight.springframework.beans.PropertyValues;
import com.knight.springframework.beans.factory.config.BeanDefinition;
import com.knight.springframework.beans.factory.config.BeanReference;
import com.knight.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.knight.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.knight.springframework.core.io.DefaultResourceLoader;
import com.knight.springframework.core.io.Resource;
import com.knight.springframework.test.bean.UserDao;
import com.knight.springframework.test.bean.UserService;
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

        // (2) 注入 bean
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // (3) userService 设置属性[uid, userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uid", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        // (4) UserService 注入 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // (5) 获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        String result = userService.findUserInfo();

        System.out.println("test result: " + result);
    }

    private DefaultResourceLoader resourceLoader;

    @Before
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }
    @Test
    public void test_classpath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_file() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println("local_file" + content);
    }

    @Test
    public void test_url() throws IOException {
        Resource resource = resourceLoader.getResource("https://github.com/Java-knight/spring-mini/blob/master/spring-mini-05/src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println("url: " + content);
    }

    @Test
    public void test_xml() {
        // (1) 初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // (2) 读取配置文件 & 注册 bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // (3) 获取 bean 对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        String result = userService.findUserInfo();
        System.out.println(result);
    }
}
