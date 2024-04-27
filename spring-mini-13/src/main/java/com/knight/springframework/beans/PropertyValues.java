package com.knight.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * bean 属性值集合
 * @desc
 * @author knight
 * @date 2023/7/29
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue propertyValue) {
        this.propertyValueList.add(propertyValue);
    }

    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue propertyValue : this.propertyValueList) {
            if (propertyValue.getName().equals(propertyName))
                return propertyValue;
        }
        return null;
    }
}
