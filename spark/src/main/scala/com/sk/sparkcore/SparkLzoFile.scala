package com.sk.sparkcore

import com.sk.scala.{NTCNTF, TimeUtils}
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

import java.io.{File, FileOutputStream, PrintWriter}
import java.util
import java.util.{Collections}
import scala.collection.mutable
import scala.io.Source

/**
 * @Title: SparkLzoFile
 * @Package: com.sk.sparkcore
 * @Description:
 * @Author: sk
 * @Date: 2021/6/11 - 14:31
 */
object SparkLzoFile {
  def main(args: Array[String]): Unit = {
    //    val conf = new SparkConf().setAppName("Spark_Lzo_File").setMaster("local[1]")
    //    val sc = new SparkContext(conf)
    //    sc.setLogLevel("WARN")
    //    getHigh(sc)
    val array = scala.io.Source.fromFile("E://Cennavi//2021-07//wuxisuo//RTIC//mapResPath//cityAMapping.csv").getLines.toArray
    val arr2 = array.map(line => {
      val cols = line.split("#")
      val rticID: String = cols(0)
      val base: Array[String] = cols(1).split(",")
      val length = Integer.valueOf(base(0).split("\\.")(0))
      val kind: Int = base(1).toInt
      val vkt: Long = base(2).toLong
      var freeSpeed: Int = -1

      length
    })

    val sorted = arr2.sorted
    sorted.foreach(println(_))

    println(s"sorted(1). = ${sorted(1)}")

  }

  def testRDD(): Unit = {
    val conf = new SparkConf().setAppName("Spark_Lzo_File").setMaster("local[1]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")

    val rdd = sc.makeRDD((1 to 10).toArray)
    rdd.saveAsTextFile("d://testrdd.csv")
  }

  def getLinkMap(): Unit = {
    val source = Source.fromFile("E:\\Cennavi\\xiamen\\厦门市收费站及关联link.csv")
    val strings = source.getLines()

    strings.foreach(println(_))
  }

  def getLinkMap2(): Unit = {
    val conf = new SparkConf().setAppName("Spark_Lzo_File").setMaster("local[1]")

    val spark = SparkSession.builder().
      appName("Spark_Lzo_File").
      master("local[*]")
      .config(conf).getOrCreate()
    spark.sparkContext.setLogLevel("WARN")

    val df = spark.read.option("header", "true").option("inferSchema", true).csv("E:\\new 29.txt")
    df.createOrReplaceTempView("t1")
    val rdd = df.rdd
    df.sqlContext.sql("select * from t1").show()

  }

  /**
   * test
   */
  def test(): Unit = {
    val conf = new SparkConf().setAppName("Spark_Lzo_File").setMaster("local[*]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")

    // 指定的link
    val source = Source.fromFile("C:\\Users\\sunkuan\\Documents\\WeChat Files\\wxid_7555995544114\\FileStorage\\File\\2021-06\\高速路(1).txt").getLines().toArray
    val hashMap = new mutable.HashMap[String, String]()
    source.map(x => hashMap.put(x, x))
    val broadcastVar = sc.broadcast(hashMap)

    val input = "E:\\Cennavi\\ningde"
    val output = "d:\\ningde2"
    //val input = "E://Cennavi//ningde"
    //    val output = "d://ningde2"
    val file = new File(input)

    if (!file.isDirectory) {
      return
    }

    val files1 = file.listFiles().filter(files1x => !files1x.isFile)
    files1.foreach(
      file1 => {
        val outputPath = output.concat(File.separator).concat(file1.getName)
        new File(outputPath).mkdirs()
        val files2 = file1.listFiles().filter(file2x => !file2x.isFile)

        files2.foreach(
          file2 => {
            val files3 = file2.listFiles().filter(file3x => !file3x.isFile)
            files3.foreach(file3 => {

              //              val input_e = input + File.separator + file1.getName + File.separator + file2.getName + File.separator + file3.getName + File.separator
              val input_e = input + "//" + file1.getName + "//" + file2.getName + "//" + file3.getName + "//"

              for (hour <- 0 until getHourList().size()) {
                dataProcess(sc, broadcastVar, input_e + hour, outputPath)
              }
            })
          })

      }
    )

  }

  def dataProcess(sc: SparkContext, broadcastVar: Broadcast[mutable.HashMap[String, String]], input: String, output: String): Unit = {
    // 文件路径
    val filePath = "file:///" + input
    println(s"filePath = ${filePath}")
    // 创建RDD
    val rdd: RDD[String] = sc.textFile(filePath)

    // 开始时间
    val stime = System.currentTimeMillis()
    // rdd长度
    println(s"rdd.count = ${rdd.count()}")


    val value1 = rdd.filter(ntcntf => {
      val strings = ntcntf.split(",")
      strings.length >= 18 && strings(1).trim.length >= 8 && broadcastVar.value.contains(strings(1).trim.substring(7))
    }).map(ntcntf => {
      val strings = ntcntf.split(",")

      val linkId = strings(1).trim.substring(7)
      val laneFlag = strings(2)
      //        val publishTime = TimeUtils.getDataTime(strings(3))
      val publishTime = strings(3)
      val roadLen = strings(5)
      val roadClass = strings(6)
      val travelTime = strings(9)
      val status = strings(10)
      val calTravelTime = strings(14)
      val fillFlag = strings(15)

      val ntcntf1 = NTCNTF(linkId,
        laneFlag,
        publishTime,
        roadLen,
        roadClass,
        travelTime,
        status,
        calTravelTime,
        fillFlag)
      //        println(ntcntf1)
      (ntcntf1.linkId, ntcntf1)
    })

    val pathBroadcast = sc.broadcast(output)
    value1.groupByKey().foreach {
      case (k, v) => {
        new File(pathBroadcast.value + k).mkdirs()
        val stringToNtcntfs = v.groupBy(_.laneFlag)
        stringToNtcntfs.map {
          case (k2, v2) => {
            val writer = new PrintWriter(new FileOutputStream(pathBroadcast.value + k + File.separator + k + "_" + k2 + ".csv"))
            v2.foreach(va => {
              writer.println(va)
              writer.flush()
            })

            writer.close()
          }
        }

      }
    }

    val etime = System.currentTimeMillis()
    println(s"etime - stime = ${etime - stime}")
  }

  def getHigh(sc: SparkContext) {
    val value = sc.textFile("file:///D://xm//20210504//d=04//w=3//*.dat")
    val l = value.count()
    println(s"l = ${l}")

    value
  }

  def getHourList(): util.List[String] = {
    val list = new util.ArrayList[String]
    for (i <- 0 until 24) {
      if (i < 10) list.add("0" + i)
      else list.add(i + "")
    }
    Collections.sort(list)
    list
  }

}
