package com.knight.springframework.beans.factory.config;

import com.knight.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * 由大多数 bean 工厂是实现的配置接口.
 * 除了在 {@link com.knight.springframework.beans.factory.BeanFactory} 中的 bean 工厂客户端之外, 还提供了配置 bean工厂的工具
 * @desc
 * @author knight
 * @date 2023/8/5
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";  // 单例

    String SCOPE_PROTOTYPE = "prototype";  // 原型
}
