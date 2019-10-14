package com.example.demo;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@RunWith(SpringRunner.class)
// ↓Springのモックを使用するために必要なアノテーション
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * ログイン画面の表示テスト
     * @throws Exception
     */
    @Test
    public void ログイン画面表示() throws Exception {

        // 画面表示内容の確認

        // ログイン画面をGET
        mockMvc.perform(get("/login"))
                // HTTPリクエストが正常終了したか
                .andExpect(status().isOk())
                // ログイン画面に「ユーザーID」という文字列が表示されているか
                .andExpect(content().string(containsString("ユーザーID")));
    }
}
