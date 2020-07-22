package com.sk.sparksql.dataframe

import org.apache.spark.sql.types.{StringType, StructField, StructType}

object SchemaDemo {
  def main(args: Array[String]): Unit = {
    val schema = StructType(List(StructField("name",StringType,false)))  //可否为空
  }
}
