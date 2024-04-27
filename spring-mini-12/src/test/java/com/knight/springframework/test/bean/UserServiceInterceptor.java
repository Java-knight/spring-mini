package com.knight.springframework.test.bean;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author knight
 * @desc
 * @date 2024/4/22
 */
public class UserServiceInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return invocation.proceed();
        } finally {
            System.out.println("monitor: Begin By AOP");
            System.out.println("method name: " + invocation.getMethod());
            System.out.println("method consuming: " + (System.currentTimeMillis() - start) + "ms");
            System.out.println("monitor: End\r\n");
        }
    }
}
