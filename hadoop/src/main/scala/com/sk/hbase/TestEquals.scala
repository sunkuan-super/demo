package com.sk.hbase

/**
 * @Title: TestEquals
 * @Package: com.sk.hbase
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/8/20 - 11:06
 */
object TestEquals {
  def main(args: Array[String]): Unit = {
    val str = new String("school")
    val str2 = "school"
    println(str == str2)
    println(str2.equals(str))
    println(str2.eq(str))
  }
}
