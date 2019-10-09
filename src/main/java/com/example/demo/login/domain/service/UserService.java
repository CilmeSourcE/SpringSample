package com.example.demo.login.domain.service;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

@Transactional
@Service
public class UserService {

    @Autowired
    @Qualifier("UserDaoJdbcImpl")
    UserDao dao;

    /**
     * 1件追加処理
     * @param user
     * @return 処理結果 正常:true, 異常:false
     */
    public boolean insert(User user) {
        int rowNumber = dao.insertOne(user);

        boolean result = false;

        if (rowNumber > 0) {
            result = true;
        }

        return result;
    }

    /**
     * レコード数の取得
     * @return レコード数
     */
    public int count() {

        return dao.count();
    }

    /**
     * 全レコード取得
     * @return
     */
    public List<User> selectAll() {
        return dao.selectAll();
    }

    /**
     * レコードを1件取得
     * @param userId
     * @return User
     */
    public User selectOne(String userId) {
        return dao.selectOne(userId);
    }

    /**
     * レコード更新
     *
     * @param user
     * @return 処理結果 正常:true, 異常:false
     */
    public boolean updateOne(User user) {
        int rowNumber = dao.updateOne(user);

        boolean result = false;

        if (rowNumber > 0) {
            result = true;
        }

        return result;
    }

    /**
     * レコード削除処理
     * @param userId
     * @return
     */
    public boolean deleteOne(String userId) {
        int rowNumber = dao.deleteOne(userId);

        boolean result = false;

        if (rowNumber > 0) {
            result = true;
        }

        return result;
    }

    /**
     * CSV出力
     * @throws DataAccessException
     */
    public void userCsvOut() throws DataAccessException {

        // CSV出力
        dao.userCsvOut();
    }

    /**
     * サーバーに保存されているファイルを取得し、byte配列に変換する
     * @param fileName
     * @return
     * @throws IOException
     */
    public byte[] getFile(String fileName) throws IOException {

        // ファイルシステムの取得
        FileSystem fs = FileSystems.getDefault();

        // ファイルの取得
        Path p = fs.getPath(fileName);

        // ファイルをbyte配列に変換
        byte[] bytes = Files.readAllBytes(p);

        return bytes;
    }
}
