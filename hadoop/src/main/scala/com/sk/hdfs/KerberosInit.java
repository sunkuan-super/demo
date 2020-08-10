package com.sk.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;

/**
 * @Title: KerberosInit
 * @Package: com.sk.hdfs
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/8/3 - 11:24
 */
public class KerberosInit {
    public static void initKerberos() throws IOException {
        //kerberos权限认证
        String krb5 = "hadoop/file/krb5.conf";
        String principal = "hdfs";
        String keytab = "hadoop/file/hdfs-5g.keytab";
        System.setProperty("java.security.krb5.conf", krb5);
        System.setProperty("javax.security.auth.useSubjectCredsOnly", "false");
        Configuration conf = new Configuration();
        //    conf.addResource(new Path(path + "hbase-site.xml"))
        //    conf.addResource(new Path(path + "hdfs-site.xml"))
        //    conf.addResource(new Path(path + "hive-site.xml"))
        conf.set("hadoop.security.authentication", "Kerberos");
        //    conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem")
        UserGroupInformation.setConfiguration(conf);
        UserGroupInformation.loginUserFromKeytab(principal, keytab);
        System.out.println("login user: " + UserGroupInformation.getLoginUser());
    }

    public static void initKerberosHBase() throws IOException {
        //kerberos权限认证
        String krb5 = "hadoop/file/krb5.conf";
        String principal = "hbase";
        String keytab = "hadoop/file/hbase.keytab";
        System.setProperty("java.security.krb5.conf", krb5);
        System.setProperty("javax.security.auth.useSubjectCredsOnly", "false");
        Configuration conf = new Configuration();
        //    conf.addResource(new Path(path + "hbase-site.xml"))
        //    conf.addResource(new Path(path + "hdfs-site.xml"))
        //    conf.addResource(new Path(path + "hive-site.xml"))
        conf.set("hadoop.security.authentication", "Kerberos");
        //    conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem")
        UserGroupInformation.setConfiguration(conf);
        UserGroupInformation.loginUserFromKeytab(principal, keytab);
        System.out.println("login user: " + UserGroupInformation.getLoginUser());
    }
}

