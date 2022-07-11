package com.sk.sparkproject.controller

import com.sk.sparkproject.common.TController
import com.sk.sparkproject.service.WordCountService

/**
 * @author: create by sunkuan
 * @Description: 控制层
 * @date: 2022/5/8 - 17:28
 */
class WordCountController extends TController{
    private val wordCountService = new WordCountService

    def dispatch(): Unit ={
      val array = wordCountService.dataAnalysis()
      array.foreach(println(_))
    }

}
