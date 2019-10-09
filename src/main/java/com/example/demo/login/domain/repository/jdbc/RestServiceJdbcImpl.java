package com.example.demo.login.domain.repository.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;
import com.example.demo.login.domain.service.RestService;

@Transactional
@Service
public class RestServiceJdbcImpl implements RestService {

    @Autowired
    @Qualifier("UserDaoJdbcImpl")
    UserDao dao;

    @Override
    public boolean insert(User user) {
        // TODO 自動生成されたメソッド・スタブ
        return false;
    }

    @Override
    public User selectOne(String userId) {
        return dao.selectOne(userId);
    }

    @Override
    public List<User> selectAll() {
        return dao.selectAll();
    }

    @Override
    public boolean update(String userId) {
        // TODO 自動生成されたメソッド・スタブ
        return false;
    }

    @Override
    public boolean delete(String userId) {
        // TODO 自動生成されたメソッド・スタブ
        return false;
    }

}