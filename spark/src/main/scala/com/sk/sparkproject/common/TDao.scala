package com.sk.sparkproject.common

import com.sk.sparkproject.util.EnvUtils

/**
 * @author: create by sunkuan
 * @Description:
 * @date: 2022/5/8 - 18:46
 */
trait TDao {
  def readFile(path : String)= {
    EnvUtils.get().textFile(path)
  }
}
