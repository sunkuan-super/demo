package com.sk.sparkcore

/**
 * @Title: WordCount
 * @Package: com.sk.sparkcore
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/7/20 - 12:03
 */
object WordCount {
  def main(args: Array[String]): Unit = {
    val arr = Array("zhangsan","lisi","wangwu","zhangsan")
    val result = arr.groupBy(x => x).map(x => (x._1,x._2.length))
    println(result)
  }
}
