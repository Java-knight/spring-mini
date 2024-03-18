package com.knight.springframework.beans.factory.support;

import com.knight.springframework.core.io.DefaultResourceLoader;
import com.knight.springframework.core.io.ResourceLoader;

/**
 * 实现 {@link BeanDefinitionReader} 接口的beanDefinition 读取器的抽象基类
 * @desc
 * @author knight
 * @date 2023/8/5
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
