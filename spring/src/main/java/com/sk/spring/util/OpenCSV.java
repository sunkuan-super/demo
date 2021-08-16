package com.sk.spring.util;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OpenCSV {
    public static void main(String[] args) throws Exception {
        OpenCSV oc = new OpenCSV();
        oc.CSVReadAll();

        oc.CSVWriter();
    }

    public String dir = System.getProperty("user.dir") + "/testMaven";

    public void CSVReadAll() throws Exception {
        File csv = new File(dir + "/file", "readerTest.csv");

        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(csv), "GBK"), ',');
        String [] header = reader.readNext();   //用readnext读取之后就不存在于stream中了
        for (String s : header) {
            System.out.print(s + ",");
        }
        System.out.println("");

        List<String[]> list = reader.readAll(); //此时读取的已经是第二行了
        System.out.println(list.get(0)[0]);
        System.out.println("");
    }

    public void CSVWriter() throws Exception{
        File csv = new File(dir + "/file", "writerTest.csv");
        if (!csv.exists()) {
            csv.createNewFile();
        }

        List<String[]> list = new ArrayList<>();
        String s1 = "123";
        for (int i = 0; i < 22; i++) {
            String[] ss = {String.valueOf(i),String.valueOf(i),String.valueOf(i),String.valueOf(i)};
            list.add(ss);
        }

        CSVWriter writer = new CSVWriter(new OutputStreamWriter(new FileOutputStream(csv),"GBK"),CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER);
        writer.writeNext(new String[]{s1, s1, s1, s1});
        writer.writeNext(new String[]{"#","#","#","#"});
        writer.writeAll(list);
        writer.flush();
        writer.close();
    }
}
