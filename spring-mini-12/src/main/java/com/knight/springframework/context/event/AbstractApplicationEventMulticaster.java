package com.knight.springframework.context.event;

import com.knight.springframework.beans.BeansException;
import com.knight.springframework.beans.factory.BeanFactory;
import com.knight.springframework.beans.factory.BeanFactoryAware;
import com.knight.springframework.context.ApplicationEvent;
import com.knight.springframework.context.ApplicationListener;
import com.knight.springframework.util.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 事件广播器基本实现(manager抽象类)
 * @desc 接口 {@link ApplicationEventMulticaster} 的抽象实现, 提供基本的监听器注册功能
 * @author knight
 * @date 2024/3/24
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    // 事件监听器工厂
    public final Set<ApplicationListener<ApplicationEvent>> applicationListenerSet = new LinkedHashSet<>();

    // bean 工厂
    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListenerSet.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListenerSet.remove(listener);
    }

    /**
     * 获取关注此事件的监听器
     * @param event
     * @return
     */
    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event) {
        LinkedList<ApplicationListener> followListeners = new LinkedList<>();
        for (ApplicationListener<ApplicationEvent> listener : applicationListenerSet) {
            if (supportsEvent(listener, event))   // 是否关注 event 事件
                followListeners.add(listener);
        }
        return followListeners;
    }

    /**
     * listener监听器 是否关注 event事件
     * 关注: 是通过 applicationListener 的非 cglib 创建的(类 —> 接口 —> 泛型参数), 判断event 是否是 listener 得到的泛型参数类型
     * 优点: 不需要调用者关注了, 不会说我们都发送了, 由监听器去过滤. Gson 序列化也使用了类似操作
     * @param applicationListener
     * @param event
     * @return
     */
    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
        Class<? extends ApplicationListener> listenerClass = applicationListener.getClass();

        // 按照 CglibSubClassingInstantiationStrategy、SimpleInstantiationStrategy 不同的实例化类型, 需要判断后获取目标 class
        // 获取监听器非 cglib 创建的(类 —> 接口 —> 泛型参数)
        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        Type genericInterface = targetClass.getGenericInterfaces()[0];

        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name: " + className);
        }

        // 判定此 eventClassName 对象所表示的类或者接口 与 指定 event.getClass() 参数所表示的类或接口是否相同, 或是否是其父类/父接口
        // isAssignableFrom: 用来判断子类和父类的关系; 或接口的实现类和接口的关系, 默认所有的类的最终父类是 Object.
        // 比如: A.isAssignableFrom(B) 返回true, 证明B可以转换为 A, 也就是A可以由B转换而来
        return eventClassName.isAssignableFrom(event.getClass());


    }
}
