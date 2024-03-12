package com.knight.springframework.test.common;

import com.knight.springframework.beans.BeansException;
import com.knight.springframework.beans.PropertyValue;
import com.knight.springframework.beans.PropertyValues;
import com.knight.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.knight.springframework.beans.factory.config.BeanDefinition;
import com.knight.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @author knight
 * @desc
 * @date 2024/3/13
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "ByteDance"));
    }
}
