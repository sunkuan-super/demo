package com.sk.sparkproject.application

import com.sk.sparkproject.common.TApplication
import com.sk.sparkproject.controller.WordCountController
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author: create by sunkuan
 * @Description:
 * @date: 2022/5/8 - 17:25
 */
object WordCountApp extends App with TApplication {
  start(){
    val controller = new WordCountController
    controller.dispatch()
  }
}
