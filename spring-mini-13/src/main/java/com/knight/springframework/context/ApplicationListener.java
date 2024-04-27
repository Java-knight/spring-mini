package com.knight.springframework.context;

import java.util.EventListener;

/**
 * 应用事件监听器
 * @desc 基于jdk的 {@link EventListener} 实现
 * @author knight
 * @date 2024/3/24
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    /**
     * 处理应用事件
     * @param event
     */
    void onApplicationEvent(E event);
}
