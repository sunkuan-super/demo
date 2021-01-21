package com.sk.util.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExportSmsDetailPo {

    /**
     * 发送时间
     */
    private String createTime;

    /**
     * 发送方式
     */
    private String sendWay;

    /**
     * 手机号
     */
    private String mobile;

    /**
     *  短信内容
     */
        private String smsContent;


}
