package com.sk.sparksql.datasource

import java.sql.DriverManager

import com.sk.Utils

/**
 * @Title: HiveJDBC
 * @Package: com.sk.sparksql.datasource
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/7/28 - 13:05
 */
object HiveJDBC {
  def main(args: Array[String]): Unit = {
    Utils.initKerberos()
    var driver = "org.apache.hive.jdbc.HiveDriver"
    Class.forName(driver)

    // 获取connection
    val (url,username,password) = ("jdbc:hive2://bj-hw-8f-18-163:10000/sms2019;principal=hdfs/hdfs@ASPIRE.COM","hdfs@ASPIRE.COM","spark/file/hdfs-5g.keytab")

    println(url)

    val conn = DriverManager.getConnection(url,username,password)
//    val sql = "SELECT count(*) as mycount FROM mytest1.myt1";
//    val preparedStatement = conn.prepareStatement(sql)
//    val resultSet = preparedStatement.executeQuery()
//
//    while (resultSet.next()){
//      resultSet.getInt(0)
//    }
//
//    resultSet.close()
//    preparedStatement.close()
//    conn.close()
  }
}
