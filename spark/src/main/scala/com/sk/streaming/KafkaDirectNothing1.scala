//package com.sk.streaming
//
//import kafka.serializer.StringDecoder
//import org.apache.spark.SparkConf
//import org.apache.spark.streaming.kafka.{HasOffsetRanges, KafkaUtils, OffsetRange}
//import org.apache.spark.streaming.{Seconds, StreamingContext}
//
//object KafkaDirectNothing1 {
//  def main(args: Array[String]): Unit = {
//
//    val conf = new SparkConf().setAppName(this.getClass.getCanonicalName).setMaster("local[*]")
//    val ssc = new StreamingContext(conf, Seconds(6))
//    ssc.sparkContext.setLogLevel("WARN")
//
//    val brokers = "master:9092,slave:9092,slave1:9092"
//
//    val kafkaParams: Map[String, String] = Map("bootstrap.server" -> brokers, "auto.offset.reset" -> "smallest")
//    val topic = Set("myKafka0")
//    // 使用map后不能提取Offset的信息
//    val kafkaDS = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topic) //.map(_._2)
//
//
//    kafkaDS.foreachRDD((rdd, time) => {
//      if (!rdd.isEmpty()) {
//        // 打印每次接收的总数据量和时间戳
//        println(s"***** rdd.count = ${rdd.count()}; time = ${time} *****")
//
//        // 打印offset的情况(topic,partition,from,until
//        val offsetRanges: Array[OffsetRange] = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
//        for (offset <- offsetRanges) {
//          println(s"${offset.topic} ${offset.partition} ${offset.fromOffset} ${offset.untilOffset}")
//        }
//
//      }
//    })
//
//    ssc.start()
//    ssc.awaitTermination()
//
//  }
//}
