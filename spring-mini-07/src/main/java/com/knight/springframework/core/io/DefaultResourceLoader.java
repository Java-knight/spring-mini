package com.knight.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 默认资源加载器的实现
 * 主要是包装了三种资源的获取, 分别判断了 classpath、filesystem、url 文件
 * @desc
 * @author knight
 * @date 2023/7/29
 */
public class DefaultResourceLoader implements ResourceLoader {

    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "Location must not be null.");
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {  // classpath
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                URL url = new URL(location);
                return new UrlResource(url);  // url
            } catch (MalformedURLException e) {  // filesystem
                return new FileSystemResource(location);
            }
        }
    }
}
