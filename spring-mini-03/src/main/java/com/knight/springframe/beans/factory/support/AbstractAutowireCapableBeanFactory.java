package com.knight.springframe.beans.factory.support;

import com.knight.springframework.beans.BeansException;
import com.knight.springframework.beans.factory.config.BeanDefinition;

/**
 * {@link AbstractBeanFactory}抽象 bean 工厂的实现子类. createBean()发放的主要实现. 提供 bean的属性注入、初始化方法、自动装配、bean处理器
 * @desc
 * @author knight
 * @date 2023/7/28
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        addSingleton(beanName, bean);
        return bean;
    }
}
