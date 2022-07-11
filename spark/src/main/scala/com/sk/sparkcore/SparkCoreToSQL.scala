//package com.sk.sparkcore
//
//import com.sk.bean.People
//import org.apache.spark.sql.SparkSession
//import org.apache.spark.{SparkConf, SparkContext}
//
//object SparkCoreToSQL {
//  def main(args: Array[String]): Unit = {
//
//    val conf = new SparkConf().setMaster("local").setAppName(this.getClass.getCanonicalName)
//    val sparkSession = new SparkSession.Builder().appName("name").master("local").config(conf).getOrCreate()
//    val sc = sparkSession.sparkContext
//
//    import sparkSession.implicits._
//    //    sc.addJar("D:\\IdeaProjects\\demo\\spark\\target\\spark-1.0-SNAPSHOT.jar")
//
//    sc.setLogLevel("WARN")
//    val rdd = sc.textFile("file:///e://student.csv")
////    rdd.foreach(println(_))
//   // rdd.repartition(4).saveAsTextFile("file:///e://students")
//
//    println(rdd.id)
//    println(rdd.name)
//
//    val value = sc.textFile("file:///e://students")
//    value.foreach(println(_))
//
//    val df = rdd.map(
//      _.split(",")
//    ).map(x => People(x(0).trim.toInt, x(1).trim, x(2).trim.toInt)).toDF
//
//    println("----------------------")
//    df.createOrReplaceTempView("t2")
//    df.sqlContext.sql("select age,id,name from t2").show()
//
//
//  }
//
//}
