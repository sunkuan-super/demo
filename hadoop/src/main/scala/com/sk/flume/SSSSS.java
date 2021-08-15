package com.sk.flume;

/**
 * @Title: SSSSS
 * @Package: com.sk.flume
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/9/19 - 23:58
 */
public class SSSSS {
    public static void main(String[] args) {
        String str = "DIAMETER_ERROR_UNKNOWN_EPS_SUBSCRIPTION (5420), without Error Diagnostic, or with Error Diagnostic of GPRS_DATA_SUBSCRIBED      No suitable cells in tracking area             不携带";
        String[] split = str.split("\\s{2,}");
        System.out.println(split[1]);
    }
}
