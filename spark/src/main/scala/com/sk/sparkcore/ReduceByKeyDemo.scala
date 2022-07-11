package com.sk.sparkcore

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**
 * @author: create by sunkuan
 * @Description:
 * @date: 2022/4/11 - 23:51
 */
object ReduceByKeyDemo {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("ReduceByKeyDemo")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")

    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 1, 2, 3, 4),2)
    val mapRDD: RDD[(Int, Int)] = rdd.map((_, 1))
    val newRDD = mapRDD.partitionBy(new HashPartitioner(2))
    newRDD.partitionBy(new HashPartitioner(2))

    val value = sc.makeRDD(List(1, 2, 3, 4), 2)
    val value1 = value.mapPartitions(
      iter => Iterator(iter.max)
    )

    value1.collect().foreach(println)
  }

}
