package com.knight.springframework;

/**
 * bean 定义
 * @desc
 * @author knight
 * @date 2023/7/22
 */
public class BeanDefinition {

    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }
}
