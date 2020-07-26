package com.sk.sparkproject

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object CubeAndRollup {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]")

    val spark = SparkSession.builder().
      appName(this.getClass.getCanonicalName.take(this.getClass.getCanonicalName.length - 1)).
      config(conf).
      getOrCreate()
    spark.sparkContext.setLogLevel("warn")

    val df = spark.read.option("header", true).option("inferschema", true).csv("spark/file/cubeAndRollup.csv")

    df.createOrReplaceTempView("t")

    /**
     * cube，按照GROUP BY维度的所有组合进行聚合
     */
    spark.sql(
      """
        |select id,name,sum(num)
        |
        |from t group by cube(id,name)
        |order by id,name
        |""".stripMargin).show()

    /**
     * rollup是CUBE的子集，以最左侧的维度为主，从该维度进行层级聚合
     */
    spark.sql(
      """
        |select id,name,sum(num)
        |from t group by rollup(id,name)
        |order by id,name
        |""".stripMargin).show()

    spark.stop()
  }
}
