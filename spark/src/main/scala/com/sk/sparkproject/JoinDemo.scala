package com.sk.sparkproject

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object JoinDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[4]")

    val spark = SparkSession.builder().
      appName(this.getClass.getCanonicalName.take(this.getClass.getCanonicalName.length - 1)).
      config(conf).
      getOrCreate()
    spark.sparkContext.setLogLevel("warn")

    val ds1 = spark.range(1,21)
    val ds2 = spark.range(10,31)

    ds1.createTempView("ds1")
    ds2.createTempView("ds2")

    spark.sql(
      """
        |select * from ds1
        |intersect
        |select * from ds2
        |order by id
        |""".stripMargin).show()

    spark.sql(
      """
        |select ds1.id from ds1
        |left semi join ds2 on ds1.id = ds2.id
        |""".stripMargin).show()

    spark.sql(
      """
        |select ds1.id from ds1
        |join ds2 on ds1.id = ds2.id
        |""".stripMargin).show()

    spark.sql(
      """
        |select id
        |from ds1
        |where id in (select id from ds2)
        |""".stripMargin).show()

    spark.sql(
      """
        |select id
        |from ds1
        |where  exists (select id from ds2 where ds2.id = ds1.id)
        |""".stripMargin).show()

    spark.stop()
  }
}
