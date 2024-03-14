package com.knight.springframework.context.support;

import com.knight.springframework.beans.BeansException;
import com.knight.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.knight.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.knight.springframework.beans.factory.config.BeanPostProcessor;
import com.knight.springframework.context.ConfigurableApplicationContext;
import com.knight.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * 抽象应用上下文
 * @author knight
 * @desc
 * @date 2024/3/13
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    /**
     * (1) 创建 BeanFactory, 并加载 BeanDefinition
     * (2) 获取 BeanFactory
     * (3) 在 Bean 实例化之前, 执行 BeanFactoryPostProcessor[调用 context 注册 beanFactoryProcessor]
     * (4) BeanPostProcessor 需要提前于其他 Bean 对象实例化之前进行注册
     * (5) 提前实例化单例 Bean 对象
     * @throws BeansException
     */
    @Override
    public void refresh() throws BeansException {
        // (1) 创建 BeanFactory, 并加载 BeanDefinition
        refreshBeanFactory();

        // (2) 获取 BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // (3) 在 Bean 实例化之前, 执行 BeanFactoryPostProcessor[调用 context 注册 beanFactoryProcessor]
        invokeBeanFactoryPostProcessors(beanFactory);

        // (4) BeanPostProcessor 需要提前于其他 Bean 对象实例化之前进行注册
        registerBeanPostProcessors(beanFactory);

        // (5) 提前实例化单例 Bean 对象
        beanFactory.preInstantiateSingletons();
    }

    /**
     * 创建 BeanFactory, 并加载 BeanDefinition
     * @throws BeansException
     */
    protected abstract void refreshBeanFactory() throws BeansException;

    /**
     * 获取 BeanFactory
     * @return
     */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    /**
     * 在 Bean 实例化之前, 执行 BeanFactoryPostProcessor[调用 context 注册 beanFactoryProcessor]
     * @param beanFactory
     */
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * BeanPostProcessor 需要提前于其他 Bean 对象实例化之前进行注册
     * @param beanFactory
     */
    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        getBeanFactory().destroySingletons();
    }
}
