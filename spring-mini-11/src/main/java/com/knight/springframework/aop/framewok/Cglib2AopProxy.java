package com.knight.springframework.aop.framewok;

import com.knight.springframework.aop.AdvisedSupport;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib 动态代理实现
 *
 * Spring Aop 框架基于 cglib2 {@link AopProxy} 实现
 * @desc 这种类型的对象应该通过代理工厂获得, 由一个 AdvisedSupport 对象配置. 该类位于 Spring AOP框架内部, 不需要调用者去实现
 * @author knight
 * @date 2024/4/5
 */
public class Cglib2AopProxy implements AopProxy {

    // aop 代理对象(父类)
    private final AdvisedSupport advised;

    public Cglib2AopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        return null;
    }

    /**
     * 动态代理拦截器
     */
    private static class DynamicAdvisedInterceptor implements MethodInterceptor {

        private final AdvisedSupport advised;

        public DynamicAdvisedInterceptor(AdvisedSupport advised) {
            this.advised = advised;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            CglibMethodInvocation methodInvocation = new CglibMethodInvocation(advised.getTargetSource(), method, objects, methodProxy);
            if (advised.getMethodMatcher().matches(method, advised.getTargetSource().getTarget().getClass())) {  // 方法匹配器匹配到了
                return advised.getMethodInterceptor().invoke(methodInvocation);
            }
            // cglib 没有方法匹配器匹配到, 也会自动走方法代理的
            return methodInvocation.proceed();
        }
    }

    /**
     * 反射 cglib 方法调用
     */
    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {

        private final MethodProxy methodProxy;

        public CglibMethodInvocation(Object target, Method method, Object[] arguments, MethodProxy methodProxy) {
            super(target, method, arguments);
            this.methodProxy = methodProxy;
        }

        @Override
        public Object proceed() throws Throwable {
            return this.methodProxy.invoke(this.target, this.arguments);
        }
    }
}
