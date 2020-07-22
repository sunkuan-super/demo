package com.sk.sparksql.rdd

import com.sk.sparksql.dataset.Person
import org.apache.spark.SparkConf
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}

/**
 * 两个步骤，第一先转化为Row,第二加上Schema
 */
object RDD2DataFrame {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[1]").setAppName(this.getClass.getCanonicalName)

    val spark = SparkSession.builder().
      config(conf).
      getOrCreate()
    //设置日志级别
    val sc = spark.sparkContext
    sc.setLogLevel("WARN")

    val arr = Array(("Jack", 28, 184), ("Tom", 10, 144), ("Andy", 16, 165))
    val rdd = sc.makeRDD(arr).map(x => Row(x._1, x._2, x._3))

    val schema1 = new StructType().
      add("name", "string", false).
      add("age", "integer", false).
      add("height", "integer", false)

    val schema2 = StructType(List(StructField("name", StringType, false),
      StructField("age", IntegerType, false), StructField("height", IntegerType, false)))

    val df = spark.createDataFrame(rdd, schema2)
    df.show()

    /**
     * 2.0新方法
     */
    import spark.implicits._
    val df2 = sc.makeRDD(arr).toDF("name", "age", "height")
    df2.show()

    /**
     * RDD转为case class可以直接转DS和DF
     */
    val rdd2 = sc.makeRDD(arr).map(x => Person(x._1, x._2, x._3))
    val df3 = rdd2.toDF()
    df3.show()

    val ds = rdd2.toDS()
    ds.show()

    spark.stop()
  }
}
