package com.sk.util

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.hadoop.io.IOUtils
import org.apache.log4j.LogManager

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}
import java.util.Properties

/**
 * @Title: HadoopUtils
 * @Package: com.sk.util
 * @Description:
 * @Author: sk
 * @Date: 2021/8/27 - 10:19
 */
object HadoopUtils {
  private lazy val logger = LogManager.getLogger(this.getClass.getName.dropRight(1))

  def readPropFileFromHDFS(fs: FileSystem, path: String): ByteArrayInputStream = {
    println("readPropFileFromHDFS")
    // 从HDFS上读取配置文件
    try {
      val is = fs.open(new Path(path), 4096)
      val baos = new ByteArrayOutputStream(4096)
      IOUtils.copyBytes(is, baos, fs.getConf)
      new ByteArrayInputStream(baos.toByteArray)
    } catch {
      case ex: Exception=>
        logger.error("从本地文件系统和HDFS上读取配置文件时发生错误", ex)
        IOUtils.closeStream(fs)
        System.exit(2)
        null
    }
  }

  def main(args: Array[String]): Unit = {
    val configuration = new Configuration()
//    configuration.addResource("conf/core-site.xml");
//    configuration.addResource("conf/hdfs-site.xml");
//    configuration.addResource("conf/mapred-site.xml");
//    configuration.addResource("conf/yarn-site.xml");
    configuration.set("fs.defaultFS", "hdfs://hadoop1:9000")
    configuration.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem")
    System.setProperty("HADOOP_USER_NAME", "root")

    logger.info("开始")

    logger.info(configuration)
    val prop = new Properties()
    val propByteArrInput = readPropFileFromHDFS(FileSystem.get(configuration), "hdfs://hadoop1:9000/data/sk/system.properties.old")
    if(propByteArrInput != null){
      prop.load(propByteArrInput)
    }

    logger.info(propByteArrInput)

    val str = prop.getProperty("cities")
    logger.info(str)
  }
}
