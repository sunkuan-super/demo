package com.aspirecn.mybatis.dao;

import com.aspirecn.mybatis.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title: UserDao
 * @Package: com.aspirecn.mybatis.dao
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/23 - 11:54
 */
@Mapper
public interface UserDao {
    public List<User> listUsers();
}
