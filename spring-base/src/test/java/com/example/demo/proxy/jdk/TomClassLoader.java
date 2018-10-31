package com.example.demo.proxy.jdk;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 代码生成，编译，重新load到ＪＶＭ
 */
public class TomClassLoader extends ClassLoader {
    private File baseDir = null;

    public TomClassLoader() {
        String basePath = TomClassLoader.class.getResource("").getPath();
        this.baseDir = new File(basePath);
    }

    public Class<?> findClass(String name) {
        String className = TomClassLoader.class.getPackage().getName() + "." + name;
        if (baseDir != null) {

            File classFile = new File(baseDir, name.replaceAll("\\.", "/") + ".class");

            if (classFile.exists()) {
                try {
                    byte[] bytes = FileUtils.readFileToByteArray(classFile);
                    return defineClass(className, bytes, 0, bytes.length);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    FileUtils.deleteQuietly(classFile);
                }


                /*FileInputStream in = null;
                try {
                    in = new FileInputStream(classFile);
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    byte[] buff = new byte[1024];
                    int len;
                    while ((len = in.read(buff)) != -1) {
                        out.write(buff, 0, len);
                    }
                    return defineClass(className, out.toByteArray(), 0, out.size());

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (null != in) {
                        IOUtils.closeQuietly(in);
                    }
                }*/
            }
        }
        return null;
    }
}
