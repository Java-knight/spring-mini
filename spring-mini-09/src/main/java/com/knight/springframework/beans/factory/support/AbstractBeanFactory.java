package com.knight.springframework.beans.factory.support;

import com.knight.springframework.beans.BeansException;
import com.knight.springframework.beans.factory.FactoryBean;
import com.knight.springframework.beans.factory.BeanFactory;
import com.knight.springframework.beans.factory.config.BeanDefinition;
import com.knight.springframework.beans.factory.config.BeanPostProcessor;
import com.knight.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.knight.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象实现 {@link BeanFactory} 接口, 提供一个模板的 getBean 流程, 其子类只需要关注具体的子方法实现
 * 模板设计模式
 * @desc
 * @author knight
 * @date 2023/7/28
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    /**
     * 当前 beanFactory 的类加载器
     */
    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    /**
     * bean 对象处理器
     */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    protected <T> T doGetBean(final String name, Object[] args) {
        // (1) 首先从 单例Bean缓存 中获取bean对象
        Object sharedInstance = getSingleton(name);
        if (sharedInstance != null) {
            // 如果是 FactoryBean, 则需要调用 FactoryBean#getObject
            return (T) getObjectForBeanInstance(sharedInstance, name);
        }

        // (2) bean 不是单例 或 这个单例bean第一次进行初始化
        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition, args);
        return (T) getObjectForBeanInstance(bean, name);
    }

    /**
     * 获取真正的 bean 对象
     * 如果是 FactoryBean, 则需要调用 FactoryBean#getObject
     * @param beanInstance
     * @param beanName
     * @return
     */
    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        if (!(beanInstance instanceof FactoryBean)) {  // 普通 bean 直接返回
            return beanInstance;
        }

        Object object = getCachedObjectForFactoryBean(beanName);
        if (null == object) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean, beanName);
        }
        return object;
    }

    /**
     * 获取单例 bean定义信息[类似于jvm在元空间获取创建对象的类元信息]
     * @param beanName
     * @return
     * @throws BeansException
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 创建 bean 对象, 根据 beanName & bean定义信息 创建对象
     * @param beanName
     * @param beanDefinition
     * @param args  含参构造参数
     * @return
     * @throws BeansException
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }

    public ClassLoader getBeanClassLoader() {
        return beanClassLoader;
    }
}
