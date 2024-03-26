package com.knight.springframework.context.support;

import com.knight.springframework.beans.BeansException;
import com.knight.springframework.context.ApplicationEvent;

/**
 * xml 文件应用上下文
 * @author knight
 * @desc
 * @date 2024/3/13
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String[] configLocations;

    public ClassPathXmlApplicationContext() {
    }

    public ClassPathXmlApplicationContext(String configLocation) throws BeansException {
        this(new String[]{configLocation});
    }

    /**
     * 从 xml 中加载 BeanDefinition, 并刷新上下文
     */
    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}
