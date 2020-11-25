package com.practice.sk.poi;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;

/**
 * @Title: ExcelReadTest
 * @Package: com.aspirecn.sk.poi
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/12 - 13:33
 */
public class ExcelReadTest extends TestCase {

    @Test
    public void testTestExcelRead() throws IOException {
        ExcelRead excelRead = new ExcelRead();
//        excelRead.testExcelRead2();
        excelRead.testExcelReadFunction();
    }
}