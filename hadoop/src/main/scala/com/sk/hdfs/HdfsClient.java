package com.sk.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

/**
 * @Title: com.sk.hdfs.HdfsClient
 * @Package: PACKAGE_NAME
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/8/3 - 11:11
 */
public class HdfsClient {
    public static void main(String[] args) throws IOException {
        KerberosInit.initKerberos();
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS","hdfs://sklocal:8020");

        FileSystem fs = FileSystem.get(configuration);
        FileStatus[] fileStatuses = fs.listStatus(new Path("/"));
        for (FileStatus fileStatus : fileStatuses) {
            System.out.println(fileStatus.getPath().getName());
        }

        fs.close();
    }
}
