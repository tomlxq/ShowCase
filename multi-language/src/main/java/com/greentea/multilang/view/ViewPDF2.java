package com.greentea.multilang.view;

/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/1/5  20:44
 */
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
/**
 * 生成PDF视图，可用PDF浏览器打开或者保存
 * 由ViewController的return new ModelAndView(viewPDF, model)生成
 * @author Tony Lin Created on 2008-10-22
 * @version Version 1.0
 */
public class ViewPDF2 extends AbstractPdfView {
    public void buildPdfDocument(Map model, Document document,
                                 PdfWriter writer, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        List list = (List) model.get("list");

        for (int i = 0; i < list.size(); i++)
            document.add(new Paragraph((String) list.get(i)));
    }
}