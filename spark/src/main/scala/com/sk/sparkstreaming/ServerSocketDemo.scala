package com.sk.sparkstreaming

import java.io.PrintWriter
import java.net.{ServerSocket, Socket}

import scala.util.Random

/**
 * @Title: ServerSocketDemo
 * @Package: com.sk.sparkstreaming
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/7/30 - 14:03
 */
object ServerSocketDemo {

  def main(args: Array[String]): Unit = {
    val server: ServerSocket = new ServerSocket(8888)
    val socket: Socket = server.accept()

    println("is linked " + socket.getInetAddress)
    val words = "hadoop spark hive flume hbase kafka impla hue".split("\\s+")
    val length = words.length
    val random = new Random()

    while (true) {
      //      val inputStream = socket.getInputStream
      //
      //      val bytes = new Array[Byte](1024)
      //      val len = inputStream.read(bytes)
      //      System.out.println(new String(bytes, 0, len))

      val outputStream = socket.getOutputStream
      val out = new PrintWriter(outputStream)
      out.println(words(random.nextInt(length)))
      out.flush()
      Thread.sleep(50)

    }
    socket.close
    server.close
  }
}
