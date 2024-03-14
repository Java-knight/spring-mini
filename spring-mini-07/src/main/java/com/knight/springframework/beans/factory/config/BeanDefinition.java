package com.knight.springframework.beans.factory.config;

import com.knight.springframework.beans.PropertyValues;

/**
 * bean 定义
 * @desc
 * @author knight
 * @date 2023/7/22
 */
@SuppressWarnings({"rawtypes"})
public class BeanDefinition {

    // bean对象类元信息
    private Class beanClass;

    // bean 属性值集合
    private PropertyValues propertyValues;

    // bean 初始化后调用(InitializingBean)[注解/xml配置使用]
    private String initMethodName;

    // bean 销毁前调用(DisposableBean)[注解/xml配置使用]
    private String destroyMethodName;

    public BeanDefinition(Class beanClass) {
        this(beanClass, null);
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }
}
