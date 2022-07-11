package com.sk.sparkproject.util

import org.apache.spark.SparkContext

/**
 * @author: create by sunkuan
 * @Description:
 * @date: 2022/5/8 - 18:33
 */
object EnvUtils {
  private val scLocal = new ThreadLocal[SparkContext]()

  def set(sc: SparkContext): Unit ={
    scLocal.set(sc)
  }

  def get(): SparkContext ={
    scLocal.get()
  }

  def clear: Unit ={
    scLocal.remove()
  }
}
