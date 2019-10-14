package com.example.demo.login.domain.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.login.domain.model.User;

@Mapper
public interface UserMapper2 {

    /**
     * 登録用メソッド
     * @param user
     * @return
     */
    public boolean insert(User user);

    /**
     * 一件取得用メソッド
     * @param userId
     * @return
     */
    public User selectOne(String userId);

    /**
     * 全件取得メソッド
     * @return
     */
    public List<User> selectAll();

    /**
     * 更新用メソッド
     * @param userId
     * @return
     */
    public boolean updateOne(User user);

    /**
     * 削除用メソッド
     * @param userId
     * @return
     */
    public boolean deleteOne(String userId);
}
