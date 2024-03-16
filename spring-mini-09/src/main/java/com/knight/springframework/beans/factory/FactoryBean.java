package com.knight.springframework.beans.factory;

/**
 * 工厂Bean. 不同于普通的Bean, 用于给用户自定义工厂的. 场景: 创建AOP代理对象、Web工厂
 * @desc 接口将被 {@link com.knight.springframework.beans.factory.BeanFactory} 中使用的对象实现, 这些对象本身就是工厂.
 * 如果一个 bean 实现了这个接口, 它将被用作要公开的对象的工厂, 而不是直接作为将要公开自己的 bean 对象
 * @author knight
 * @date 2024/3/16
 */
public interface FactoryBean<T> {

    /**
     * 获取真正的 bean 对象
     * @return
     * @throws Exception
     */
    T getObject() throws Exception;

    /**
     * 获取 bean 对象类型
     * @return
     */
    Class<?> getObjectType();

    /**
     * 是否单例
     * @return
     */
    boolean isSingleton();
}
