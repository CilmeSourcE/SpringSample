package com.example.demo.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin() {

        return "login/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String postLogin() {

        return "redirect:/home";
    }
}
