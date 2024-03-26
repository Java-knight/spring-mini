package com.knight.springframework.test.event;

import com.knight.springframework.context.ApplicationListener;
import com.knight.springframework.context.event.ContextClosedEvent;

/**
 * @author knight
 * @desc
 * @date 2024/3/26
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("close event: " + this.getClass().getName());
    }
}
