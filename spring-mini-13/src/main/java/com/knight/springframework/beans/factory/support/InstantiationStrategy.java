package com.knight.springframework.beans.factory.support;

import com.knight.springframework.beans.BeansException;
import com.knight.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * bean 对象实例化策略
 * 策略模式
 * @desc
 * @author knight
 * @date 2023/7/29
 */
public interface InstantiationStrategy {

    /**
     * 实例化方法
     * @param beanDefinition
     * @param beanName
     * @param ctor  构造函数
     * @param args  构造函数参数
     * @return
     * @throws BeansException
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;
}
