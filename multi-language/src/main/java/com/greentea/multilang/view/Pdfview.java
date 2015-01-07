package com.greentea.multilang.view;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/1/5  19:16
 */
public class PdfView  extends AbstractPdfView {
    private static final Logger logger = LoggerFactory.getLogger(PdfView.class);

    private static final Font getChineseFont(float size) {
        Font FontChinese = null;
        try {
           // BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
            //微软雅黑字体文件
            //fontResolver.addFont("C:/Windows/fonts/msyh.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            //微软雅黑加粗后额字体文件
            //fontResolver.addFont("C:/Windows/fonts/msyhbd.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            BaseFont bfHei = BaseFont.createFont("c:\\Windows\\Fonts\\SIMHEI.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            Font font = new Font(bfHei, 32);
            String text = "这是黑体字测试！";
           // BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            FontChinese = new Font(bfHei, size, Font.NORMAL);
        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        return FontChinese;
    }

    @Override
    protected void buildPdfDocument(Map<String, Object> map, Document document, PdfWriter pdfWriter, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        logger.debug("test sys are create pdf.....");
        Paragraph header = new Paragraph(new Chunk("PDF 输出测试",
                getChineseFont(24)));
        document.add(header);
        document.add(new Paragraph("测试",getChineseFont(12)));
    }
}
