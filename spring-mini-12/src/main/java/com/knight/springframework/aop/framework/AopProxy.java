package com.knight.springframework.aop.framework;

/**
 * AOP 代理的抽象
 * @desc JDK 动态代理和 cglib 代理都有开箱即用的实现, 如 DefaultAopProxyFactory 所应用的那样
 * @author knight
 * @date 2024/4/5
 */
public interface AopProxy {

    /**
     * 获取代理对象
     * @return
     */
    Object getProxy();
}
