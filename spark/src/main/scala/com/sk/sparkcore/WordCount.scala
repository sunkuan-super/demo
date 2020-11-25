package com.sk.sparkcore

import com.sk.Utils
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
    Utils.initKerberos()
    val arr = Array("zhangsan", "lisi", "wangwu", "zhangsan", "zhangsan", "lisi", "wangwu", "zhangsan")
    val result = arr.groupBy(x => x).map(x => (x._1, x._2.length))
    println(result)

    val conf = new SparkConf().setMaster("spark://sklocal:7077").setAppName(this.getClass.getCanonicalName.take(this.getClass.getCanonicalName.length - 1))
    val sc = new SparkContext(conf)
    sc.addJar("D:\\IdeaProjects\\demo\\spark\\target\\spark-1.0-SNAPSHOT.jar")
    sc.setLogLevel("WARN")
    val rdd: RDD[Int] = sc.makeRDD(1 to 10).union(sc.makeRDD(1 to 10))
    rdd.map((_, 1)).reduceByKey(_ + _).collect().foreach(println)
  }
}
