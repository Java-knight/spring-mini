package com.knight.springframe.beans.factory.support;

import com.knight.springframework.beans.factory.config.BeanDefinition;

/**
 * beanDefinition 注册
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
}
