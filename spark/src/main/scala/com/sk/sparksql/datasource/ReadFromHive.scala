package com.sk.sparksql.datasource

import com.sk.Utils
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
 * @Title: ReadFromHive
 * @Package: com.sk.sparksql.datasource
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/7/28 - 10:16
 */
object ReadFromHive {
  def main(args: Array[String]): Unit = {

    Utils.initKerberos()
    val conf = new SparkConf()
    val spark = SparkSession.builder()
      .appName(this.getClass.getCanonicalName.dropRight(1))
      .master("local[*]")
      .enableHiveSupport()
      .config("hive.metastore.warehouse.dir","thrift://bj-hw-8f-18-163:9083")
      .config("spark.sql.warehouse.dir", "/user/hive/warehouse")
      .config(conf)
      .getOrCreate()

    spark.sparkContext.setLogLevel("WARN")

    spark.sql("show databases").show()

    spark.sql("use sms2019").show()
    spark.sql("show tables").show()

    spark.sql("select count(1) from up_sms_detail").show()
    val df3 = spark.sql("select * from submit_sms_detail limit 100000")
    df3.collect().foreach(println(_))
    spark.stop()
  }

}
