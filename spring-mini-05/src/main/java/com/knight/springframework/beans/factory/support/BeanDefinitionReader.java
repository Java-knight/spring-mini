package com.knight.springframework.beans.factory.support;

import com.knight.springframework.beans.BeansException;
import com.knight.springframework.core.io.Resource;
import com.knight.springframework.core.io.ResourceLoader;

/**
 * beanDefinition 读取器接口
 * @desc
 * @author knight
 * @date 2023/8/5
 */
public interface BeanDefinitionReader {

    /**
     * 获取 beanDefinition 注册器
     * @return
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 获取资源加载器
     * @return
     */
    ResourceLoader getResourceLoader();

    /**
     * 加载 beanDefinition 信息[resource —> all_bean_definition]
     * @param resource
     * @throws BeansException
     */
    void loadBeanDefinitions(Resource resource) throws BeansException;

    /**
     * 加载 beanDefinition 信息[resource —> all_bean_definition]
     * 多个配置文件[resource文件]
     * @param resources
     * @throws BeansException
     */
    void loadBeanDefinitions(Resource... resources) throws BeansException;

    /**
     * 加载 beanDefinition 信息[location位置中的all_resource —> all_bean_definition]
     * @param location
     * @throws BeansException
     */
    void loadBeanDefinitions(String location) throws BeansException;

    /**
     * 加载 beanDefinition 信息[location位置中的all_resource —> all_bean_definition]
     * 多个配置路径
     * @param locations
     * @throws BeansException
     */
    void loadBeanDefinitions(String... locations) throws BeansException;
}
