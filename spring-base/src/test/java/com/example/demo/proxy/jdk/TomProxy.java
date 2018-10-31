package com.example.demo.proxy.jdk;

import org.apache.commons.io.FileUtils;
import sun.reflect.CallerSensitive;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 生成代理对象源代码
 */
public class TomProxy {

    public static final String SEP = "\n";

    @CallerSensitive
    public static Object newProxyInstance(TomClassLoader loader,
                                          Class<?>[] interfaces,
                                          TomInvocationHandler h)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, IOException {
        //1.生成源代码
        final Class<?>[] intfs = interfaces.clone();
        String proxySrc = generateSrc(intfs[0]);
        //2.将源代码生成java文件
        String path = TomProxy.class.getResource("").getPath();
        File f=new File(path+"$Proxy0.java");
        FileUtils.writeStringToFile(new File(path+"$Proxy0.java"),proxySrc,"UTF-8");
        //3.编译源代码，生成.class文件
        JavaCompiler systemJavaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager standardFileManager = systemJavaCompiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> javaFileObjects = standardFileManager.getJavaFileObjects(f);
        JavaCompiler.CompilationTask task = systemJavaCompiler.getTask(null, standardFileManager, null, null, null, javaFileObjects);
        //4.将class文件动态加载到jvm
        task.call();
        FileUtils.deleteQuietly(f);
        //5.返回代理后的代理对象
        Class<?> c=loader.findClass("$Proxy0");
        Constructor<?> constructor = c.getConstructor(TomInvocationHandler.class);
        System.out.println(constructor);
        return constructor.newInstance(h);
    }

    private static String generateSrc(Class<?> intfs) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("package com.example.demo.proxy.jdk;" + SEP +
                "import com.example.demo.proxy.jdk.Person;" + SEP +
                "import com.example.demo.proxy.jdk.TomInvocationHandler;" + SEP +
                "import java.lang.reflect.Method;" + SEP );
        stringBuffer.append("public final class $Proxy0 implements " + intfs.getName() + " {" + SEP);
        stringBuffer.append("protected TomInvocationHandler h;" + SEP);
        stringBuffer.append("public $Proxy0(TomInvocationHandler h) {" + SEP +
                "        this.h = h;" + SEP +
                "    }" + SEP);
        for (Method m : intfs.getMethods()) {

            stringBuffer.append("public " + m.getReturnType().getName() + " " + m.getName() + "(){" + SEP);
            stringBuffer.append("try {"+ SEP);
            stringBuffer.append("Method  m= " + intfs.getName() + ".class.getMethod(\"" + m.getName() + "\",new Class[]{});" + SEP);
            stringBuffer.append("this.h.invoke(this,m,null);" + SEP);
            stringBuffer.append("} catch (Throwable e) {"+ SEP+
                    "e.printStackTrace();"+ SEP+
                    "}" + SEP);
            stringBuffer.append("}" + SEP);

        }
        stringBuffer.append("}" + SEP);
        return stringBuffer.toString();
    }
}
