package com.sk.sparksql.practice

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object BasketballDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[4]")

    val spark = SparkSession.builder().
      appName(this.getClass.getCanonicalName.take(this.getClass.getCanonicalName.length - 1)).
      config(conf).
      getOrCreate()
    spark.sparkContext.setLogLevel("warn")

    val df = spark.read.option("header", true).option("inferschema", true).
      csv("spark/file/basketball.csv").
      createTempView("t")

    spark.sql(
      """
        |select *
        |from t
        |""".stripMargin).show(false)

    spark.sql(
      """
        |select team,y,lag(y) over (partition by team order by y) as y1
        |from t
        |""".stripMargin).show()
  }
}
