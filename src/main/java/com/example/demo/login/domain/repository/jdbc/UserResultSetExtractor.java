package com.example.demo.login.domain.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.example.demo.login.domain.model.User;

public class UserResultSetExtractor implements ResultSetExtractor<List<User>> {

    @Override
    public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {

        List<User> userList = new ArrayList<User>();

        while (rs.next()) {

            User user = new User();

            // 取得結果をuserへ設定する
            user.setUserId(rs.getString("user_id"));
            user.setPassword("password");
            user.setUserName("user_name");
            user.setBirthDay(rs.getDate("birthday"));
            user.setAge(rs.getInt("age"));
            user.setMarriage(rs.getBoolean("marriage"));
            user.setRole(rs.getString("role"));

            userList.add(user);
        }

        if (userList.isEmpty()) {
            // 1件も取得されなかった場合は例外を発生させる
            throw new EmptyResultDataAccessException(1);
        }

        return userList;
    }
}
