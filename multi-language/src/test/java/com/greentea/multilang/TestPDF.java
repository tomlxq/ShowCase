package com.greentea.multilang;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/1/5  16:34
 */
public class TestPDF extends TestBase {
    @Test
    public void createPdf() throws Exception {
        //logger.debug("################{}", TestPDF.class.getClassLoader().getResource(""));
        // step 1
        String inputFile = "F:\\data\\wwwroot\\ShowCase\\multi-language\\src\\main\\webapp\\WEB-INF\\jsp\\pdf1.html";
        //String outputFile = "F:\\data\\wwwroot\\ShowCase\\multi-language\\src\\main\\webapp\\WEB-INF\\jsp\\pdf1.pdf";
        createPDF(inputFile);
        inputFile = "F:\\data\\wwwroot\\ShowCase\\multi-language\\src\\main\\webapp\\WEB-INF\\jsp\\pdf2.html";
        //String outputFile = "F:\\data\\wwwroot\\ShowCase\\multi-language\\src\\main\\webapp\\WEB-INF\\jsp\\pdf1.pdf";
        createPDF(inputFile);
    }

    private void createPDF(String inputFile) throws DocumentException, IOException {
        File file=new File(inputFile);
       String dir="F:\\data\\wwwroot\\ShowCase\\multi-language\\target\\"+ FilenameUtils.getBaseName(file.getPath())+".pdf";
       // String outputFile = StringUtils.replaceOnce(file.getPath(),"."+ FilenameUtils.getExtension(inputFile),".pdf");
        String url = new File(inputFile).toURI().toURL().toString();

        System.out.println(url);
        // step 2
        OutputStream os = new FileOutputStream(dir);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(url);

        // step 3 解决中文支持
        org.xhtmlrenderer.pdf.ITextFontResolver fontResolver = renderer
                .getFontResolver();
        fontResolver.addFont("c:/Windows/Fonts/simsun.ttc", BaseFont.IDENTITY_H,
                BaseFont.NOT_EMBEDDED);

        renderer.layout();
        renderer.createPDF(os);
        os.close();

        System.out.println("create pdf done!!");
    }

    /**
     * 1. 无法识别很多HTML的tag和attribute(应该是iText的HTMLParser不够强大)
     * 2. 无法识别CSS
     * @throws Exception
     */
    @Test
    public void testCreatePDF2() throws Exception {
        Document document = new Document();
        StyleSheet st = new StyleSheet();
        st.loadTagStyle("body", "leading", "16,0");
        PdfWriter.getInstance(document, new FileOutputStream("html2.pdf"));
        document.open();
        ArrayList p = HTMLWorker.parseToList(new FileReader("example.html"), st);
        for (int k = 0; k < p.size(); ++k)
            document.add((Element) p.get(k));
        document.close();
    }

    @Test
    public void testCreatePDF3() throws Exception {
        String inputFile = "conf/template/test.html";
        String url = new File(inputFile).toURI().toURL().toString();
        String outputFile = "firstdoc.pdf";
        OutputStream os = new FileOutputStream(outputFile);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(url);

        // 解决中文支持问题
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont("C:/Windows/Fonts/arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

        // 解决图片的相对路径问题
        renderer.getSharedContext().setBaseURL("file:/D:/Work/Demo2do/Yoda/branch/Yoda%20-%20All/conf/template/");

        renderer.layout();
        renderer.createPDF(os);

        os.close();

    }

    @Test
    public void testCreatePDF4() throws Exception {
     /*   URL url=PdfAndExcelController.class.getResource("/");
       logger.debug("{}",url);
        File file=new File(url.toURI());
        logger.debug("{}",file.getPath());*/
        mockMvc.perform((get("/viewPDF"))).andExpect(status().isOk()).andDo(print());

    }



}
