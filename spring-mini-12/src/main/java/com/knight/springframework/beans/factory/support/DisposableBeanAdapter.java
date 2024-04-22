package com.knight.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.knight.springframework.beans.BeansException;
import com.knight.springframework.beans.factory.DisposableBean;
import com.knight.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * Bean对象 销毁适配器
 * @author knight
 * @desc 实现 {@link DisposableBean} 和 {@link Runnable}接口的适配器. 作用: 给 Bean 对象执行各种销毁步骤
 * @date 2024/3/14
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;

    private final String beanName;

    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        // (1) 实现接口 DisposableBean
        if (bean instanceof DisposableBean) {  // 实现接口
            ((DisposableBean) bean).destroy();
        }

        // (2) 注解配置 destroy-method (判断是为了避免二次执行销毁)
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean)) {  // 注解调用
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if (null == destroyMethod) {
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethod + "' on bean with name '"+ beanName + "'");
            }
            destroyMethod.invoke(bean);
        }
    }
}
