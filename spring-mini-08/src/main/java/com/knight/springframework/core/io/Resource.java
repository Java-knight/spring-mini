package com.knight.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源加载器, 包含: classpath、本地文件(fileSystem)、远程文件(url)
 * @desc
 * @author knight
 * @date 2023/7/29
 */
public interface Resource {

    /**
     * 获取加载资源流
     * @return
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;
}
