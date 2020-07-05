package com.aspirecn.sk.poi.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Title: DemoData
 * @Package: com.aspirecn.sk.poi.bean
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/14 - 0:54
 */
@Data
public class DemoData {
    @ExcelProperty("字符串标题")
    private String string;
    @ExcelProperty("日期标题")
    private Date date;
    @ExcelProperty("数字标题")
    private Double doubleData;

    //忽略这个字段
    @ExcelIgnore
    private String ignore;
}
