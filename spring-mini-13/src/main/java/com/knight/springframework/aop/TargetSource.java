package com.knight.springframework.aop;

/**
 * 被代理的目标对象
 * @desc 获取当前 “目标” 一个AOP调用, 如果不存在, 它将通过反射调用. 建议选择终止拦截器链本身
 * @author knight
 * @date 2024/4/3
 */
public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    /**
     * 返回 target的目标类型(存在泛型返回真实类型)
     */
    public Class<?>[] getTargetClass() {
        return this.target.getClass().getInterfaces();
    }

    /**
     * 返回目标实例. 在 AOP 框架调用AOP方法调用的 “target” 之前立即调用.
     */
    public Object getTarget() {
        return target;
    }
}
