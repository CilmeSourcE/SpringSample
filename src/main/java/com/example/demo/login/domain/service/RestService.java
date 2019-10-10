package com.example.demo.login.domain.service;

import java.util.List;

import com.example.demo.login.domain.model.User;

public interface RestService {

    /**
     * 1件登録メソッド
     * @param user
     * @return
     */
    public boolean insertOne(User user);

    /**
     * 1件取得メソッド
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
     * 更新メソッド
     * @param user
     * @return
     */
    public boolean update(User user);

    /**
     * 1件削除メソッド
     * @param userId
     * @return
     */
    public boolean delete(String userId);
}
