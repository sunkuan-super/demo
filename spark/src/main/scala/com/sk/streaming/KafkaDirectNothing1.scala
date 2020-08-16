package com.sk.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object KafkaDirectNothing1 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName(this.getClass.getCanonicalName).setMaster("local[*]")

    val ssc = new StreamingContext(conf, Seconds(5))
    ssc.sparkContext.setLogLevel("WARN")

    val kafkaParams: Map[String, String] = Map(("" -> "1"))
    val topic = Set("")
    val lines = KafkaUtils.createDirectStream(ssc,kafkaParams,topic)

    lines.foreachRDD((rdd,time) => {
      if(!rdd.isEmpty()){
        println(s"***** rdd.count = ${rdd.count()}; time = ${time} *****")
      }
    })

    ssc.start()
    ssc.awaitTermination()

  }
}
