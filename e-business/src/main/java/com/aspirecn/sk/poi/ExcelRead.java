package com.aspirecn.sk.poi;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

/**
 * @Title: ExcelRead
 * @Package: com.aspirecn.sk.poi
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/12 - 11:59
 */
public class ExcelRead {

    public void testExcelRead() throws IOException {

        FileInputStream fileInputStream = new FileInputStream("excel/07.xlsx");
        Workbook workbook = new XSSFWorkbook(fileInputStream);

        // 获取工作簿 sheet个数
        System.out.println("======== sheet个数 =========");
        System.out.println(workbook.getNumberOfSheets());

        Sheet sheet = workbook.getSheet("成绩表");

        //获取 sheet的行数
        System.out.println("=========== 行数 ===========");
        System.out.println(sheet.getPhysicalNumberOfRows());

        Row row = sheet.getRow(0);

        //获取每行的单元格个数
        System.out.println("============列数============");
        System.out.println(row.getPhysicalNumberOfCells());

        System.out.println(row.getCell(0).getStringCellValue());
        System.out.println(row.getCell(1).getStringCellValue());

        fileInputStream.close();


    }

    public void testExcelRead2() throws IOException {
        // workbook
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\sunkuan\\Desktop\\Aspire\\项目\\5G端到端天馈智能优化\\广东\\深圳\\5G公参 广东Massive MIMO权值优化项目地市需求文档V2 - 副本.xlsx");
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        // 读取一个sheet
        Sheet sheet = workbook.getSheetAt(0);

        Row row = sheet.getRow(0);
        if (row == null) {
            return;
        }
        for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
            Cell cell = row.getCell(i);
            if (cell == null) {
                continue;
            }

            System.out.print(cell.getStringCellValue() + "\t");
        }

        System.out.println();

        for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
            Row row1 = sheet.getRow(j);
            if (row1 == null) {
                continue;
            }

            for (int k = 0; k < row1.getPhysicalNumberOfCells(); k++) {
                Cell cell = row1.getCell(k);
                if (cell == null) {
                    continue;
                }
                int cellType = cell.getCellType();
                String cellTypeStr = "";
                switch (cellType) {
                    case Cell
                            .CELL_TYPE_STRING:
//                        System.out.println("【字符串】");
                        cellTypeStr = cell.getStringCellValue();
                        break;
                    case XSSFCell.CELL_TYPE_BOOLEAN:
                        //System.out.println("【布尔】");
                        cellTypeStr = String.valueOf(cell.getBooleanCellValue());
                        break;
                    case Cell.CELL_TYPE_BLANK:
                        //System.out.println("【空】");
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
//                        System.out.println("【数字类型】");
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            //System.out.println("【日期】");
                            Date date = cell.getDateCellValue();
                            cellTypeStr = new DateTime(date).toString("yyyy-MM-dd HH:mm:ss");
                        } else {
                            //System.out.println("【普通数字】");
                            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                            cellTypeStr = cell.toString();
                        }
                        break;
                    case Cell.CELL_TYPE_ERROR:
                        //System.out.println("【类型错误】");
                        break;
                    default:
                }
                // System.out.println("行： " + j + "列：" + k + " " + cellTypeStr);
                System.out.print(cellTypeStr + "\t");
            }
            System.out.println();
        }

    }

    public void testExcelReadFunction() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\sunkuan\\Desktop\\求和.xlsx");
        Workbook workbook = new XSSFWorkbook(fileInputStream);

        Sheet sheet = workbook.getSheetAt(0);

        Row row = sheet.getRow(4);
        Cell cell = row.getCell(0);

        //拿到计算公式
        FormulaEvaluator formulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);

        int cellType = cell.getCellType();
        switch (cellType){
            case Cell.CELL_TYPE_FORMULA:
//                String formula = cell.getCellFormula();
//                System.out.println("计算公式： " + formula);

                CellValue evaluator = formulaEvaluator.evaluate(cell);
                String cellValue = evaluator.formatAsString();
                System.out.println(cellValue);
                break;
        }

    }
}
