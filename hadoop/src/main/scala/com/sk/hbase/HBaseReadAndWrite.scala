package com.sk.hbase

import com.sk.hdfs.KerberosInit
import org.apache.hadoop.hbase.{HBaseConfiguration, HColumnDescriptor, HTableDescriptor, TableName}
import org.apache.hadoop.hbase.client.{Connection, ConnectionFactory, HTable, Put, Scan, Table}
import org.apache.hadoop.hbase.util.Bytes
import scala.collection.JavaConversions._
import scala.util.control.Breaks._

object HBaseReadAndWrite {
  def main(args: Array[String]): Unit = {
    KerberosInit.initKerberosHBase()
    // 1、获取配置、创建连接、表模式管理器
    val hbaseConf = HBaseConfiguration.create()
    println(hbaseConf)
    val conn = ConnectionFactory.createConnection(hbaseConf)
    val admin = conn.getAdmin

    //2、创建表(如果存在先删除) (创建Hbase表模式、创建列簇offsets)
    val name = "sunkuan"
    val tableName: TableName = TableName.valueOf(name)
    if (admin.tableExists(tableName)) {
      println("表已经存在")
      admin.disableTable(tableName)
      admin.deleteTable(tableName)
      println("表删除成功")
      //admin.truncateTable(tableName,false)
    }

    // 创建表描述
    val hTableDescriptor = new HTableDescriptor(tableName)
    hTableDescriptor.addFamily(new HColumnDescriptor("offsets").setTimeToLive(295000))
    admin.createTable(hTableDescriptor)

    // 3、插入记录 rowkey
    val key0 = "myoffset0::000000000"
    val family = "offsets".getBytes()
    val key1 = "myoffset0::000001000"

    val table: Table = conn.getTable(tableName)
    val put0 = new Put(key0.getBytes())
    put0.addColumn(family, "0".getBytes, "8888".getBytes())
    put0.addColumn(family, "1".getBytes, "100".getBytes())
    put0.addColumn(family, "2".getBytes, "200".getBytes())
    put0.addColumn(family, "3".getBytes, "300".getBytes())
    put0.addColumn(family, "4".getBytes, "400".getBytes())
    put0.addColumn(family, "5".getBytes, "9999".getBytes())
    table.put(put0)

    val put1 = new Put(key1.getBytes())
    put1.addColumn(family, "0".getBytes, "888888".getBytes())
    put1.addColumn(family, "1".getBytes, "100".getBytes())
    put1.addColumn(family, "2".getBytes, "200".getBytes())
    put1.addColumn(family, "3".getBytes, "300".getBytes())
    put1.addColumn(family, "4".getBytes, "400".getBytes())
    put1.addColumn(family, "5".getBytes, "999999".getBytes())

    table.put(put1)
    println("success")

    read(table, family, conn)

  }

  def read(tableOffsets: Table, family: Array[Byte], conn: Connection): Unit = {
    val startRowKey = "myoffset0::999999999"
    val endRowKey = "myoffset0::0"

    val scan = new Scan()
    val scanner = tableOffsets.getScanner(
      scan.withStartRow(startRowKey.getBytes()).withStopRow(endRowKey.getBytes()).setReversed(true))

    /*if(scanner != null) {
      scanner.foreach(result => {
        var limitInd = 0;
        breakable{
          for (i <- result.getRow.indices){
            val rowkey = result.getRow
            if(rowkey(i) == ':'){
              limitInd = i
              break()
            }
          }
        }

        println(limitInd)
      })
    }*/
    scanner.foreach(result =>{
      val cellsSize = result.listCells().size()
      println(s"result.listCells().size() = ${cellsSize}")

      val n1 = result.getValue(family, "0".getBytes())
      val n2 = result.getValue(family, "1".getBytes())
      val n3 = result.getValue(family, "2".getBytes())
      val n4 = result.getValue(family, "3".getBytes())
      val n5 = result.getValue(family, "4".getBytes())
      val n6 = result.getValue(family, "5".getBytes())

      println(s"n1 = $n1")
      println(s"n6 = $n6")
      println(s"n1 = ${n1.mkString("^")}")
      println(s"n1 = ${Bytes.toString(n1)}")
      println(s"n6 = ${Bytes.toString(n6)}")
    })
//    val result = scanner.next()
//
//    val cellsSize = result.listCells().size()
//    println(s"result.listCells().size() = ${cellsSize}")
//
//    val n1 = result.getValue(family, "0".getBytes())
//    val n2 = result.getValue(family, "1".getBytes())
//    val n3 = result.getValue(family, "2".getBytes())
//    val n4 = result.getValue(family, "3".getBytes())
//    val n5 = result.getValue(family, "4".getBytes())
//    val n6 = result.getValue(family, "5".getBytes())
//
//    println(s"n1 = $n1")
//    println(s"n6 = $n6")
//    println(s"n1 = ${n1.mkString("^")}")
//    println(s"n1 = ${Bytes.toString(n1)}")
//    println(s"n6 = ${Bytes.toString(n6)}")

    conn.close()
  }
}
