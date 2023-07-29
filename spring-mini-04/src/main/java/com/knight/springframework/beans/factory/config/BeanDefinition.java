package com.knight.springframework.beans.factory.config;

import com.knight.springframework.beans.PropertyValue;
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
}
