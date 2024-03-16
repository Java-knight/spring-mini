package com.knight.springframework.beans.factory;

import com.knight.springframework.beans.BeansException;

import java.util.Map;

/**
 * {@link BeanFactory} 接口的扩展, 由可是枚举所有 bean 实例的 bean工厂实现, 而不是像客户端请求的那样逐个按名称进行 bean查找.
 * 预加载所有 bean 定义(如基于 xml 的工厂)的 beanFactory 实现可以实现这个接口.
 * @desc
 * @author knight
 * @date 2023/8/5
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 按照类型返回 Bean 实例
     * @param type
     * @return
     * @param <T>
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回注册 bean 集合的all_bean_name
     * @return
     */
    String[] getBeanDefinitionNames();
}
