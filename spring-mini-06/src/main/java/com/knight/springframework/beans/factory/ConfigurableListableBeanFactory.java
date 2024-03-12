package com.knight.springframework.beans.factory;

import com.knight.springframework.beans.BeansException;
import com.knight.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.knight.springframework.beans.factory.config.BeanDefinition;
import com.knight.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * 配置接口, 由大多数可列出的 bean 工厂实现.
 * 除了 {@link ConfigurableBeanFactory} 之外, 它提供了分析和修改 bean 定义以及预实例化单例的工具
 * @desc
 * @author knight
 * @date 2023/8/5
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    /**
     * 获取 beanDefinition 信息
     * @param beanName
     * @return
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 提前初始化单例bean
     * @throws BeansException
     */
    void preInstantiateSingletons() throws BeansException;

}
