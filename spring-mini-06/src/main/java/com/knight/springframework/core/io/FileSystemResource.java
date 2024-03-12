package com.knight.springframework.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * file 资源
 * 通过指定文件路径的方式读取文件信息时会读取到一些 txt/excel 文件, 将这些文件输出到控制台
 * @desc
 * @author knight
 * @date 2023/7/29
 */
public class FileSystemResource implements Resource {

    private final String path;

    // 文件
    private final File file;

    public FileSystemResource(String path) {
        this.file = new File(path);
        this.path = path;
    }

    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getPath();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    public String getPath() {
        return path;
    }
}
