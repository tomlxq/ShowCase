package com.greentea.multilang.view;

/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/1/5  20:43
 */
import org.apache.poi.hssf.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
/**
 * 生成excel视图，可用excel工具打开或者保存
 * 由ViewController的return new ModelAndView(viewExcel, model)生成
 * @author Tony Lin Created on 2008-10-22
 * @version Version 1.0
 */
public class ViewExcel extends AbstractExcelView {

    public void buildExcelDocument(Map model, HSSFWorkbook workbook,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HSSFSheet sheet = workbook.createSheet("list");
        sheet.setDefaultColumnWidth((short) 12);

        HSSFCell cell = getCell(sheet, 0, 0);
        setText(cell, "Spring Excel test");

        HSSFCellStyle dateStyle = workbook.createCellStyle();
        //dateStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("mm/dd/yyyy"));
        cell = getCell(sheet, 1, 0);
        cell.setCellValue("日期：2008-10-23");
        //cell.setCellStyle(dateStyle);
        getCell(sheet, 2, 0).setCellValue("测试1");
        getCell(sheet, 2, 1).setCellValue("测试2");

        HSSFRow sheetRow = sheet.createRow(3);
        for (short i = 0; i < 10; i++) {
            sheetRow.createCell(i).setCellValue(i * 10);
        }

    }
}
