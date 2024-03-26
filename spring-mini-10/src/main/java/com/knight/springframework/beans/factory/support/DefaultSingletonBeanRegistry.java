package com.knight.springframework.beans.factory.support;

import com.knight.springframework.beans.BeansException;
import com.knight.springframework.beans.factory.DisposableBean;
import com.knight.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 默认单例 bean 注册器[对象信息]
 * @desc
 * @author knight
 * @date 2023/7/28
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * 空单例对象的内部标记
     * 是作为并发映射的标记值(不支持空值)
     */
    protected static final Object NULL_OBJECT = new Object();

    // 单例 bean 注册器
    private Map<String, Object> singletonObjectMap = new HashMap<>();

    // 销毁 bean 注册器
    private final Map<String, DisposableBean> disposableBeanMap = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjectMap.get(beanName);
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjectMap.put(beanName, singletonObject);
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjectMap.put(beanName, singletonObject);
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeanMap.put(beanName, bean);
    }

    public void destroySingletons() {
        Set<String> keySet = this.disposableBeanMap.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeanMap.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' throw an exception", e);
            }
        }
    }
}
