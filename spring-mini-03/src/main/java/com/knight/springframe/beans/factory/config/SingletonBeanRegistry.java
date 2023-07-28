package com.knight.springframe.beans.factory.config;

/**
 * 单例 bean 注册
 * @desc
 * @author knight
 * @date 2023/7/23
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

}
