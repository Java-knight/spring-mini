package com.knight.springframework.aop.framework.autoproxy;

import com.knight.springframework.aop.*;
import com.knight.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.knight.springframework.aop.framework.ProxyFactory;
import com.knight.springframework.beans.BeansException;
import com.knight.springframework.beans.factory.BeanFactory;
import com.knight.springframework.beans.factory.BeanFactoryAware;
import com.knight.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.knight.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;

/**
 * 通知者 自动代理创建
 * @desc 基于当前 BeanFactory 中所有候选 advisor(通知者) 创建 AOP 代理的 BeanPostProcessor 实现.
 *       这个类是完全泛型; 它不包含处理任何特定方面的特殊代码, 例如池化方面
 * @author knight
 * @date 2024/4/27
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }



    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * 代理对象的创建:
     * 1. 从 beanFactory 获取切点表达式类型的对象
     * 2. 遍历 切点表达式类型对象集合
     * (1) 判断此切点是否match住这个beanClass
     * (2) 如果匹配, 需要构建通知&通知器管理者 AdvisedSupport 去构建代理工厂 ProxyFactory
     * AdvisedSupport构建信息: 创建代理对象-TargetSource; 方法拦截器-MethodInterceptor; 方法匹配器-MethodMatcher; 是否需要代理对象-false
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (isInfrastructureClass(beanClass))
            return null;

        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();

        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if (!classFilter.matches(beanClass)) {
                continue;
            }

            AdvisedSupport advisedSupport = new AdvisedSupport();

            TargetSource targetSource = null;
            // 创建代理对象
            try {
                targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 设置通知管理器[组装代理信息]
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(false);

            return new ProxyFactory(advisedSupport).getProxy();
        }
        return null;
    }

    /**
     * 是否是基础类(通知/通知者/切入点)
     * @param beanClass
     * @return
     */
    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) ||     // 通知
                Pointcut.class.isAssignableFrom(beanClass) ||  // 切入点
                Advisor.class.isAssignableFrom(beanClass);     // 通知者
    }
}
