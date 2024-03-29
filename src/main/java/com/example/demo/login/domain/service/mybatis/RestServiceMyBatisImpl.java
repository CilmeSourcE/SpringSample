package com.example.demo.login.domain.service.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.mybatis.UserMapper2;
import com.example.demo.login.domain.service.RestService;

@Transactional
@Service("RestServiceMyBatisImpl")
public class RestServiceMyBatisImpl implements RestService {

    @Autowired
    private UserMapper2 userMapper;

    @Override
    public boolean insertOne(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User selectOne(String userId) {
        return userMapper.selectOne(userId);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public boolean update(User user) {
        return userMapper.updateOne(user);
    }

    @Override
    public boolean delete(String userId) {
        return userMapper.deleteOne(userId);
    }
}
