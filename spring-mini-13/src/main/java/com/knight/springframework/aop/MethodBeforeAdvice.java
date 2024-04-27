package com.knight.springframework.aop;

import java.lang.reflect.Method;

/**
 * 调用方法之前的通知
 * 这样的通知不能阻止方法调用过程, 除非它们抛出一个 Throwable
 * @desc
 * @author knight
 * @date 2024/4/27
 */
public interface MethodBeforeAdvice extends BeforeAdvice {

    /**
     * 调用给定方法前的回调
     * @param method 被调用的方法
     * @param args 方法参数
     * @param target 方法调用的目标对象, 可以为 null
     * @throws Throwable 异常此对象终止调用.
     *                   如果方法签名允许, 抛出的任何异常都将返回给调用者. 否则异常将被包装为 RuntimeException
     */
    void before(Method method, Object[] args, Object target) throws Throwable;

}
