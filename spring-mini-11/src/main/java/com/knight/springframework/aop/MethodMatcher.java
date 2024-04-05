package com.knight.springframework.aop;

import java.lang.reflect.Method;

/**
 * 方法匹配器
 * @desc {@link Pointcut} 切点的一部分: 检查目标方法是否有资格获得通知
 * @author knight
 * @date 2024/4/3
 */
public interface MethodMatcher {

    /**
     * 执行静态检查给定方法是否匹配
     * @param method 方法
     * @param targetClass 目标类
     * @return 此方法是否静态匹配
     */
    boolean matches(Method method, Class<?> targetClass);
}
