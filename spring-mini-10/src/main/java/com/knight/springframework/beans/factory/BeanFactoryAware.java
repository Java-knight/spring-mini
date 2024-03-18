package com.knight.springframework.beans.factory;

import com.knight.springframework.beans.BeansException;

/**
 * 实现此接口, 可以感知到所属的 BeanFactory
 * @desc
 * @author knight
 * @date 2024/3/16
 */
public interface BeanFactoryAware extends Aware {

    /**
     * 设置 BeanFactory
     * @param beanFactory
     * @throws BeansException
     */
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
