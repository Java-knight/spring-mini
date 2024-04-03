package com.knight.springframework.context.event;

import com.knight.springframework.beans.factory.BeanFactory;
import com.knight.springframework.context.ApplicationEvent;
import com.knight.springframework.context.ApplicationListener;

/**
 * 简单应用事件广播器
 * @desc
 * @author knight
 * @date 2024/3/24
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(final ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);  // 执行回调方法
        }
    }
}
