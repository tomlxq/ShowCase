package com.greentea.multilang.view;

import com.greentea.multilang.pojo.Person;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/1/5  19:37
 */
public class PdfView2 extends AbstractPdfView {

    protected void buildPdfDocument(
            Map model,
            Document doc,
            PdfWriter writer,
            HttpServletRequest req,
            HttpServletResponse resp)
            throws Exception {


        Person person = (Person) model.get("person");
        doc.add(new Paragraph(person.toString()));

    }
}
