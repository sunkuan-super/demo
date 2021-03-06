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
    //设置日志级别
    spark.sparkContext.setLogLevel("WARN")

    /**
     * 创建dataset方式一,由range创建
     */
    val ds = spark.range(1, 10)
    ds.show()
    ds.filter("id > 5").show()

    import spark.implicits._
    ds.filter($"id" > 5).show
    ds.filter('id > 5).show() //转义
    ds.where('id < 3).show()
    ds.orderBy($"id" desc).show() //排序
    ds.describe("id").show() //统计信息

    /**
     * 创建dataset方式二： 由集合创建
     */
    val lst = List(Person("Jack", 28, 184), Person("Tom", 10, 144), Person("Andy", 16, 165))
    spark.createDataset(lst).show()
    val seq = Seq(("Jack", 28, 184), ("Tom", 10, 144), ("Andy", 16, 165))
    spark.createDataset(seq).show()

    /**
     * 创建dataset方式三：读文件
     */
    val df = spark.read.
      option("header", true). //设置文件头
      option("inferschema", true). //设置自动类型推断
      option("delimit", ","). //指定分隔符(缺省是逗号 )
      csv("spark/file/aa.txt")
    df.show()

    /**
     * 集合转成DataFrame,并修改列名
     */
    val df2 = spark.createDataset(seq).withColumnRenamed("_1","name").
      withColumnRenamed("_2","age").withColumnRenamed("_3","height")
    df2.show()

    spark.createDataFrame(seq).toDF("c1","c2","c3").show()
    //
    //    df.show()
    //    spark.stop()
    //
    //    import spark.implicits._
    //    val df2 = spark.read.options(Map("header" ->true,"inferschema" -> true,"delimit" -> true)).csv("file:///home/sk/aa.txt")

  }
}
