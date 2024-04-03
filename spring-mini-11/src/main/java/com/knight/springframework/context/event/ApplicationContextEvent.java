package com.knight.springframework.context.event;

import com.knight.springframework.context.ApplicationContext;
import com.knight.springframework.context.ApplicationEvent;

/**
 * 应用程序上下文事件
 * @desc
 * @author knight
 * @date 2024/3/24
 */
public class ApplicationContextEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    /**
     * 获取触发事件的 applicationContext
     * @return
     */
    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
