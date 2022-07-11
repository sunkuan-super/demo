package com.sk.sparkcore.file

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author: create by sunkuan
 * @Description:
 * @date: 2022/4/14 - 23:21
 */
object AggregateByKeyDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("AggregateByKeyDemo")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")

    val rdd = sc.makeRDD(List(("a", 1), ("a", 2), ("b", 3), ("b", 4), ("b", 5), ("a", 6)), 2)
    rdd.saveAsTextFile("out")
    println(s" = ${rdd.getNumPartitions}")
    val value1 = rdd.aggregateByKey((0, 0))(
      (tup, value) => {
        println(">>>>>>> tup" + tup)
        println(">>>>>>> " + value)
        (tup._1 + value, tup._2 + 1)
      },
      (tup1, tup2) => {
        println("============= " + tup1 + " ==== " + tup2)
        (tup1._1 + tup2._1, tup2._1 + tup2._1)
      }
    )

    value1.collect()
  }
}
