package com.sk.sparksql.db

import org.apache.spark.sql.SparkSession

/**
 * @Title: ReadFromMySQL
 * @Package: com.sk.sparksql.db
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/7/26 - 23:27
 */
object ReadFromMySQL {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName(this.getClass.getCanonicalName.take(this.getClass.getCanonicalName.length-1))
      .master("local[*]")
      .getOrCreate()
    spark.sparkContext.setLogLevel("WARN")

    val jdbcDF = spark.read.format("jdbc")
      .option("url","jdbc:mysql://localhost:3306/spark?serverTimezone=Asia/Shanghai&useSSL=false&characterEncoding=utf-8")
      .option("driver","com.mysql.cj.jdbc.Driver")
      .option("dbtable","student")
      .option("user","root")
      .option("password","123456").load()

    jdbcDF.show()

    jdbcDF.printSchema

    spark.stop()

  }
}
