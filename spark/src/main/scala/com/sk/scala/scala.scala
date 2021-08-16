package com.sk.scala

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.io.{LongWritable, Text}
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Title: scala
 * @Package: com.sk
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/7/20 - 12:03
 */
object scala {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName(this.getClass.getName.dropRight(1))
    val spark = SparkSession.builder().config(conf).getOrCreate()
    val sc = spark.sparkContext
    sc.setLogLevel("WARN")

    val hadoopConf = new Configuration()
    val rdd = sc.newAPIHadoopFile("/data/fujian/a=350200/b=NT/y=2021/m=05/d=01/*.lzo", classOf[LzoTextInputFormat], classOf[LongWritable], classOf[Text], hadoopConf).map(x => x._2.toString)
    val rdd2 = rdd.filter(line => {val words = line.split(","); words.length >= 18 && words(5).toDouble > 1000 && words(10).toDouble > 2 ;})
    rdd2.repartition(1).saveAsTextFile("/data/save1/xiamen20210809筛选")
  }
}
