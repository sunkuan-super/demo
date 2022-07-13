//package com.sk.streaming
//
//import kafka.utils.ZkUtils
//import kafka.common.TopicAndPartition
//import kafka.message.MessageAndMetadata
//import kafka.serializer.StringDecoder
//import org.I0Itec.zkclient.ZkClient
//import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}
//import org.apache.hadoop.hbase.client.{ConnectionFactory, Put, Scan}
//import org.apache.hadoop.hbase.util.Bytes
//import org.apache.log4j.{Level, Logger}
//import org.apache.spark.SparkConf
//import org.apache.spark.streaming.{Seconds, StreamingContext, Time}
//import org.apache.spark.streaming.kafka.{HasOffsetRanges, KafkaUtils, OffsetRange}
//
//object KafkaDirectWithHBase {
//  // 保存Offset
//  def saveOffsets(topicName: String, groupId: String,
//                  offsetRanges: Array[OffsetRange],
//                  htableName: String, batchTime: Time): Unit = {
//    val hbaseConf = HBaseConfiguration.create()
//    val conn = ConnectionFactory.createConnection(hbaseConf)
//    val table = conn.getTable(TableName.valueOf(htableName))
//    val rowkey = s"${topicName}:${groupId}:${String.valueOf(batchTime.milliseconds)}"
//    val put = new Put(rowkey.getBytes)
//    for (offset <- offsetRanges) {
//      put.addColumn(Bytes.toBytes("offsets"),
//        Bytes.toBytes(offset.partition.toString), Bytes.toBytes(offset.untilOffset.toString))
//    }
//
//    table.put(put)
//    conn.close()
//  }
//
//  // 从zookeeper中获取topic的分区数。
//  def getNumberOfPartitionsForTopicFromZK(topicName: String, groupId: String, zkrootpath: String,
//                                          sessTimeout: Int,  connTimeOut: Int): Int = {
//    val zkClient = new ZkClient(zkrootpath, sessTimeout, connTimeOut)
//    val numberOfPartitions = ZkUtils.getAllPartitions(zkClient).toList.count(_.topic==topicName)
//    zkClient.close()
//    numberOfPartitions
//  }
//
//  // 从HBase中获取最新的offset
//  def getLastestOffsets(topicName : String, groupId : String,
//                        htableName: String, zkrootpath: String,
//                        sessTimeout: Int,   connTimeOut: Int): Map[TopicAndPartition, Long] = {
//    // 从zookeeper获取给定topic的Partition的个数
//    val numberOfPartitions = getNumberOfPartitionsForTopicFromZK(topicName, groupId, zkrootpath, sessTimeout, connTimeOut)
//    val hbaseConf = HBaseConfiguration.create()
//
//    // 从hbase中获取最后提交的offset
//    val conn = ConnectionFactory.createConnection(hbaseConf)
//    val table = conn.getTable(TableName.valueOf(htableName))
//    val startRow = topicName + ":" + groupId + ":" + String.valueOf(System.currentTimeMillis())
//    val stopRow  = topicName + ":" + groupId + ":" + 0
//    val scan = new Scan()
//    val scanner = table.getScanner(scan.withStartRow(startRow.getBytes).withStopRow(stopRow.getBytes).setReversed(true))
//    val result = scanner.next()
//
//    var hbaseNumberOfPartitions = 0 // 在hbase中获取的分区数量
//    if (result != null){
//      // 将分区数量设置为hbase表的列数量
//      hbaseNumberOfPartitions = result.listCells().size()
//    }
//
//    val fromOffsets = collection.mutable.Map[TopicAndPartition, Long]()
//    // 等于0，代表HBase中没有保存任何信息。那么就将值初始化为0
//    if (hbaseNumberOfPartitions == 0){
//      for (partition <- 0 until numberOfPartitions) {
//        fromOffsets += ((TopicAndPartition(topicName, partition), 0))
//      }
//    } else if(numberOfPartitions > hbaseNumberOfPartitions){    // 处理新增加的分区添加到kafka的topic
//      // 原有分区的offset正常获取
//      for (partition <- 0 until numberOfPartitions) {
//        val offset = Bytes.toString(result.getValue(Bytes.toBytes("offsets"),
//          Bytes.toBytes(partition.toString)))
//        fromOffsets += ((TopicAndPartition(topicName, partition), offset.toLong))
//        }
//
//      // 新分区的offset置为0
//      for (partition <- hbaseNumberOfPartitions until numberOfPartitions) {
//        fromOffsets += ((TopicAndPartition(topicName, partition), 0))
//      }
//    } else {
//      // 既不是第一次，分区也没有改变。获取上次运行的offset
//      for (partition <- 0 until numberOfPartitions) {
//        val offset = Bytes.toString(result.getValue(Bytes.toBytes("offsets"),
//          Bytes.toBytes(partition.toString)))
//        fromOffsets += ((TopicAndPartition(topicName, partition), offset.toLong))
//      }
//    }
//
//    scanner.close()
//    conn.close()
//    fromOffsets.toMap
//  }
//
//  def main(args: Array[String]): Unit = {
//    val processingInterval = 2
//    val brokers = "node1:9092"
//    val topics = "mykafka1"
//    val kafkaParams = Map[String, String]("metadata.broker.list" -> brokers,
//      "auto.offset.reset" -> "smallest")
//    val groupId = "mycgHBase"
//    val hbaseTableName = "kafka_offsets"
//    val zkRootPath = "node1:2181/kafka0.11"
//
//    Logger.getLogger("org").setLevel(Level.WARN)
//    val sparkConf = new SparkConf().setAppName(s"${this.getClass.getCanonicalName}").setMaster("local[2]")
//    val ssc = new StreamingContext(sparkConf, Seconds(processingInterval))
//
//    // 获取 offsets。这个代码只在streaming程序启动时执行（只能看见一次打印的结果）
//    val fromOffsets = getLastestOffsets(topics, groupId, hbaseTableName, zkRootPath, 30000, 30000)
//    println(s"fromOffsets = $fromOffsets")
//
//    // 获取kafkaStream
//    val messageHandler = (mmd: MessageAndMetadata[String, String]) => (mmd.topic, mmd.message())
//    val kafkaStream = KafkaUtils.createDirectStream[String, String,
//      StringDecoder, StringDecoder, (String, String)](ssc, kafkaParams, fromOffsets, messageHandler)
//
//    // 这里是具体的业务逻辑(省略)
//
//    kafkaStream.foreachRDD((rdd,btime) => {
//      if(!rdd.isEmpty()){
//        println(s"*********** rdd.count = ${rdd.count()}; btime = $btime ***********")
//        // 保存offset
//        saveOffsets(topics, groupId, rdd.asInstanceOf[HasOffsetRanges].offsetRanges, hbaseTableName, btime)
//      }
//    })
//
//    ssc.start()
//    ssc.awaitTermination()
//  }
//}