package com.sk.sparkproject

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
 * @Title: AnalyFuncData
 * @Package: com.sk.sparkproject
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/7/23 - 11:37
 */
object AnalyFuncData {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName(this.getClass.getCanonicalName)
    val spark = SparkSession.builder().
      config(conf).
      getOrCreate()
    //设置日志级别
    spark.sparkContext.setLogLevel("WARN")

    val df = spark.read.
      option("header", true).
      option("inferschema", true).
      csv("spark/file/analyfuncdata.csv")

    df.createTempView("t1")

    /**
     * 求和用的分析函数
     */
    spark.sql(
      """select cookieid, createtime, pv,
        |sum(pv) over (partition by cookieid order by createtime) as pv1,
        |sum(pv) over (partition by cookieid order by createtime rows between unbounded preceding and current row) as pv2,
        |sum(pv) over (partition by cookieid order by createtime rows between unbounded preceding and unbounded following) as pv3,
        |sum(pv) over (partition by cookieid order by createtime rows between current row and unbounded following) as pv4,
        |sum(pv) over (partition by cookieid order by createtime rows between 1 preceding  and unbounded following) as pv5
        |from t1
        |""".stripMargin).show()


    /**
     * 排名函数
     */
    spark.sql(
      """select cookieid, createtime, pv,
        |row_number() over (partition by cookieid order by pv) as row_number,
        |rank() over (partition by cookieid order by pv) as rank,
        |dense_rank() over (partition by cookieid order by pv) as dense_rank
        |from t1
        | having row_number <= 10
        |""".stripMargin).show()
    // 合计值、累计值

    /**
     * 随机拆分
     */
    
  }
}
