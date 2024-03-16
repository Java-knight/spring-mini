package com.knight.springframework.core.io;

import cn.hutool.core.lang.Assert;
import com.knight.springframework.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * classpath下的资源
 * 通过 classloader 读取 classpath 中的文件信息实现, 读取方法: classPath.getResourceAsStream(path)
 * @desc
 * @author knight
 * @date 2023/7/29
 */
public class ClassPathResource implements Resource {

    // 路径
    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "Path must not be null");
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = classLoader.getResourceAsStream(path);
        if (null == inputStream) {
            throw new FileNotFoundException(this.path + "cannot be opened because it does not exist.");
        }
        return inputStream;
    }
}
