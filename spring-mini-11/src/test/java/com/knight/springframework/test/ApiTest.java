package com.knight.springframework.test;

import com.knight.springframework.aop.AdvisedSupport;
import com.knight.springframework.aop.MethodMatcher;
import com.knight.springframework.aop.TargetSource;
import com.knight.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.knight.springframework.aop.framewok.Cglib2AopProxy;
import com.knight.springframework.aop.framewok.JdkDynamicAopProxy;
import com.knight.springframework.aop.framewok.ReflectiveMethodInvocation;
import com.knight.springframework.test.bean.IUserService;
import com.knight.springframework.test.bean.UserService;
import com.knight.springframework.test.bean.UserServiceInterceptor;
import org.aopalliance.intercept.MethodInterceptor;
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

    /**
     * 测试 aop
     * @throws NoSuchMethodException
     */
    @Test
    public void test_aop() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.knight.springframework.test.bean.UserService.*(..))");

        Class<UserService> clazz = UserService.class;
        Method method = clazz.getDeclaredMethod("findUserInfo");

        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));
    }

    @Test
    public void test_dynamic() {
        // 目标对象
        IUserService userService = new UserService();
        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.knight.springframework.test.bean.IUserService.*(..))"));

        // 代理对象(JdkDynamicAopProxy)
        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        // test jdk call
        System.out.println("jdk test result: " + proxy_jdk.findUserInfo());

        // 代理对象(Cglib2AopProxy)
        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        // test cglib call
        System.out.println("cglib test result: " + proxy_cglib.register("qishi"));
    }

    @Test
    public void test_proxy_class() {
        IUserService userService = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserService.class}, ((proxy, method, args) -> "your are proxy!"));
        String result = userService.findUserInfo();
        System.out.println("test result: " + result);
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
        System.out.println("test result：" + result);
    }
}
