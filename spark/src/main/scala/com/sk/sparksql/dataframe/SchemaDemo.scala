package com.sk.sparksql.dataframe

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object SchemaDemo {
  def main(args: Array[String]): Unit = {
    val schema = new StructType().
      add("name", "string", false).
      add("age", "integer", false).
      add("height", "integer", false)

    val schema2 = StructType(List(StructField("name", StringType, false),
      StructField("age", IntegerType, false), StructField("height", IntegerType, false))) //可否为空
  }
}
