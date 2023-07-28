package com.knight.springframe.beans;

/**
 * bean 异常类
 * @desc
 * @author knight
 * @date 2023/7/23
 */
public class BeansException extends RuntimeException {

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
