package com.knight.springframework.beans.factory;

import com.knight.springframework.beans.BeansException;

/**
 * bean 工厂
 * @desc
 * @author knight
 * @date 2023/7/23
 */
public interface BeanFactory {

    /**
     * 获取 bean 对象
     * @param name
     * @return
     * @throws BeansException
     */
    Object getBean(String name) throws BeansException;

    /**
     * 获取 bean 对象, 含有参数[含参构造函数]
     * @param name
     * @param args
     * @return
     * @throws BeansException
     */
    Object getBean(String name, Object... args) throws BeansException;
}
