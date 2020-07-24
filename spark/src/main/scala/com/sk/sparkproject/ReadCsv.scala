package com.sk.sparkproject

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
 * @Title: ReadCsv
 * @Package: com.sk.sparkproject
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/7/23 - 10:54
 */
object ReadCsv {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName(this.getClass.getCanonicalName)
    val spark = SparkSession.builder().
      config(conf).
      getOrCreate()
    //设置日志级别
    spark.sparkContext.setLogLevel("WARN")

    val df = spark.read.
      option("header",true).
      option("inferschema",true).
      csv("spark/file/test.csv")

    df.createTempView("tmp2")

    spark.sql(
      """select deptno,salary as over
        |
        |""".stripMargin).show()


  }

}
