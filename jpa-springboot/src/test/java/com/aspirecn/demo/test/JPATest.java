package com.aspirecn.demo.test;

import com.aspirecn.demo.domain.User;
import com.aspirecn.demo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Title: JPATest
 * @Package: com.aspirecn.demo.test
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
