package com.sk

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author: create by sunkuan
 * @Description:
 * @date: 2022/5/13 - 20:31
 */
object AggregateByKeyDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName(this.getClass.getCanonicalName.take(this.getClass.getCanonicalName.length - 1))
    val sc = new SparkContext(conf)
    // sc.addJar("D:\\IdeaProjects\\demo\\spark\\target\\spark-1.0-SNAPSHOT.jar")
    sc.setLogLevel("WARN")
//    val rdd: RDD[Int] = sc.makeRDD(1 to 10).union(sc.makeRDD(1 to 10))
//    rdd.map((_, 1)).reduceByKey(_ + _).collect().foreach(println)

    sc.setCheckpointDir("dirsss/aaa")
    val rdd = sc.makeRDD(List(("a", 1), ("a", 2), ("a", 3), ("a", 4), ("b", 8), ("b", 5), ("b", 6), ("b", 7), ("c", 10)), 2)
    rdd.checkpoint()

    rdd.saveAsTextFile("ttt")
    println(rdd.getNumPartitions)
    rdd.aggregateByKey((0, 0))(
      (t, v) => {
        println(s"t= $t")
        println(s"v= $v")
        println("------------")
        (t._1 + v, t._2 +1)
      },
      (t1, t2) => {
        println((t1, t2))
        (t1._1 + t2._1, t1._2 + t2._2)
      }
    ).collect()
  }

}
