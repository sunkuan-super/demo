package com.sk.sparksql.datasource

import java.util.Properties

import org.apache.spark.sql.{SaveMode, SparkSession}

/**
 * @Title: WriteToMySQL
 * @Package: com.sk.sparksql.db
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/7/27 - 11:49
 */
object WriteToMySQL {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName(this.getClass.getCanonicalName.take(this.getClass.getCanonicalName.length-1))
      .master("local[*]")
      .getOrCreate()
    spark.sparkContext.setLogLevel("WARN")

    val prop = new Properties()
    prop.put("user","root")
    prop.put("password","123456")
    prop.put("driver","com.mysql.cj.jdbc.Driver")

//    val jdbcDF = spark.read.format("jdbc")
//      .option("url","jdbc:mysql://localhost:3306/spark?serverTimezone=Asia/Shanghai&useSSL=false&characterEncoding=utf-8")
//      .option("driver","com.mysql.cj.jdbc.Driver")
//      .option("dbtable","student")
//      .option("user","root")
//      .option("password","123456").load()
    import spark.implicits._
    val jdbcDF = spark.sparkContext.makeRDD(Array((1,"hadoop","nan",24),(2,"hive","nv",25),(3,"hbase","nan",26))).toDF("id","name","gender","age")

    jdbcDF.write.mode(SaveMode.Append).jdbc("jdbc:mysql://localhost:3306/spark?serverTimezone=Asia/Shanghai&useSSL=false&characterEncoding=utf-8","spark.student",prop)

  }
}
