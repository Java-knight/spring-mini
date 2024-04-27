package com.knight.springframework.aop.framework;

import com.knight.springframework.aop.AdvisedSupport;

/**
 * 代理工厂
 *
 * @desc 工厂用于编程使用 AOP代理, 而不是通过 Bean工厂. 该类提供了一种在代码中获取和配置 AOP代理的简单方法
 * @author knight
 * @date 2024/4/27
 */
public class ProxyFactory {

    // 通知器管理
    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {  // 是否代理
            return new Cglib2AopProxy(advisedSupport);
        }
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
