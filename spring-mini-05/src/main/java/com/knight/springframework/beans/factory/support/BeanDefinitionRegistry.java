package com.knight.springframework.beans.factory.support;

import com.knight.springframework.beans.BeansException;
import com.knight.springframework.beans.factory.config.BeanDefinition;

/**
 * beanDefinition 注册
 * TODO 剩余 support & xml文件
 * @desc
 * @author knight
 * @date 2023/7/28
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册 beanDefinition
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 使用 beanName 获取 BeanDefinition
     * @param beanName
     * @return
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 判断是否包含指定 beanName 的 BeanDefinition
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 返回注册表中所有的 beanName
     * @return
     */
    String[] getBeanDefinitionNames();
}
