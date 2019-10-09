package com.example.demo.login.domain.repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;

public class UserRowCallbackHandler implements RowCallbackHandler {

    @Override
    public void processRow(ResultSet rs) throws SQLException {
        try {

            // 書き込むファイルの準備
            File file = new File("sample.csv");
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);

            do {
                // 取得結果を文字列結合し、格納
                String str = rs.getString("user_id") + ", "
                        + rs.getString("password") + ", "
                        + rs.getString("user_name") + ", "
                        + rs.getDate("birthday") + ", "
                        + rs.getInt("age") + ", "
                        + rs.getBoolean("marriage") + ", "
                        + rs.getString("role");

                // ファイルへ書き込み、改行
                bw.write(str);
                bw.newLine();

            } while (rs.next());

            // 強制書き込み&クローズ
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
            throw new SQLException(e);
        }
    }
}
