package com.knight.springframework.core.io;

/**
 * 资源加载器
 * @desc
 * @author knight
 * @date 2023/7/29
 */
public interface ResourceLoader {

    /**
     * 从类路径加载伪 url 前缀
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 获取资源
     * @param location 资源地址
     * @return
     */
    Resource getResource(String location);
}
