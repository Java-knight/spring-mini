package com.knight.springframework.context;

import com.knight.springframework.beans.BeansException;
import com.knight.springframework.beans.factory.Aware;

/**
 * 容器标记接口; 实现此接口可以感知到所属的 ApplicationContext
 * @desc
 * @author knight
 * @date 2024/3/16
 */
public interface ApplicationContextAware extends Aware {

    /**
     * 设置 应用上下文(给当前 bean 对象重新设置一个 applicationContext)
     * @param applicationContext
     * @throws BeansException
     */
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
