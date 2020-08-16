package com.sk.sparksql.datasource

import org.apache.spark.sql.SparkSession

/**
 * @Title: ParquetRead
 * @Package: com.sk.sparksql.datasource
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/7/27 - 12:14
 */
/**
 * Parquet的特性（重要）
 *
 * Parquet是列式存储格式的一种文件类型，列式存储有以下的核心：
 * A. 可以跳过不符合条件的数据，只读取需要的数据，降低IO数据量
 * B. 压缩编码可以降低磁盘存储空间。由于同一列的数据类型是一样的，可以使用更高效的压缩编码进一步节约存储空间
 * C. 只读取需要的量，支持向量运算，能够获取更好的扫描性能
 */
object ParquetRead {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName(this.getClass.getCanonicalName.take(this.getClass.getCanonicalName.length-1))
      .master("local[*]")
      .getOrCreate()
    spark.sparkContext.setLogLevel("WARN")

    val studentDF = spark.read.load("spark/file/student.parquet")
    studentDF.show()
  }
}
