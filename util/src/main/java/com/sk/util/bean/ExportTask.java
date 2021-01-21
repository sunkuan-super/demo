package com.sk.util.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: ExportTask
 * @Package: com.sk.util.bean
 * @Description:
 * @Author: sunkuan
 * @Date: 2021/1/21 - 16:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExportTask {

    /**
     * 导出任务id
     */
    private Long seqNo;

    /**
     * 时间范围
     */
    private String dateScope;

    /**
     * 当前处理的时间
     */
    private String currentDate;

    /**
     * 结束时间
     */
    private String endDate;

    /**
     * 写到第几个文件
     */
    private Integer numOfFile;

    @Override
    public String toString() {
        return "ExportTask{" +
                "seqNo=" + seqNo +
                ", dateScope='" + dateScope + '\'' +
                ", currentDate='" + currentDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", numOfFile=" + numOfFile +
                '}';
    }


}
