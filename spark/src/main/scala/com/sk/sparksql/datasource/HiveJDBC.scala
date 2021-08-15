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
    val (url,username,password) = ("jdbc:hive2://sk:10000/ha;principal=hdfs/hdfs@SK.COM","hdfs@SK.COM","spark/file/hdfs-sk.keytab")

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
