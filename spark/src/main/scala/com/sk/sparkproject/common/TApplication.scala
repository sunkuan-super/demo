package com.sk.sparkproject.common

import com.sk.sparkproject.util.EnvUtils
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author: create by sunkuan
 * @Description:
 * @date: 2022/5/8 - 18:36
 */
trait TApplication {

  def start(master: String = "local[*]", app: String = "Application")(op: => Unit) {
    val conf = new SparkConf().setMaster(master).setAppName(app)
    val sc = new SparkContext(conf)
    EnvUtils.set(sc)
    try {
      op
    } catch {
      case ex: Exception => println(ex.printStackTrace())
    }

    // TODO 关闭连接
    sc.stop()
    EnvUtils.clear
  }
}
