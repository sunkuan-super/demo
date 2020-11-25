package com.practice.sk.poi;

import com.alibaba.excel.EasyExcel;
import com.practice.sk.poi.bean.DemoData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Title: EasyExcelDemo
 * @Package: com.practice.sk.poi
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/14 - 0:52
 */
public class EasyExcelDemo {

    List<DemoData> getList() {
        List<DemoData> demoDataList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            DemoData demoData = new DemoData();
            demoData.setString("标题" + i);
            demoData.setDate(new Date());
            demoData.setDoubleData((double) i);
            demoDataList.add(demoData);
        }
        return demoDataList;
    }

    public static void main(String[] args) {
        String fileName = "easyexcel.xlsx";
        EasyExcel.write(fileName,DemoData.class).sheet("模板").doWrite(new EasyExcelDemo().getList());
    }
}
