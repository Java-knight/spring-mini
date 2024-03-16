package com.knight.springframework.context.support;

import com.knight.springframework.beans.BeansException;
import com.knight.springframework.beans.factory.config.BeanPostProcessor;
import com.knight.springframework.context.ApplicationContext;
import com.knight.springframework.context.ApplicationContextAware;

/**
 * 应用上下文标记处理器. 通过在Bean对象初始化前后进行标记
 * @desc
 * @author knight
 * @date 2024/3/16
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware) {  // 将上下文设置
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
