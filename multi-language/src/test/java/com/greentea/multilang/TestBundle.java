package com.greentea.multilang;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/1/5  10:29
 */
public class TestBundle extends TestBase {
    private static final Logger logger = LoggerFactory.getLogger(TestBundle.class);

    @Test
    public void testLang() {
        //JSP和JSTL标签底层都是通过Java的ResourceBundle类来获取资源并设置参数的
        String myName;
        ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.ENGLISH);
        myName = bundle.getString("contact.mytestkey").trim();

        System.out.println("myName will use the default value: " + myName);
    }

    /*对于java web程序中Servlet访问资源文件可以通过ServletContext类中getResourceAsStream方法，
    它是通过Servlet容器来获得资源文件的，它使得Servlet程序可以访问web应用程序内部的任意位置的文件。
    （非Servlet中用classLoader，jdk中ClassLoader类专门提供了getResource等方法去装载资源文件，他们使用与查找Java类文件同样的方式去查找原文件，
    即在类 装载器所搜索的目录中查找。为了防止外部使用浏览器访问到资源文件，web应用程序中的资源文件通常应放到Web-INF目录或其子目录中。
    由于web应 用程序的类装载器会搜索web-inf/classes目录，所有ClassLoader.getResourceAsStream方法也可以访问该目录中的资源文件，
    但是，该方法不能访问web应用程序内的其他目录中的资源。）*/
    @Test
    public void testLang2() throws IOException {

            /*总结一下，可能只是两种写法

            第一：前面有 “   / ”

            “ / ”代表了工程的根目录，例如工程名叫做myproject，“ / ”代表了myproject

            me.class.getResourceAsStream("/com/x/file/myfile.xml");

            第二：前面没有 “   / ”

            代表当前类的目录

            me.class.getResourceAsStream("myfile.xml");

            me.class.getResourceAsStream("file/myfile.xml");
            */
        InputStream in = TestBundle.class.getResourceAsStream("/messages_en.properties"); // servletContext.getResourceAsStream("/messages_en.properties");
        java.util.Properties properties = new java.util.Properties();
        properties.load(in); //得到的是map集合
        String mytestkey = properties.getProperty("contact.mytestkey");
        System.out.println("myName will use the default value: " + mytestkey);
    }

}
