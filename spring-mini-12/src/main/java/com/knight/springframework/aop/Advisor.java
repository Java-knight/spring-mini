package com.knight.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * 通知器接口
 * @desc
 * @author knight
 * @date 2024/4/27
 */
public interface Advisor {

    /**
     * 返回通知部分. 通知可以是 拦截器\before通知\抛出通知等
     * @return
     */
    Advice getAdvice();
}
