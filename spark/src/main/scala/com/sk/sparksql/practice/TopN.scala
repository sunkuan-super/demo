package com.sk.sparksql.practice

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object TopN {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]")

    val spark = SparkSession.builder().
      appName(this.getClass.getCanonicalName.take(this.getClass.getCanonicalName.length - 1)).
      config(conf).
      getOrCreate()
    spark.sparkContext.setLogLevel("warn")

    val df = spark.read.option("header", true).option("inferschema", true).csv("spark/file/TopN.csv").createTempView("t")

    spark.sql("select * from t").show(false)

    spark.sql(
      """
        |select DEPTNO,ranks[0],ranks[1],ranks[2] from (
        |
        |       select DEPTNO,collect_list(SAL) as ranks from (
        |
        |          select DEPTNO,SAL,
        |          row_number() over (partition by DEPTNO order by SAL desc) as rank1
        |          FROM t
        |          having rank1 <= 3
        |       ) group by deptno
        |
        |) order by DEPTNO
        |""".stripMargin).show(false)
  }

}
