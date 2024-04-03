package com.knight.springframework.context;

import java.util.EventObject;

/**
 * 应用事件(抽象类)
 * @desc 基于jdk的 {@link EventObject} 实现
 * @author knight
 * @date 2024/3/24
 */
public abstract class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
