package com.knight.springframework.beans.factory;

/**
 * 实现此接口可以定义 BeanName
 * @desc
 * @author knight
 * @date 2024/3/16
 */
public interface BeanNameAware extends Aware {

    /**
     * 设置 BeanName(自定义beanName)
     * @param name
     */
    void setBeanName(String name);
}
