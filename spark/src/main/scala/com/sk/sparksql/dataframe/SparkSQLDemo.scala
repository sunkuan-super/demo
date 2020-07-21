package com.sk.sparksql.dataframe

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext, sql}

/**
 * @Title: SparkSQLDemo
 * @Package: com.sk.sparksql.dataframe
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/7/21 - 16:20
 */
object SparkSQLDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName(this.getClass.getCanonicalName).setMaster("local")
    val ssc = new sql.SparkSession.Builder

    ssc.appName(this.getClass.getCanonicalName)
      .config(conf)
      .enableHiveSupport()  //有了这句就可以访问hive了
      .getOrCreate()



  }

}
