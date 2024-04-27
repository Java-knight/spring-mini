package com.knight.springframework.aop;

/**
 * 切点通知器
 * @desc
 * @author knight
 * @date 2024/4/27
 */
public interface PointcutAdvisor extends Advisor {

    /**
     * 获取通知器的切入点
     * @return
     */
    Pointcut getPointcut();
}
