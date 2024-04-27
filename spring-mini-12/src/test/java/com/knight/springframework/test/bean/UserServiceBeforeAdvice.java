package com.knight.springframework.test.bean;

import com.knight.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author knight
 * @desc
 * @date 2024/4/27
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("method interceptor: " + method.getName());
    }
}
