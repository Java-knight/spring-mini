package com.knight.springframework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * bean 工厂
 * @desc
 * @author knight
 * @date 2023/7/23
 */
public class BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public Object getBean(String name) {
        return beanDefinitionMap.get(name).getBean();
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }
}
