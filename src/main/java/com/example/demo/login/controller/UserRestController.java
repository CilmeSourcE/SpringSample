package com.example.demo.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
    @RequestMapping(value = "/rest/get/{id:.+}")
    public User getUserOne(@PathVariable("id") String userId) {

        return service.selectOne(userId);
    }
}
