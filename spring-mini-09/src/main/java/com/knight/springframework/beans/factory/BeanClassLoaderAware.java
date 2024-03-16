package com.knight.springframework.beans.factory;

/**
 * Bean 类加载器标记接口
 * @desc 允许 bean 知道 bean 的回调函数 {@link ClassLoader}. 当前 BeanFactory 用来加载 bean 类的类加载器
 * @author knight
 * @date 2024/3/16
 */
public interface BeanClassLoaderAware extends Aware {

    /**
     * 设置 bean 的类加载器
     * @param classLoader
     */
    void setBeanClassLoader(ClassLoader classLoader);
}
