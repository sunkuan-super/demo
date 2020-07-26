package com.sk.sparkproject

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

case class UserAddress(userid: String, address: String)

object RowTransColumn {
  def main(args: Array[String]): Unit = {
    val ss = SparkSession.builder().
      appName(this.getClass.getCanonicalName).
      master("local[3]").
      getOrCreate()
    ss.sparkContext.setLogLevel("warn")

    val userinfo = Seq(UserAddress("a", "address1"), UserAddress("a", "address2"), UserAddress("a", "address2"), UserAddress("b", "address3"), UserAddress("c", "address4"))
    import ss.implicits._

    val ds1 = ss.createDataset(userinfo)
    ds1.createOrReplaceTempView("t")

    /**
     * concat_ws("*",collect_set(address))是连接成字符串，collect_set是数组
     */
    ss.sql(
      """
        |select userid,
        |concat_ws(";",collect_list(address)) as addressList,
        |concat_ws("*",collect_set(address)) as addresssSet,
        |collect_set(address) as address3,
        |collect_list(address) as address4
        | from t group by userid
        |""".stripMargin).show(false)

    ss.sql(
      """
        |select userid,
        |concat_ws(";",collect_list(address)) as addressList,
        |concat_ws("*",collect_set(address)) as addresssSet,
        |collect_set(address) as address3,
        |collect_list(address) as address4
        | from t group by userid
        |""".stripMargin).printSchema()

    //对应的dsl语法
    import ss.implicits._
    ds1.groupBy($"userid").agg(collect_list($"address").alias("a1"), collect_set($"address").alias("a2")).show()
    ds1.groupBy($"userid").agg(concat_ws(";", collect_list($"address")).alias("a1"), concat_ws(";", collect_set($"address")).alias("a2")).show()

    val ds2 = ss.sql(
      """
        |select userid,
        |collect_list(address) as address4
        | from t group by userid
        |""".stripMargin)
    ds2.createTempView("t4")
    println("----------------------------------------------")
    ss.sql("select userid,explode(address4) from t4").show(false)

    // DSL语法
    ds2.select($"userid",explode($"address4")).show(false)
    ss.stop()
  }
}
