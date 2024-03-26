package com.knight.springframework.context;

import com.knight.springframework.beans.factory.HierarchicalBeanFactory;
import com.knight.springframework.beans.factory.ListableBeanFactory;
import com.knight.springframework.core.io.ResourceLoader;

/**
 * 应用上下文
 * @author knight
 * @desc
 * @date 2024/3/13
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
