package com.sk.sparksql.dataset

import org.apache.spark
import org.apache.spark.{SparkConf, sql}
import org.apache.spark.sql.SparkSession

/**
 * @Title: CreateDatasetDemo
 * @Package: com.sk.sparksql.dataset
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/7/22 - 10:55
 */
case class Person(name: String, age: Int, height: Int)

object CreateDatasetDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName(this.getClass.getCanonicalName)
    val spark = SparkSession.builder().
      config(conf).
      getOrCreate()

    spark.sparkContext.setLogLevel("WARN")

    // 创建dataset方式一
    val ds = spark.range(1, 10)
    ds.show()


    // 创建dataset方式二： 通过case class
    val seq = List(Person("Jack", 28, 184), Person("Tom", 10, 144), Person("Andy", 16, 165))
    import spark.implicits._
    val ds2 = spark.createDataset(seq)
    ds2.show()

    // 创建dataset方式三：读文件
    val df = spark.read.
      option("header", true). //设置文件头
      option("inferschema", true). //设置自动类型推断
      option("delimit", ","). //指定分隔符(缺省是逗号 )
      csv("d://aa.txt")
    df.show()
    //
    //    df.show()
    //    spark.stop()
    //
    //    import spark.implicits._
    //    val df2 = spark.read.options(Map("header" ->true,"inferschema" -> true,"delimit" -> true)).csv("file:///home/aspire/aa.txt")

  }
}
