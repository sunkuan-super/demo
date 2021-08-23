package com.sk.util.file;


import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * @Title: CsvUtils
 * @Package: com.sk.util.file
 * @Description:
 * @Author: sk
 * @Date: 2021/8/17 - 14:05
 */
public class CsvUtils {
    public static void main(String[] args) {
        try {
            File file = new File("E:\\Cennavi\\2021-08\\04_交通一张图\\national\\national.csv");

            InputStreamReader inReader = new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8"));

            CSVReader read = new CSVReader(inReader);

            String[] arr = null;
            while ((arr = read.readNext()) != null) {
                System.out.println(arr[arr.length - 1]);
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

}
