//package com.sk.util.fileutil;
//
//import com.opencsv.CSVWriter;
//import com.opencsv.bean.ColumnPositionMappingStrategy;
//import com.opencsv.bean.StatefulBeanToCsv;
//import com.opencsv.bean.StatefulBeanToCsvBuilder;
//import com.opencsv.exceptions.CsvDataTypeMismatchException;
//import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
//import com.sk.util.bean.ExportSmsDetailPo;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.*;
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipOutputStream;
//
///**
// * @Title: WriteCSV
// * @Package: com.sk.util.fileutil
// * @Description:
// * @Author: sunkuan
// * @Date: 2021/1/21 - 11:12
// */
//public class WriteCSV {
//    private static final Logger logger = LoggerFactory.getLogger(WriteCSV.class);
//
//    public void writeCSV2(List<ExportSmsDetailPo> dataList, String filePath) {
//        try {
//            Writer writer = new OutputStreamWriter(new FileOutputStream(filePath), Charset.forName("GBK"));
//
//            // 设置显示的顺序
//            String[] columnMapping = {"createTime", "sendWay", "mobile", "smsContent"};
//            ColumnPositionMappingStrategy<ExportSmsDetailPo> mapper =
//                    new ColumnPositionMappingStrategy<>();
//            mapper.setType(ExportSmsDetailPo.class);
//            mapper.setColumnMapping(columnMapping);
//
//            // 写表头
//            CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
//            String[] header = {"短信发送时间", "发送方式", "手机号码", "短信内容"};
//            csvWriter.writeNext(header);
//
//            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
//                    .withMappingStrategy(mapper)
//                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
//                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
//                    .withEscapechar('\\').build();
//            beanToCsv.write(dataList);
//            csvWriter.close();
//            writer.close();
//        } catch (IOException e) {
//            logger.error(e.getMessage());
//        } catch (CsvDataTypeMismatchException e) {
//            logger.error(e.getMessage());
//        } catch (CsvRequiredFieldEmptyException e) {
//            logger.error(e.getMessage());
//        }
//        System.out.println(filePath + "数据导出成功");
//    }
//
//    public void zipFile(String filePath,String outFilePath) throws IOException {
//        InputStream in = new FileInputStream(filePath);
//        OutputStream out = new FileOutputStream(outFilePath);
//
//        ZipOutputStream zipOutputStream = new ZipOutputStream(out);
//        ZipEntry entry = new ZipEntry(filePath);
//        zipOutputStream.putNextEntry(entry);
//
//        byte[] buffer = new byte[1024 * 4];
//        int n = 0;
//        while ((n = in.read(buffer, 0, 1024 * 4)) != -1) {
//            zipOutputStream.write(buffer, 0, n);
//        }
//
//        in.close();
//        zipOutputStream.closeEntry();
//
//        zipOutputStream.flush();
//        zipOutputStream.close();
//    }
//
//    public static void main(String[] args) throws IOException {
//        List<ExportSmsDetailPo> exportSmsDetailPos = new ArrayList<>();
//        ExportSmsDetailPo exportSmsDetailPo = new ExportSmsDetailPo();
//        exportSmsDetailPo.setCreateTime("2020-10-20");
//        exportSmsDetailPo.setMobile("13810837084");
//        exportSmsDetailPo.setSendWay("cms");
//        exportSmsDetailPo.setSmsContent("手机已欠费");
//        System.out.println(exportSmsDetailPo);
//
//        exportSmsDetailPos.add(exportSmsDetailPo);
//
//        WriteCSV writer = new WriteCSV();
//        String filePath2 = "D:/bbb.csv";
//        writer.writeCSV2(exportSmsDetailPos, filePath2);
//
//        File file = new File(filePath2);
//        System.out.println(file.getParentFile().getAbsolutePath());
//
////        writer.zipFile(filePath2,"d://工行.zip");
//    }
//
//
//}
