package com.sk.sparksql.datasource

import org.apache.spark.sql.SparkSession

/**
 * @Title: ParquetDemo
 * @Package: com.sk.sparksql.datasource
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/7/27 - 12:01
 */
object ParquetWrite {
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

    jdbcDF.select("id","name").write.save("spark/file/student.parquet")

  }
}
