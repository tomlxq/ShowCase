package com.greentea.multilang.view;

/**
 * User: TOM
 * Date: 2014/12/6
 * email: beauty9235@gmail.com
 * Time: 14:19
 */

import com.lowagie.text.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xhtmlrenderer.pdf.ITextFSImage;
import org.xhtmlrenderer.pdf.ITextOutputDevice;
import org.xhtmlrenderer.pdf.ITextUserAgent;
import org.xhtmlrenderer.resource.CSSResource;
import org.xhtmlrenderer.resource.ImageResource;
import org.xhtmlrenderer.util.XRLog;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;

/**
 * 修改xhtmlrenderer获取css和image资源的方式为网络
 */
@SuppressWarnings("unchecked")
public class HttpURLUserAgent extends ITextUserAgent {
    public final static Logger logger = LoggerFactory.getLogger(HttpURLUserAgent.class);
    HttpServletRequest request;

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * get web root url
     * @return
     * @throws Exception
     */
    public String getBaseUrl() throws Exception {
        if(this.request==null) new  Exception("request is null");
        File file=new File(request.getSession().getServletContext().getRealPath(""));
        return  file.toURI().toURL().toString();
    }

    /**
     * an LRU cache
     */
    private int imageCacheCapacity = 16;
    private LinkedHashMap imageCache =
            new LinkedHashMap(imageCacheCapacity, 0.75f, true) {
                private static final long serialVersionUID = -2333998499957890105L;

                protected boolean removeEldestEntry(java.util.Map.Entry eldest) {
                    return size() > imageCacheCapacity;
                }
            };

    public HttpURLUserAgent(ITextOutputDevice outputDevice) {
        super(outputDevice);
    }
    public HttpURLUserAgent(HttpServletRequest _request,ITextOutputDevice outputDevice) {
        super(outputDevice);
        this.setRequest(_request);
    }
    @Override
    public CSSResource getCSSResource(String uri) {
        InputStream is = null;
        try {
            uri=getBaseUrl()+uri;
            //uri = new File(StringUtils.replace(WEB_ROOT + uri, "/", "\\")).toURI().toURL().toString();
        } catch (Exception e) {
            logger.debug("{}", e);
        }
        uri = resolveURI(uri);
        try {
            URLConnection uc = new URL(uri).openConnection();
            uc.connect();
            is = uc.getInputStream();
        } catch (MalformedURLException e) {
            XRLog.exception("bad URL given: " + uri, e);
        } catch (IOException e) {
            XRLog.exception("IO problem for " + uri, e);
        }
        return new CSSResource(is);
    }

    @Override
    public ImageResource getImageResource(String uri) {
        ImageResource ir;
        try {
            uri=getBaseUrl()+uri;
         //   uri = new File(StringUtils.replace(WEB_ROOT + uri, "/", "\\")).toURI().toURL().toString();
            logger.debug("##################{}", uri);
        } catch (Exception e) {
            logger.debug("##################{}", uri);
            logger.debug("{}", e);
        }
        uri = resolveURI(uri);
        ir = (ImageResource) imageCache.get(uri);
        if (ir == null) {
            try {
                ir = new ImageResource(new ITextFSImage(Image.getInstance(new URL(uri))));
            } catch (Exception e) {
                e.printStackTrace();
            }
            imageCache.put(uri, ir);
        }
        if (ir == null) ir = new ImageResource(null);
        return ir;
    }
}