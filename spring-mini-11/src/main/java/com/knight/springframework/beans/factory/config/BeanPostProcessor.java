package com.knight.springframework.beans.factory.config;

import com.knight.springframework.beans.BeansException;

/**
 * 用于修改实例 Bean 对象的扩展点
 * @author knight
 * @desc 允许自定义修改新 bean 对象的工厂钩子. 场景: 检查标记接口或用代理包装
 * @date 2024/3/13
 */
public interface BeanPostProcessor {

    /**
     * 在 bean 对象执行初始化之前, 执行此方法
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在 bean 对象执行初始化之前, 执行此方法
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
