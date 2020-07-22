package com.sk.sparksql.rdd

import com.sk.sparksql.dataset.Person
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object Dataset2RDD {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName(this.getClass.getCanonicalName)

    val spark = SparkSession.builder().
      config(conf).
      getOrCreate()
    //设置日志级别
    spark.sparkContext.setLogLevel("WARN")

    val lst = List(Person("Jack", 28, 180), Person("Tom", 10, 144), Person("Andy", 16, 165))
    import spark.implicits._
    val ds = spark.createDataset(lst)

    val rdd = ds.rdd
    rdd.collect().foreach(println)

  }
}
