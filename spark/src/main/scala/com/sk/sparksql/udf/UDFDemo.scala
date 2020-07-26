package com.sk.sparksql.udf

import org.apache.spark.sql.{Row, SparkSession}

/**
 * @Title: UDFDemo
 * @Package: com.sk.sparksql.udf
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/7/26 - 22:40
 */
object UDFDemo {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("UDFDemo")
      .master("local[*]")
      .getOrCreate()
    spark.sparkContext.setLogLevel("WARN")

    val data = List(("scala", "author1"), ("spark", "author2"), ("hadoop", "author3"), ("hive", "author4"), ("strom", "author5"), ("kafka", "author6"))
    val df = spark.createDataFrame(data).toDF("title", "author")
    df.createTempView("books")

    spark.sql("""select * from books""").show(false)

    //定义函数并注册
    def len1(bookTitle: String): Int = bookTitle.length

    spark.udf.register("len1",len1 _)  // 注册的时候函数名可以随便叫

    /**
     * udf既可以在select语句中
     */
    spark.sql(
      """select title,len1(title),author,len1(author)
        |from books""".stripMargin).show(false)

    /**
     * udf也可以在where语句中
     */
    spark.sql(
      """
        |select title,author
        |from books
        |where len1(title) > 5
        |""".stripMargin).show()

    /**
     * UDF可以在DataFrame、Dataset的API中使用
     */
    df.where("len1(title) > 4").show()

    import spark.implicits._
    // 不能通过编译
    //df.where(len1($"title") > 5)

    // 能通过编译，但不能执行
    //df.select("len1(title)").show

    // 不能通过编译
    //df.select(len1($"title")).show

    // 如果要在DSL语法中使用$符号包裹字符串表示一个Column，需要用udf方法来接收函数。这种函数无需注册
    import org.apache.spark.sql.functions._
    val len2 = udf((bookTitle:String) => bookTitle.length)

    df.where(len2($"title") > 4).show()
    df.select(len2($"title")).show()
    // 不使用UDF
    df.map{case Row(title: String, author: String) => (title, author, title.length)}.toDF("title","author","le").show

    spark.stop()


  }
}
