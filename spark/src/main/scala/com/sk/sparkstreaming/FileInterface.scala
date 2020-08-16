package com.sk.sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @Title: FileInterface
 * @Package: com.sk.sparkstreaming
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/7/30 - 12:47
 */
object FileInterface {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName(this.getClass.getCanonicalName.dropRight(1)).setMaster("local[*]")

    val ssc = new StreamingContext(conf, Seconds(20))

    val lines = ssc.textFileStream("spark/file/elk")
    val wordCount = lines.flatMap(_.split(",")).map((_, 1)).reduceByKey(_ + _)
    wordCount.print()

    ssc.start()
    ssc.awaitTermination()
  }
}
