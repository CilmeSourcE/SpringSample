package com.example.demo.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;
import com.example.demo.login.domain.repository.UserRowCallbackHandler;

@Repository("UserDaoJdbcImpl")
public class UserDaoJdbcImpl implements UserDao {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public int count() throws DataAccessException {
        int count = jdbc.queryForObject("SELECT COUNT(*) FROM m_user", Integer.class);
        return count;
    }

    @Override
    public int insertOne(User user) {

        // パスワードの暗号化
        String password = passwordEncoder.encode(user.getPassword());

        int rowNumber = jdbc.update("INSERT INTO m_user ( "
                + "user_id, "
                + "password, "
                + "user_name, "
                + "birthday, "
                + "age, "
                + "marriage, "
                + "role) VALUES(?, ?, ?, ?, ?, ?, ?)",
                user.getUserId(),
                // 暗号化したパスワードを渡す
                password,
                user.getUserName(),
                user.getBirthDay(),
                user.getAge(),
                user.isMarriage(),
                user.getRole());

        return rowNumber;
    }

    @Override
    public User selectOne(String userId) throws DataAccessException {

        Map<String, Object> map = jdbc.queryForMap("SELECT * FROM m_user WHERE user_id = ?", userId);

        User user = new User();
        user.setUserId((String) map.get("user_id"));
        user.setPassword((String) map.get("password"));
        user.setUserName((String) map.get("user_name"));
        user.setBirthDay((Date) map.get("birthday"));
        user.setAge((Integer) map.get("age"));
        user.setMarriage((Boolean) map.get("marriage"));
        user.setRole((String) map.get("role"));
        return user;
    }

    @Override
    public List<User> selectAll() throws DataAccessException {

        List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM m_user");

        List<User> userList = new ArrayList<>();

        for (Map<String, Object> map : getList) {
            User user = new User();

            // 取得したデータをuserに設定する
            user.setUserId((String) map.get("user_id"));
            user.setPassword((String) map.get("password"));
            user.setUserName((String) map.get("user_name"));
            user.setBirthDay((Date) map.get("birthday"));
            user.setAge((Integer) map.get("age"));
            user.setMarriage((Boolean) map.get("marriage"));
            user.setRole((String) map.get("role"));

            userList.add(user);
        }

        return userList;
    }

    @Override
    public int updateOne(User user) throws DataAccessException {

        // パスワードの暗号化
        String password = passwordEncoder.encode(user.getPassword());

        int rowNumber = jdbc.update("UPDATE m_user SET "
                + "user_id = ?,"
                + "password = ?, "
                + "user_name = ?, "
                + "birthDay = ?, "
                + "age = ?, "
                + "marriage = ?"
                + "WHERE user_id = ?",
                user.getUserId(),
                // 暗号化したパスワードを渡す
                password,
                user.getUserName(),
                user.getBirthDay(),
                user.getAge(),
                user.isMarriage(),
                user.getUserId());

        //        // 成功時にわざと例外を発生させる
        //        if (rowNumber > 0) {
        //            throw new DataAccessException("トランザクションテスト") {
        //            };
        //        }

        return rowNumber;
    }

    @Override
    public int deleteOne(String userId) throws DataAccessException {

        int rowNumber = jdbc.update("DELETE FROM m_user WHERE user_id = ?", userId);

        return rowNumber;
    }

    @Override
    public void userCsvOut() throws DataAccessException {

        String sql = "SELECT * FROM m_user";

        //コールバック用インスタンス(UserRowCallbackHandler)作成
        UserRowCallbackHandler callbackHandler = new UserRowCallbackHandler();

        jdbc.query(sql, callbackHandler);
    }
}
