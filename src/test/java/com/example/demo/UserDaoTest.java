package com.example.demo;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.repository.UserDao;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserDaoTest {

    @Autowired
    @Qualifier("UserDaoJdbcImpl")
    UserDao dao;

    /**
     * カウントメソッドのテスト1
     */
    @Test
    public void countTest1() {

        // 結果が2であることをテスト
        assertEquals(dao.count(), 2);
    }
}
