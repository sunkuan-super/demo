package com.sk.sparksql.rdd

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object DataFrame2RDD {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName(this.getClass.getCanonicalName)

    val spark = SparkSession.builder().
      config(conf).
      getOrCreate()
    //设置日志级别
    spark.sparkContext.setLogLevel("WARN")

    val seq = Seq(("Jack", 28, 184), ("Tom", 10, 144), ("Andy", 16, 165))
    val df = spark.createDataFrame(seq)
    df.show()

    val rdd = df.rdd
    rdd.collect().foreach(println)
  }
}
