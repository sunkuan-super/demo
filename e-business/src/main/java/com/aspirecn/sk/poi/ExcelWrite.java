package com.aspirecn.sk.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.joda.time.DateTime;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Title: ExcelWrite
 * @Package: com.aspirecn.sk.poi
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/12 - 10:42
 */
public class ExcelWrite {


    public void testExcelWrite() throws IOException {
        // 创建一个工作簿
        //03版
        // Workbook workbook = new HSSFWorkbook();
        //07版
        // Workbook workbook = new XSSFWorkbook();
        Workbook workbook = new SXSSFWorkbook();

        // 创建一个工作表
        Sheet sheet = workbook.createSheet("成绩表");

        // 创建一个行
        Row row1 = sheet.createRow(0);

        // 创建一个单元格
        Cell cell1 = row1.createCell(0);
        cell1.setCellValue("姓名");
        Cell cell2 = row1.createCell(1);
        cell2.setCellValue("年龄");

        //第二行
        Row row2 = sheet.createRow(1);
        Cell cell3 = row2.createCell(0);
        cell3.setCellValue("统计时间");

        String date = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        Cell cell4 = row2.createCell(1);
        cell4.setCellValue(date);

        //生成一张表（IO流）
        FileOutputStream out = new FileOutputStream("excel/08.xlsx");
        workbook.write(out);

        out.close();
    }

    public void testBigDataExcelWrite() throws IOException {
        Workbook workbook = new SXSSFWorkbook();

        // 创建一个工作表
        Sheet sheet = workbook.createSheet("成绩表");

        for (int i = 0; i < 100000; i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j < 10; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(j);
            }
        }

        FileOutputStream out = new FileOutputStream("excel/07bigdata.xlsx");
        workbook.write(out);
        out.close();

        //清除临时文件
        ((SXSSFWorkbook) workbook).dispose();
    }
}
