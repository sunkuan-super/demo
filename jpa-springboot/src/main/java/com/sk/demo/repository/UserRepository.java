package com.sk.demo.repository;

import com.sk.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Title: UserRepository
 * @Package: com.demo.demo.repository
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/23 - 14:12
 */
public interface UserRepository extends JpaRepository<User,Long> {
    public List<User> findAll();
 }
