package com.knight.springframework.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * AOP 代理配置管理器基类(目标对象、方法拦截器、方法匹配器)
 *
 * @desc 这个本身不是 AOP 代理, 但是这个类的子类通常是工厂, 从这些工厂直接获取 AOP 代理实例
 * @author knight
 * @date 2024/4/5
 */
public class AdvisedSupport {

    // ProxyConfig
    private boolean proxyTargetClass = false;

    // 被代理的目标对象
    private TargetSource targetSource;

    // 方法拦截器(aop)
    private MethodInterceptor methodInterceptor;

    // 方法匹配器(自己实现)
    private MethodMatcher methodMatcher;

    public boolean isProxyTargetClass() {
        return proxyTargetClass;
    }

    public void setProxyTargetClass(boolean proxyTargetClass) {
        this.proxyTargetClass = proxyTargetClass;
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
