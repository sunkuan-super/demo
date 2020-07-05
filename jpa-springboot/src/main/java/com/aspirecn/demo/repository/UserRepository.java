package com.aspirecn.demo.repository;

import com.aspirecn.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Title: UserRepository
 * @Package: com.aspirecn.demo.repository
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/23 - 14:12
 */
public interface UserRepository extends JpaRepository<User,Long> {
    public List<User> findAll();
 }
