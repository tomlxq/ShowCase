package com.greentea.multilang.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;


/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/1/5  8:49
 */
@Controller
public class HelloWorldController {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @RequestMapping("/hello")
    public ModelAndView helloWorld() {
        String message = "Hello World, Spring 3.0!";
        return new ModelAndView("hello", "message", message);
    }


    /*@RequestMapping(value = "/changelanguage")
    public String changeLanguage(HttpServletRequest request, HttpServletResponse response) {
        String newLocale = request.getParameter("language");
        if (newLocale != null) {
            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
            if (localeResolver == null) {
                throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
            }
            localeResolver.setLocale(request, response, StringUtils.parseLocaleString(newLocale));
        }
        return "redirect:" + request.getParameter("originalUrl");
    }*/

    /* HttpServletRequest支持一些接口来获得请求客户端浏览器的地区、语言和国家
        （IE-Internet Options设置-General-Language可以设置你的浏览器语言），
        然后HttpServletResponse通过设置http头的"Content-Language"来动态设置客户端语言。*/
    @RequestMapping(value = "/displaySpanish")
    public String displaySpanish(HttpServletRequest request, HttpServletResponse response) throws IOException {

//Get the client's Locale
        Locale locale = request.getLocale();
        String language = locale.getLanguage();
        String country = locale.getCountry();

        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        // Set spanish language code.
        response.setHeader("Content-Language", "es");

        String title = "En Espa&ntilde;ol";
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " +
                        "transitional//en\">\n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1>" + "En Espa&ntilde;ol:" + "</h1>\n" +
                "<h1>" + "&iexcl;Hola Mundo!" + "</h1>\n" +
                "</body></html>");
        return null;
    }
    /*Spring MVC+Freemarker+jQuery/js的多语言实现
1.根据客户端浏览器语言显示相应的语言
2.用户可以动态切换语言，保存在Cookie中，下次自动使用该语言
3.静态html、jsp页面多语言、controller里面能获得多语言、freemarker多语言、jquery/js的多语言

1.用param的方式：url?lang=de，就是URL的方式，然后用拦截器LocaleChangeInterceptor: 来实现动态多语言。 本文没有采用这种方式，不喜欢改变URL，一切应该是在背后默默进行的。
2.用Session方式来存储用户动态选择的语言：session过期就没有了，所以本文没有采用这种方式。
3.用Cookie的方式来存储用户动态选择的语言：本文采用这种方式，session过期了下次登录还是能记住，但同一机器多个用户需要区分。
    */
    @RequestMapping(value = "/lang")
    public String displayLang(HttpServletRequest request, HttpServletResponse response){
        return "lang";
    }
    @RequestMapping(value = "/changelanguage", method = RequestMethod.POST)
    public ModelAndView changeLanguage( HttpServletRequest request,HttpServletResponse response,@RequestParam String lang) {
        String msg = "";
        logger.debug("#######################{}",lang);
        try {
            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
            if (localeResolver == null) {
                throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
            }

            LocaleEditor localeEditor = new LocaleEditor();
            localeEditor.setAsText(lang);
            localeResolver.setLocale(request, response, (Locale) localeEditor.getValue());

            msg = "Change Language Success!";
        } catch (Exception ex) {
            msg = "error";
        }
        return new ModelAndView("msg", "json", msg);
    }
}
