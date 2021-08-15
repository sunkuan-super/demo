package com.sk.sparkcore

import com.sk.Utils
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Title: CreateRDD
 * @Package: com.sk.sparkcore
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/9/7 - 13:37
 */
object CreateRDD {
  def main(args: Array[String]): Unit = {
    Utils.initKerberos()

    val conf = new SparkConf().setMaster("local[*]").setAppName(this.getClass.getCanonicalName.take(this.getClass.getCanonicalName.length - 1))
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")

    // 1、通过程序中的集合创建RDD
    val rdd1 = sc.makeRDD(1 to 10,10)
    val rdd2 = sc.parallelize(1 to 1000, 10)

    // 2、使用本地文件创建RDD
    // 3、使用hdfs文件创建RDD
    val rdd3 = sc.textFile("hdfs://sklocal:8020/mdt/20200224/00000_0")
    rdd3.collect().foreach(println(_))
    val result = sc.wholeTextFiles("hdfs://sklocal:8020/mdt/20200224/00000_0")
    result.collect().foreach(elem =>{
      println(elem._1)
      println(elem._2)
    })


    // val value1 = sc.sequenceFile("/home/sk/hdfs.txt",10)

//    sc.hadoopRDD()
    val value2 = sc.objectFile("/hdfs.txt", 10)
  }
}
