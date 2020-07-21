package com.sk.sparkcore

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Title: WordCount
 * @Package: com.sk.sparkcore
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/7/20 - 12:03
 */
object WordCount {
  def main(args: Array[String]): Unit = {
    val arr = Array("zhangsan","lisi","wangwu","zhangsan")
    val result = arr.groupBy(x => x).map(x => (x._1,x._2.length))
    println(result)

    val conf = new SparkConf().setMaster("local").setAppName(this.getClass.getCanonicalName)
    val sc = new SparkContext(conf)

    sc.setLogLevel("WARN")
    val rdd:RDD[Int] = sc.makeRDD(1 to 10)
    rdd.map((_,1)).reduceByKey(_+_).collect().foreach(println)



  }
}
