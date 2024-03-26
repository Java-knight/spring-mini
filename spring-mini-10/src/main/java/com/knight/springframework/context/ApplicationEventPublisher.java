package com.knight.springframework.context;

/**
 * 事件发布/注册者接口
 * @desc 发布 {@link ApplicationEvent} 事件
 * @author knight
 * @date 2024/3/24
 */
public interface ApplicationEventPublisher {

    /**
     * 发布/注册事件(事件可以是框架事件/特定程序的事件)
     * @param event
     */
    void publishEvent(ApplicationEvent event);
}
