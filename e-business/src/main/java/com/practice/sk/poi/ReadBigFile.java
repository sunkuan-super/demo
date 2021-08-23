package com.practice.sk.poi;


import com.alibaba.excel.util.StringUtils;
import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.*;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title: ReadBigFile
 * @Package: com.practice.sk.poi
 * @Description:
 * @Author: sk
 * @Date: 2021/8/13 - 14:01
 */
public class ReadBigFile {

    public static Object testExcelBigFileTitle() throws IOException {

        long start = System.currentTimeMillis();
        System.out.println("start :" + System.currentTimeMillis());
        Workbook workbook = StreamingReader.builder().rowCacheSize(10).bufferSize(1024).open(new File("E:\\Cennavi\\2021-08\\04_交通一张图\\mileage.xlsx"));

        if (workbook == null) {
            return null;
        }

        // 2、工作表 sheet
        Sheet sheet = workbook.getSheetAt(0);
        if (sheet == null) {
            return null;
        }

        List<String> headerList = new ArrayList<>();

        int rowIndex = 0;
        for (Row row : sheet) {
            if (rowIndex == 0) {
                rowIndex ++;
                continue;
            }

            // 4、单元格 Cell
            int cellNum = row.getLastCellNum();
            for (int k = 0; k < cellNum; k++) {
                Cell cell = row.getCell(k);
                String value = getCellStringVal(cell);
                headerList.add(value.trim());
            }
            rowIndex++;
        }

        System.out.println("total-time:" + (System.currentTimeMillis() - start));
        System.out.println("rowIndex: " + rowIndex);



        return null;
    }

    public static Object testExcelBigFile() throws IOException {

        long start = System.currentTimeMillis();
        System.out.println("start :" + System.currentTimeMillis());
        Workbook workbook = StreamingReader.builder().rowCacheSize(10).bufferSize(1024).open(new File("E:\\Cennavi\\2021-08\\04_交通一张图\\national\\national.xlsx"));

        if (workbook == null) {
            return null;
        }

        // 2、工作表 sheet
        Sheet sheet = workbook.getSheetAt(0);
        if (sheet == null) {
            return null;
        }

        List<String> headerList = new ArrayList<>();

        for (Row row : sheet) {

            // 4、单元格 Cell
            int cellNum = row.getLastCellNum();
            for (int k = 0; k < cellNum; k++) {
                Cell cell = row.getCell(k);
                String value = getCellStringVal(cell);
                headerList.add(value.trim());
            }
        }

        System.out.println("total-time:" + (System.currentTimeMillis() - start));

        for (int i = 10; i < 22; i++) {
            System.out.println(headerList.get(i));
        }

        return null;
    }

    public static String getCellStringVal(Cell cell) {
        if (cell == null) {
            return "";
        }

        CellType cellType = cell.getCellTypeEnum();
        switch (cellType) {
            case NUMERIC:
                return cell.getStringCellValue();
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                // 公式
                return cell.getCellFormula();
            case ERROR:
                return String.valueOf(cell.getErrorCellValue());
            case BLANK:
            default:
                return StringUtils.EMPTY;
        }
    }

    public static void main(String[] args) throws IOException {
        testExcelBigFile();
    }
}
