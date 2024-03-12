package com.knight.springframework.context.support;

import com.knight.springframework.beans.BeansException;
import com.knight.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.knight.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * 抽象刷新应用上下文. 支持对{@link #refresh()}多次调用, 每次创建一个新的内部 BeanFactory
 * @author knight
 * @desc
 * @date 2024/3/13
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinition(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    /**
     * 加载 BeanDefinition
     */
    protected abstract void loadBeanDefinition(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
