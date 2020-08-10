package com.sk.hbase

import java.util.Collections

import com.sk.hdfs.KerberosInit
import org.apache.hadoop.hbase.{Cell, CellUtil, HBaseConfiguration, TableName}
import org.apache.hadoop.hbase.client.{Connection, ConnectionFactory, Get, Result, ResultScanner, Scan, Table}
import org.apache.hadoop.hbase.util.Bytes

/**
 * @Title: HBaseHelloWorld
 * @Package: com.sk.hbase
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/8/10 - 9:56
 */
object HBaseHelloWorld {

  def getConn(): Connection = {
    //KerberosInit.initKerberosHBase()
    // 获取配置、创建连接
    val hBaseConf = HBaseConfiguration.create()
    println(1)
    println(s"HBaseConf = $hBaseConf")
    println(2)
    val conn = ConnectionFactory.createConnection(hBaseConf)
    println(3)
    println(conn)
    conn
  }

  /**
   * 获取原始数据
   */
  def getNoDealData(): Unit = {
    val tableName = "kafka_offset"
    val conn = getConn()

    val table: Table = conn.getTable(TableName.valueOf(tableName))
    val scan = new Scan()
    val resultScanner = table.getScanner(scan)

    import scala.collection.JavaConversions._
    for (result <- resultScanner) {

      val cells: Array[Cell] = result.rawCells()
      for (cell <- cells) {
        // 得到rowkey
        println(s"行键：${Bytes.toString(CellUtil.cloneRow(cell))}")
        // 得到列族
        println(s"列族：${Bytes.toString(CellUtil.cloneFamily(cell))}")

        println(s"列：${Bytes.toString(CellUtil.cloneQualifier(cell))}")
        println(s"值：${Bytes.toString(CellUtil.cloneValue(cell))}")

      }
    }


  }

  def main(args: Array[String]): Unit = {
    getNoDealData()
    // Hbase表模式管理器

    // 如果表不存在先删除

    // 创建表

    // 创建列族 offsets

    // 插入两条记录


  }
}
