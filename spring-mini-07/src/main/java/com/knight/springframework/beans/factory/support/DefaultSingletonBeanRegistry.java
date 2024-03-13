package com.knight.springframework.beans.factory.support;

import com.knight.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 默认单例 bean 注册器[对象信息]
 * @desc
 * @author knight
 * @date 2023/7/28
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    // 单例 bean 注册器
    private final Map<String, Object> singletonObjectMap = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjectMap.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjectMap.put(beanName, singletonObject);
    }
}
