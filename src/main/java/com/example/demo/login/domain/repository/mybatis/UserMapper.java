package com.example.demo.login.domain.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.login.domain.model.User;

@Mapper
public interface UserMapper {

    /**
     * 登録用メソッド
     * @param user
     * @return
     */
    @Insert("INSERT INTO m_user ( "
            + "user_id, "
            + "password, "
            + "user_name, "
            + "birthday, "
            + "age, "
            + "marriage, "
            + "role"
            + ") VALUES ( "
            + "#{userId}, "
            + "#{password}, "
            + "#{userName}, "
            + "#{birthDay}, "
            + "#{age}, "
            + "#{marriage}, "
            + "#{role} )")
    public boolean insert(User user);

    /**
     * 一件取得用メソッド
     * @param userId
     * @return
     */
    @Select("SELECT user_id AS userId, "
            + "password, "
            + "user_name AS userName, "
            + "birthday AS birthDay, "
            + "age, "
            + "marriage, "
            + "role "
            + "FROM m_user "
            + "WHERE user_id = #{userId}")
    public User selectOne(String userId);

    /**
     * 全件取得メソッド
     * @return
     */
    @Select("SELECT user_id AS userId, "
            + "password, "
            + "user_name AS userName, "
            + "birthday AS birthDay, "
            + "age, "
            + "marriage, "
            + "role "
            + "FROM m_user ")
    public List<User> selectAll();

    /**
     * 更新用メソッド
     * @param userId
     * @return
     */
    @Update("UPDATE m_user SET "
            + "password = #{password}, "
            + "user_name = #{userName}, "
            + "birthday = #{birthDay}, "
            + "age = #{age}, "
            + "marriage = #{marriage}, "
            + "role = #{role} "
            + "WHERE user_id = #{userId}")
    public boolean updateOne(User user);

    /**
     * 削除用メソッド
     * @param userId
     * @return
     */
    @Delete("DELETE FROM m_user WHERE "
            + "user_id = #{userId}")
    public boolean deleteOne(String userId);
}
