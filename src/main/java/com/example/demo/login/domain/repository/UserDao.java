package com.example.demo.login.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.login.domain.model.User;

public interface UserDao {

    /**
     * Userテーブルの件数を取得する。
     * @return
     * @throws DataAccessException
     */
    public int count() throws DataAccessException;

    /**
     * Userテーブルにデータを1件挿入する。
     * @param user
     * @return
     * @throws DataAccessException
     */
    public int insertOne(User user) throws DataAccessException;

    /**
     * Userテーブルから1件データを取得する。
     * @param userId
     * @return
     * @throws DataAccessException
     */
    public User selectOne(String userId) throws DataAccessException;

    /**
     * Userテーブルのデータを全件取得する。
     * @param userId
     * @return
     * @throws DataAccessException
     */
    public List<User> selectAll() throws DataAccessException;

    /**
     * Userテーブルのデータを1件更新する。
     * @param user
     * @return
     * @throws DataAccessException
     */
    public int updateOne(User user) throws DataAccessException;

    /**
     * Userテーブルのデータを1件削除する。
     * @param userId
     * @return
     * @throws DataAccessException
     */
    public int deleteOne(String userId) throws DataAccessException;

    /**
     * SQL取得結果をサーバーにCSVで保存する。
     * @throws DataAccessException
     */
    public void userCsvOut() throws DataAccessException;
}
