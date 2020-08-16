package com.sk

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.security.UserGroupInformation

/**
 * @Title: Utils
 * @Package: com.sk
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/7/27 - 13:27
 */
object Utils {
  def initKerberos(): Unit = {
    //kerberos权限认证
    val krb5 = "spark/file/krb5.conf"
    val principal = "hdfs"
    val keytab = "spark/file/hdfs-5g.keytab"
    System.setProperty("java.security.krb5.conf", krb5)
    System.setProperty("javax.security.auth.useSubjectCredsOnly", "false")
    val conf = new Configuration
    //    conf.addResource(new Path(path + "hbase-site.xml"))
    //    conf.addResource(new Path(path + "hdfs-site.xml"))
    //    conf.addResource(new Path(path + "hive-site.xml"))
        conf.set("hadoop.security.authentication", "Kerberos")
    //    conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem")
    UserGroupInformation.setConfiguration(conf)
    UserGroupInformation.loginUserFromKeytab(principal, keytab)
    println("login user: " + UserGroupInformation.getLoginUser())
  }


}
