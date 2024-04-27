package com.knight.springframework.beans.factory.config;

import com.knight.springframework.beans.BeansException;

/**
 * 初始化标记 Bean 对象扩展点
 *
 * @desc {@link BeanPostProcessor} 的子接口, 增加了一个实例化前的回调, 以及一个实例化后但是显式属性设置或自动装配发生在之前的回调
 * @author knight
 * @date 2024/4/27
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * 在 Bean 对象执行初始化方法之前, 执行此方法
     * 具体实现可以看子类
     * @param beanClass
     * @param beanName
     * @return 返回 bean对象可能是要使用代理而不是目标bean, 从而有效地抑制目标 bean 的默认实例化
     * @throws BeansException
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;
}
