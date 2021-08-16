package com.sk.sparkcore.file

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Title: ToCsv
 * @Package: com.sk.sparkcore.file
 * @Description:
 * @Author: sk
 * @Date: 2021/6/15 - 11:03
 */
object ToCsv {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Spark_Lzo_File").setMaster("local[2]")

    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")
    val rdd: RDD[String] = sc.textFile("file:///E:\\Cennavi\\quanzhou\\20210501\\d=01\\w=7\\00")

    println(rdd.count())

  }
}
