package com.knight.springframework.aop.aspectj;

import com.knight.springframework.aop.Pointcut;
import com.knight.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * 切点表达式执行/切面 通知器
 * @desc
 * @author knight
 * @date 2024/4/27
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    // 切面
    private AspectJExpressionPointcut pointcut;

    // 具体的拦截方法通知
    private Advice advice;

    // 表达式
    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
