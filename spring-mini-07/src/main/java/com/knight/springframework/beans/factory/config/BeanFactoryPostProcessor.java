package com.knight.springframework.beans.factory.config;

import com.knight.springframework.beans.BeansException;
import com.knight.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * 允许自定义修改 BeanDefinition 属性信息
 * @author knight
 * @desc
 * @date 2024/3/13
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所以的 BeanDefinition 加载完后, 实例化 Bean 对象之前, 提供修改 BeanDefinition 属性的机制
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
