package com.greentea.multilang.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/1/5  9:35
 */
public class SystemLanguageFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(SystemLanguageFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.debug("DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE {}",DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE);
        request.setAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE, SpringContextHolder.getBean(org.springframework.web.servlet.i18n.CookieLocaleResolver.class));
        Locale locale = RequestContextUtils.getLocale((HttpServletRequest) request);
        request.setAttribute("easeyeI18nLang", locale.getLanguage());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }


}
