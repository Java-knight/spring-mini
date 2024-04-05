package com.knight.springframework.aop;

/**
 * 类过滤器
 * @desc 限制切入点 或 引入到一组给定类的匹配过滤器
 * @author knight
 * @date 2024/4/3
 */
public interface ClassFilter {

    /**
     * 切入点应该应用给定的接口或目标类
     * @param clazz 目标类
     * @return
     */
    boolean matches(Class<?> clazz);
}
