package com.knight.springframework.test.event;

import com.knight.springframework.context.ApplicationListener;

import java.util.Date;

/**
 * @author knight
 * @desc
 * @date 2024/3/26
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("receive: " + event.getSource() + " message; time: " + new Date());
        System.out.println("message id: " + event.getId() + "; content: " + event.getMessage());
    }
}
