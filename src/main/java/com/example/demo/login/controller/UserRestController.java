package com.example.demo.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.RestService;

@RestController
public class UserRestController {

    @Autowired
    RestService service;

    /**
     * 全件取得時のRESTコントローラー
     * @return
     */
    @RequestMapping(value = "/rest/get", method = RequestMethod.GET)
    public List<User> getUserAll() {

        return service.selectAll();
    }

    /**
     * 1件取得時のRESTコントローラー
     * @param userId
     * @return
     */
    @RequestMapping(value = "/rest/get/{id:.+}", method = RequestMethod.GET)
    public User getUserOne(@PathVariable("id") String userId) {

        return service.selectOne(userId);
    }

    /**
     * 一件登録時のRESTコントローラー
     * @param user
     * @return
     */
    @RequestMapping(value = "/rest/insert", method = RequestMethod.POST)
    public String postUserOne(@RequestBody User user) {

        boolean result = service.insertOne(user);

        String str = "";

        // 追加結果
        if (result) {
            // ok
            str = "{\"result\":\"ok\"}";
        } else {
            // error
            str = "{\"result\":\"error\"}";
        }

        // 結果用の文字列を返す
        return str;
    }

    /**
     * ユーザー情報更新時のRESTコントローラー
     * @param user
     * @return
     */
    @RequestMapping(value = "/rest/update", method = RequestMethod.PUT)
    public String putUserOne(@RequestBody User user) {

        boolean result = service.update(user);

        String str = "";

        // 更新結果
        if (result) {
            // ok
            str = "{\"result\":\"ok\"}";
        } else {
            // error
            str = "{\"result\":\"error\"}";
        }

        // 結果用の文字列を返す
        return str;
    }

    /**
     * ユーザー削除時のRESTコントローラー
     * @param userId
     * @return
     */
    @RequestMapping(value = "/rest/delete/{id:.+}", method = RequestMethod.DELETE)
    public String deleteUserOne(@PathVariable("id") String userId) {

        boolean result = service.delete(userId);

        String str = "";

        // 削除結果
        if (result) {
            // ok
            str = "{\"result\":\"ok\"}";
        } else {
            // error
            str = "{\"result\":\"error\"}";
        }

        // 結果用の文字列を返す
        return str;
    }
}
