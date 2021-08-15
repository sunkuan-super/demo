package com.sk.mybatis.dao;

import com.sk.mybatis.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title: UserDao
 * @Package: com.practice.mybatis.dao
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/23 - 11:54
 */
@Mapper
public interface UserDao {
    public List<User> listUsers();
}
