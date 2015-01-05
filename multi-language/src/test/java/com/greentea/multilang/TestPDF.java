package com.greentea.multilang;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/1/5  16:34
 */
public class TestPDF extends TestBase {

    public void createPdf() throws Exception {
        //logger.debug("################{}", TestPDF.class.getClassLoader().getResource(""));
        // step 1
        String inputFile = "F:\\data\\wwwroot\\ShowCase\\multi-language\\src\\main\\webapp\\WEB-INF\\jsp\\pdf1.html";
        //String outputFile = "F:\\data\\wwwroot\\ShowCase\\multi-language\\src\\main\\webapp\\WEB-INF\\jsp\\pdf1.pdf";
        creatgePDF(inputFile);
        inputFile = "F:\\data\\wwwroot\\ShowCase\\multi-language\\src\\main\\webapp\\WEB-INF\\jsp\\pdf2.html";
        //String outputFile = "F:\\data\\wwwroot\\ShowCase\\multi-language\\src\\main\\webapp\\WEB-INF\\jsp\\pdf1.pdf";
        creatgePDF(inputFile);
    }

    private void creatgePDF(String inputFile) throws DocumentException, IOException {
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

    @Test
    public void testCreatePDF() throws Exception {
        createPdf();
    }
}
