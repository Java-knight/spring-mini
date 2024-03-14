package com.knight.springframework.context;

import com.knight.springframework.beans.BeansException;

/**
 * SPI 接口将由大多数应用程序上下文实现.
 * 配置应用上下文
 * @author knight
 * @desc
 * @date 2024/3/13
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     * @throws BeansException
     */
    void refresh() throws BeansException;

    /**
     * 注册 context 关闭钩子函数
     */
    void registerShutdownHook();

    /**
     * context 关闭
     */
    void close();
}
