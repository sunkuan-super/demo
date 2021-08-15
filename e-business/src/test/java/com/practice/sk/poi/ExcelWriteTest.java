package com.practice.sk.poi;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;

/**
 * @Title: ExcelWriteTest
 * @Package: com.practice.sk.poi
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/12 - 11:22
 */
public class ExcelWriteTest extends TestCase {


    @Test
    public void testTestExcelWrite() throws IOException {
        ExcelWrite excelWrite = new ExcelWrite();
//        excelWrite.testExcelWrite();
        excelWrite.testBigDataExcelWrite();
    }
}