package com.knight.springframework.beans.factory;

/**
 * 销毁 Bean
 * @author knight
 * @desc
 * @date 2024/3/13
 */
public interface DisposableBean {

    /**
     * Bean 对象销毁前调用
     * 类似于Java代码 Object#finalize() 方法
     * @throws Exception
     */
    void destroy() throws Exception;
}
