package com.sk.sparksql.dataframe

import org.apache.spark.sql.Row

/**
 * @Title: RowDemo
 * @Package: com.sk.sparksql.dataframe
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/7/21 - 18:15
 */
/**
 * 泛化的无类型的JVM对象
 */
object RowDemo {
  def main(args: Array[String]): Unit = {
    val row1 = Row(1,"abc",1.2)

    println(row1(0))
    println(row1(1))
    println(row1(2))

    println(row1.getInt(0))
    println(row1.getString(1))
    println(row1.getDouble(2))

    println(row1.getAs[Int](0))
    println(row1.getAs[String](1))
    println(row1.getAs[Double](2))
  }
}
