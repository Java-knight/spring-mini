package com.knight.springframework.test;

import com.knight.springframework.aop.AdvisedSupport;
import com.knight.springframework.aop.ClassFilter;
import com.knight.springframework.aop.MethodMatcher;
import com.knight.springframework.aop.TargetSource;
import com.knight.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.knight.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.knight.springframework.aop.framework.Cglib2AopProxy;
import com.knight.springframework.aop.framework.JdkDynamicAopProxy;
import com.knight.springframework.aop.framework.ProxyFactory;
import com.knight.springframework.aop.framework.ReflectiveMethodInvocation;
import com.knight.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import com.knight.springframework.context.support.ClassPathXmlApplicationContext;
import com.knight.springframework.test.bean.IUserService;
import com.knight.springframework.test.bean.UserService;
import com.knight.springframework.test.bean.UserServiceBeforeAdvice;
import com.knight.springframework.test.bean.UserServiceInterceptor;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @desc
 * @author knight
 * @date 2023/7/23
 */
public class ApiTest {

    private AdvisedSupport advisedSupport;

    @Before
    public void init() {
        // 目标对象
        IUserService userService = new UserService();
        // 组装代理信息
        advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.knight.springframework.test.bean.IUserService.*(..))"));
    }

    @Test
    public void test_proxyFactory() {
        advisedSupport.setProxyTargetClass(false);
        IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();

        System.out.println("[test_proxyFactory] test result: " + proxy.findUserInfo());
    }

    @Test
    public void test_beforeAdvice() {
        UserServiceBeforeAdvice beforeAdvice = new UserServiceBeforeAdvice();
        MethodBeforeAdviceInterceptor interceptor = new MethodBeforeAdviceInterceptor(beforeAdvice);
        advisedSupport.setMethodInterceptor(interceptor);

        IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();
        System.out.println("[test_beforeAdvice] test result: " + proxy.findUserInfo());
    }

    @Test
    public void test_advisor() {
        // 目标对象
        IUserService userService = new UserService();

        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression("execution(* com.knight.springframework.test.bean.IUserService.*(..))");
        advisor.setAdvice(new MethodBeforeAdviceInterceptor(new UserServiceBeforeAdvice()));

        ClassFilter classFilter = advisor.getPointcut().getClassFilter();
        if (classFilter.matches(userService.getClass())) {
            // 组装代理信息
            AdvisedSupport advised = new AdvisedSupport();

            TargetSource targetSource = new TargetSource(userService);
            advised.setTargetSource(targetSource);
            advised.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advised.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advised.setProxyTargetClass(true);  // false/true JDK动态代理/CGLib动态代理

            IUserService proxy = (IUserService) new ProxyFactory(advised).getProxy();
            System.out.println("[test_advisor] test result: " + proxy.findUserInfo());
        }
    }


    /**
     * 测试 aop
     */
    @Test
    public void test_aop() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");

        IUserService userService = context.getBean("userService", IUserService.class);
        System.out.println("[test_aop] test result: " + userService.findUserInfo());
    }

    @Test
    public void test_proxy_method() {
        // 目标对象(可以替换成任何的目标对象)
        Object targetObj = new UserService();

        // AOP 代理
        IUserService proxy = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), targetObj.getClass().getInterfaces(), new InvocationHandler() {
            // 方法匹配器
            MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* com.knight.springframework.test.bean.IUserService.*(..))");

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (methodMatcher.matches(method, targetObj.getClass())) {
                    // 方法拦截器
                    MethodInterceptor methodInterceptor = invocation -> {
                        long start = System.currentTimeMillis();
                        try {
                            return invocation.proceed();
                        } finally {
                            System.out.println("monitor: Begin By AOP");
                            System.out.println("method name: " + invocation.getMethod());
                            System.out.println("method consuming: " + (System.currentTimeMillis() - start) + "ms");
                            System.out.println("monitor: End\r\n");
                        }
                    };
                    // 反射调用
                    return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObj, method, args));
                }
                return method.invoke(targetObj, args);
            }
        });

        String result = proxy.findUserInfo();
        System.out.println("[test_proxy_method] test result：" + result);
    }
}
