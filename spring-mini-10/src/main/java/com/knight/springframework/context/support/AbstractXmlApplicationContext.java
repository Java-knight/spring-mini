package com.knight.springframework.context.support;

import com.knight.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.knight.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 抽象 xml 应用上下文
 * @author knight
 * @desc 加载 bean定义 xml 配置
 * @date 2024/3/13
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinition(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    /**
     * 获取 xml 资源位置
     * @return
     */
    protected abstract String[] getConfigLocations();
}
