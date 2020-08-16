package com.sk.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object KafkaReceiver5 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName(this.getClass.getCanonicalName).
      setMaster("local[2]")

    val ssc = new StreamingContext(conf, Seconds(5))
    ssc.sparkContext.setLogLevel("WARN")

    val zkQuorum = "master:2181,slave:2181,slave1:2181"
    val groupId = "kafkagroup1"
    val topics = Map(("teacher" -> 1))

    val lines = KafkaUtils.createStream(ssc, zkQuorum, groupId, topics).map(_._2)
    lines.foreachRDD((rdd,time) => {
      if(!rdd.isEmpty()){
        println(s"rdd.count() = ${rdd.count()}")
      }
    })
  }
}
