package com.greentea.multilang.view;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/1/5  20:00
 */
public class ExcelView extends AbstractExcelView {
    protected void buildExcelDocument(Map<String, Object> model,
                                      HSSFWorkbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        HSSFSheet sheet;
        HSSFCell cell;
        sheet = workbook.createSheet("Spring");
        sheet.setDefaultColumnWidth((short) 12);
        // write a text at A1
        cell = getCell(sheet, 0, 0);
        setText(cell, "Spring-Excel 测试");
    }
}
