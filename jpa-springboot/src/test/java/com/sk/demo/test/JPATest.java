package com.sk.demo.test;

import com.sk.demo.domain.User;
import com.sk.demo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Title: JPATest
 * @Package: com.demo.demo.test
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/23 - 14:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JPATest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void test(){
        List<User> userRepositoryAll = userRepository.findAll();
        System.out.println(userRepositoryAll);
    }
}
