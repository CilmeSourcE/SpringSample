package com.example.demo;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.login.domain.service.UserService;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // モックの戻り値設定
    @MockBean
    private UserService service;

    /**
     * ユーザーリスト画面の表示テスト
     * @throws Exception
     */
    @Test
    // ↓ログイン後でしか表示できない画面をテストするために必要
    @WithMockUser
    public void ユーザーリスト画面のユーザー件数のテスト() throws Exception {

        // モックの戻り値設定
        // UserServiceのcountメソッドの戻り値を10に設定する
        when(service.count()).thenReturn(10);

        // ユーザーリスト画面をGET
        mockMvc.perform(get("/userList"))
                // HTTPリクエストが正常終了したか
                .andExpect(status().isOk())
                // ユーザーリスト画面に「合計:10件」という文字列が表示されているか
                .andExpect(content().string(containsString("合計:10件")));
    }
}
