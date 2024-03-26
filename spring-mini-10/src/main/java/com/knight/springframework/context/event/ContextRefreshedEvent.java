package com.knight.springframework.context.event;

/**
 * 应用上下文 刷新事件
 * @desc
 * @author knight
 * @date 2024/3/24
 */
public class ContextRefreshedEvent extends ApplicationContextEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
