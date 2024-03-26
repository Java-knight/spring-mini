package com.knight.springframework.test.event;

import com.knight.springframework.context.ApplicationListener;
import com.knight.springframework.context.event.ContextRefreshedEvent;

/**
 * @author knight
 * @desc
 * @date 2024/3/26
 */
public class ContextRefreshEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("refresh event: " + this.getClass().getName());
    }
}
