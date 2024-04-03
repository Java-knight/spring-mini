package com.knight.springframework.util;

/**
 * 类加载工具类
 * @desc
 * @author knight
 * @date 2023/7/29
 */
public class ClassUtils {

    /**
     * 获取当前线程的类加载器
     * @return
     */
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader classLoader = null;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        } catch (Throwable e) {
            // Cannot access thread context ClassLoader - falling back to system class loader...
        }

        if (null == classLoader) {
            // No thread context class loader -> use class loader of this class.
            classLoader = ClassUtils.class.getClassLoader();
        }
        return classLoader;
    }

    /**
     * 检查当前 clazz 是否是 cglib 生成的类
     * @param clazz
     * @return
     */
    public static boolean isCglibProxyClass(Class<?> clazz) {
        return (clazz != null && isCglibProxyClassName(clazz.getName()));
    }

    /**
     * 检查当前 className 是否是 cglib 生成的类
     * cglib 生成的类: className 中会包含 "$$"
     */
    public static boolean isCglibProxyClassName(String className) {
        return (className != null && className.contains("$$"));
    }
}
