package com.knight.springframework.beans.factory.config;

/**
 * bean 引用
 * @desc
 * @author knight
 * @date 2023/7/29
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
