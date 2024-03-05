package com.knight.springframework.beans.factory.config;

import com.knight.springframework.beans.factory.BeanFactory;

/**
 * 扩展 {@link BeanFactory} 将由能够自动装配的 bean工厂实现, 前提是它们希望为现有 bean实例公开此功能
 * @desc
 * @author knight
 * @date 2023/8/5
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
}
