package com.sk.sparkproject.service


import com.sk.sparkproject.common.TService
import com.sk.sparkproject.dao.WordCountDao
import org.apache.spark.rdd.RDD

/**
 * @author: create by sunkuan
 * @Description: 服务层
 * @date: 2022/5/8 - 17:29
 */
class WordCountService extends TService{

  private val wordCountDao = new WordCountDao

  def dataAnalysis(): Array[(String, Int)] = {
    val lines: RDD[String] = wordCountDao.readFile("spark/file/words.txt")

    val words = lines.flatMap(_.split(" "))
    val wordToOne: RDD[(String, Int)] = words.map(word => (word, 1))
    val wordToSum: RDD[(String, Int)] = wordToOne.reduceByKey(_ + _)

    val array: Array[(String, Int)] = wordToSum.collect()
    array
  }
}
