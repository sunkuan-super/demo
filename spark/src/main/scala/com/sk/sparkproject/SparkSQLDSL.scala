package com.sk.sparkproject

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.sum
object SparkSQLDSL {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().
      appName(this.getClass.getCanonicalName).
      master("local[3]").
      getOrCreate()

    val df = spark.read.option("header", true).option("inferschema", true).
      csv("spark/file/analyfuncdata.csv")
    df.createOrReplaceTempView("t")

    spark.sql(
      """
        |select cookieid,createtime,pv,
        |sum(pv) over (partition by cookieid order by createtime rows between unbounded preceding
        |and unbounded following) as pv1
        |from t
        |""".stripMargin).show()

    val window = Window.partitionBy("cookieid").orderBy("createtime")

    import spark.implicits._
    df.select($"cookieid", $"createtime", $"pv", sum($"pv").over(window).as("pv1")).show()

    spark.stop()
  }
}
