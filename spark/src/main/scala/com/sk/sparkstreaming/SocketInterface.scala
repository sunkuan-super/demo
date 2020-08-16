package com.sk.sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @Title: SocketInterface
 * @Package: com.sk.sparkstreaming
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/7/30 - 13:57
 */
object SocketInterface {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName(this.getClass.getCanonicalName.dropRight(1)).setMaster("local[3]")

    val ssc = new StreamingContext(conf, Seconds(10))
    ssc.sparkContext.setLogLevel("WARN")

    val lines = ssc.socketTextStream("127.0.0.1",8888)
    val wordCount = lines.flatMap(_.split("\\s+")).map((_, 1)).reduceByKey(_ + _)
    wordCount.print()

    ssc.start()
    ssc.awaitTermination()
  }
}
