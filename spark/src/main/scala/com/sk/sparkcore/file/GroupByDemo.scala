package com.sk.sparkcore.file

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author: create by sunkuan
 * @Description:
 * @date: 2022/4/17 - 21:49
 */
object GroupByDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("AggregateByKeyDemo")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")

    val rdd = sc.makeRDD(List("Hello Scala", "Hello Spark"))
    val flatMapRDD = rdd.flatMap(_.split(" "))
    val groupByRDD = flatMapRDD.groupBy(x => x)
    groupByRDD.collect().foreach(println)

    sc.stop()
  }

}
