package com.sk.streaming

import kafka.common.TopicAndPartition
import kafka.message.MessageAndMetadata
import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.{HasOffsetRanges, KafkaUtils, OffsetRange}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object KafkaDirectNothing2 {
  def main(args: Array[String]): Unit = {
    // Kafka Direct接口方式二(使用了另一种接口API，允许指定offset的值)，获取offset
    val conf = new SparkConf().setAppName(this.getClass.getCanonicalName).setMaster("local[3]")
    val ssc = new StreamingContext(conf, Seconds(5))
    ssc.sparkContext.setLogLevel("WARN")

    val brokers = "master:9092,slave:9092,slave1:9092"

    val kafkaParams: Map[String, String] = Map("bootstrap.server" -> brokers, "auto.offset.reset" -> "smallest")
    val topic = Set("myKafka0")

    // 在这里可以自定义各partiton的offset。存在越界的问题
    // 实际应用中应该是到，我们所用的外部存储中获取
    val fromOffsets: Map[TopicAndPartition, Long] = Map(
      TopicAndPartition("myKafka0", 0) -> 300L,
      TopicAndPartition("myKafka0", 1) -> 300L,
      TopicAndPartition("myKafka0", 2) -> 300L,
      TopicAndPartition("myKafka0", 3) -> 300L,
      TopicAndPartition("myKafka0", 4) -> 300L,
      TopicAndPartition("myKafka0", 5) -> 300L
    )

    //将Kafka的消息进行转换，这里将数据变成(topic,message)的形式，也可以使用其他的形式
    val messageHandler = (mmd: MessageAndMetadata[String, String]) => (mmd.topic, mmd.message())

    // 使用map后不能提取Offset的信息
    val kafkaDS = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder, (String, String)](ssc, kafkaParams, fromOffsets, messageHandler)


    kafkaDS.foreachRDD((rdd, time) => {
      if (!rdd.isEmpty()) {
        // 打印每次接收的总数据量和时间戳
        println(s"***** rdd.count = ${rdd.count()}; time = ${time} *****")
        rdd.foreach(println _)


        // 获取offset的值 (topic,partition,from,until)
        val offsetRanges: Array[OffsetRange] = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
        for (offset <- offsetRanges) {
          println(s"${offset.topic} ${offset.partition} ${offset.fromOffset} ${offset.untilOffset}")
        }
        println()
        // 在下面完成Offset值的保存。即将offset保存到我们所用的外部存储中。本例子未实现
      }
    })

    ssc.start()
    ssc.awaitTermination()

  }
}
