package com.knight.springframework.beans;

/**
 * bean 属性信息
 * @desc
 * @author knight
 * @date 2023/7/29
 */
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
