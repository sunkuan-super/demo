package com.aspirecn.mybatis.test;

import com.aspirecn.mybatis.dao.UserDao;
import com.aspirecn.mybatis.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Title: UserDaoTest
 * @Package: com.aspirecn.mybatis.test
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/23 - 12:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void test(){
        List<User> userList = userDao.listUsers();
        System.out.println(userList);
    }
}
