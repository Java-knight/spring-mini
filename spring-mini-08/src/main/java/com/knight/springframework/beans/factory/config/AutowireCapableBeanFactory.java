package com.knight.springframework.beans.factory.config;

import com.knight.springframework.beans.BeansException;
import com.knight.springframework.beans.factory.BeanFactory;

/**
 * 扩展 {@link BeanFactory} 将由能够自动装配的 bean工厂实现, 前提是它们希望为现有 bean实例公开此功能
 * @desc
 * @author knight
 * @date 2023/8/5
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 执行 BeanPostProcessors 接口实现类的 {@link BeanPostProcessor#postProcessBeforeInitialization(Object, String)}
     * bean对象初始化之前执行
     * @param existingBean
     * @param beanName
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * 执行 BeanPostProcessors 接口实现类的 {@link BeanPostProcessor#postProcessAfterInitialization(Object, String)}
     * bean对象初始化之后执行
     * @param existingBean
     * @param beanName
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
