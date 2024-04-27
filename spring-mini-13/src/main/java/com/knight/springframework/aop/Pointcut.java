package com.knight.springframework.aop;

/**
 * 切点(核心 Spring 切入点抽象)
 * @desc 切入点由 {@link ClassFilter} 和 {@link MethodMatcher} 组成. 这些基本术语和切入点本身都可以组合起来
 * @author knight
 * @date 2024/4/3
 */
public interface Pointcut {

    /**
     * 返回此切入点的 ClassFilter. 不能为null
     */
    ClassFilter getClassFilter();

    /**
     * 发生这个切入点的 MethodMatcher. 不能为null
     */
    MethodMatcher getMethodMatcher();
}
