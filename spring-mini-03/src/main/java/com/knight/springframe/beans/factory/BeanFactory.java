package com.knight.springframe.beans.factory;

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
}
