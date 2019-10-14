package com.example.demo;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
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
        assertEquals(2, dao.count());
    }

    /**
     * カウントメソッドのテスト2
     * testdata.sql実行後にテスト
     */
    @Test
    @Sql("/testdata.sql")
    public void countTest2() {

        // testdata.sql実行後なので、結果が3であることをテスト
        assertEquals(3, dao.count());
    }
}
