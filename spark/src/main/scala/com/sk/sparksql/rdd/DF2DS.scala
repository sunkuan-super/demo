package com.sk.sparksql.rdd

import com.sk.sparksql.dataset.Person
import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

object DF2DS {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[3]").setAppName(this.getClass.getCanonicalName)

    val spark = SparkSession.builder().
      config(conf).
      getOrCreate()
    //设置日志级别
    spark.sparkContext.setLogLevel("WARN")
    val sc = spark.sparkContext
    val arr = Array(("Jack", 28, 184), ("Tom", 10, 144), ("Andy", 16, 165))
    import spark.implicits._
    val rdd = sc.makeRDD(arr)

    val df1 = rdd.toDF()
    df1.show()
    println("----------------------")
    val df2: DataFrame = rdd.toDF("name", "age", "height")
    val ds: Dataset[Person] = df2.as[Person]
    ds.show()

    spark.stop()

  }
}
