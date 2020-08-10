package com.sk.hbase;

import com.sk.hdfs.KerberosInit;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;

/**
 * @Title: AAA
 * @Package: com.sk.hbase
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/8/10 - 17:43
 */
public class AAA {
    public static Connection getConn() throws IOException {
        KerberosInit.initKerberos();
        // 获取配置、创建连接
        Configuration configuration = HBaseConfiguration.create();
        Connection conn = ConnectionFactory.createConnection(configuration);
        return conn;
    }

    public static void main(String[] args) throws IOException {
        String tableName = "sms2019:test";
        Connection conn = getConn();

        Table table = conn.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();
        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
            System.out.println(result);
        }

    }

}
