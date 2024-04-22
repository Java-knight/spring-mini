package com.knight.springframework.context.event;

import com.knight.springframework.context.ApplicationEvent;
import com.knight.springframework.context.ApplicationListener;

/**
 * 事件广播器(manager)
 * @desc
 * @author knight
 * @date 2024/3/24
 */
public interface ApplicationEventMulticaster {

    /**
     * 添加一个事件监听器
     * @param listener
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * 删除一个事件监听器
     * @param listener
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * 广播事件
     * @param event
     */
    void multicastEvent(ApplicationEvent event);
}
