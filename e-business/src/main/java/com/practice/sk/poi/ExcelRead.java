package com.practice.sk.poi;

import com.alibaba.excel.util.StringUtils;
import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Title: ExcelRead
 * @Package: com.practice.sk.poi
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
        System.out.println("-------------------------------");
        for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
            Row row1 = sheet.getRow(i);
            int physicalNumberOfCells = row1.getPhysicalNumberOfCells();
            for (int i1 = 0; i1 < physicalNumberOfCells; i1++) {
                Cell cell = row1.getCell(i1);

                int cellType = cell.getCellType();
                if()
                cell.setCellType(CellType.STRING);
                String stringCellValue = cell.getStringCellValue();
                System.out.println(stringCellValue);
            }
        }

        fileInputStream.close();


    }

    public void testExcelRead2() throws IOException {
        // workbook
        FileInputStream fileInputStream = new FileInputStream("C:\\test.xlsx");
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
        switch (cellType) {
            case Cell.CELL_TYPE_FORMULA:
//                String formula = cell.getCellFormula();
//                System.out.println("计算公式： " + formula);

                CellValue evaluator = formulaEvaluator.evaluate(cell);
                String cellValue = evaluator.formatAsString();
                System.out.println(cellValue);
                break;
        }


    }

    public Object testExcelBigFile() throws IOException {
        long start = System.currentTimeMillis();
        System.out.println("start :" + start);

        // 1、获取工作簿 Workbook
        Workbook workbook = StreamingReader.builder().rowCacheSize(10).bufferSize(1024).open(new File("E:\\Cennavi\\2021-08\\04_交通一张图\\mileage.xlsx"));
//        Workbook workbook = new HSSFWorkbook(new FileInputStream("E:\\Cennavi\\2021-04\\省厅交通一张图\\甘肃公路水路地理信息系统18个图层数据 4.xls"));


        if (workbook == null) {
            return null;
        }

        // 2、工作表 sheet
        Sheet sheet = workbook.getSheetAt(0);
        if (sheet == null) {
            return null;
        }

        // 3、行 Row
//        Row row = sheet.getRow(0);
//        if (row == null) {
//            return null;
//        }
        List<String> headerList = new ArrayList<>();

        int rowIndex = 0;
        for (Row row : sheet) {
            if (rowIndex > 0) {
                break;
            }
            // 4、单元格 Cell
            int cellNum = row.getLastCellNum();
//            System.out.println("cellNum:" + cellNum);
            for (int k = 0; k < cellNum; k++) {
                Cell cell = row.getCell(k);
                String value = getCellStringVal(cell);
                headerList.add(value.trim());
            }
            rowIndex++;
        }


        long end = System.currentTimeMillis();

        System.out.println("total-time:" + (end - start));
        System.out.println("rowIndex: " + rowIndex);
        return null;
    }

    public static String getCellStringVal(Cell cell) {
        if (cell == null) {
            return "";
        }

        CellType cellType = cell.getCellTypeEnum();
        switch (cellType) {
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
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

    public void processOneSheet(String filename) throws Exception {
        OPCPackage pkg = OPCPackage.open(filename);
        XSSFReader r = new XSSFReader(pkg);
        SharedStringsTable sst = r.getSharedStringsTable();

        XMLReader parser = fetchSheetParser(sst);

        // 获得第一个sheet
        InputStream sheet2 = r.getSheet("rId1");
        InputSource sheetSource = new InputSource(sheet2);
        parser.parse(sheetSource);
        sheet2.close();
    }

    public XMLReader fetchSheetParser(SharedStringsTable sst) throws SAXException {
        XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
        ContentHandler handler = new SheetHandler(sst);
        parser.setContentHandler(handler);
        return parser;
    }

    /**
     * 处理sax的handler
     */
    private static class SheetHandler extends DefaultHandler {
        private SharedStringsTable sst;
        private String lastContents;
        private boolean nextIsString;

        private SheetHandler(SharedStringsTable sst) {
            this.sst = sst;
        }

        //元素开始时的handler
        public void startElement(String uri, String localName, String name,
                                 Attributes attributes) throws SAXException {
            // c => 单元格
            if (name.equals("c")) {
                System.out.print(attributes.getValue("r") + " - ");
                // 获取单元格类型
                String cellType = attributes.getValue("t");
                if (cellType != null && cellType.equals("s")) {
                    nextIsString = true;
                } else {
                    nextIsString = false;
                }
            }
            lastContents = "";
        }

        //元素结束时的handler
        public void endElement(String uri, String localName, String name)
                throws SAXException {
            if (nextIsString) {
                int idx = Integer.parseInt(lastContents);
                lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
                nextIsString = false;
            }

            // v => 单元格内容
            if (name.equals("v")) {
                System.out.println(lastContents);
            }
        }

        //读取元素间内容时的handler
        public void characters(char[] ch, int start, int length)
                throws SAXException {
            lastContents += new String(ch, start, length);
        }
    }

    public void processFileJDK8() throws IOException {
        Stream<String> lines = Files.lines(Paths.get("E:\\Cennavi\\2021-08\\04_交通一张图\\national\\national.csv"), Charset.forName("UTF-8"));
        lines.forEach(System.out::println);
    }

    public static void main(String[] args) throws Exception {
        ExcelRead example = new ExcelRead();
        example.processFileJDK8();
        System.out.println("开始=" + new Date());
        example.processOneSheet("E:\\Cennavi\\2021-08\\04_交通一张图\\national\\national.xlsx");
        System.out.println("结束=" + new Date());
    }
}
