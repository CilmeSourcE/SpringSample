package com.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.login.domain.model.GroupOrder;
import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;

@Controller
public class SignUpController {

    @Autowired
    private UserService userService;

    private Map<String, String> radioMarriage;

    private Map<String, String> initRadioMarriage() {

        Map<String, String> radio = new LinkedHashMap<>();
        radio.put("既婚", "true");
        radio.put("未婚", "false");

        return radio;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String getSignUp(@ModelAttribute SignupForm form, Model model) {

        radioMarriage = initRadioMarriage();

        model.addAttribute("radioMarriage", radioMarriage);

        return "login/signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String postSignup(@ModelAttribute @Validated(GroupOrder.class) SignupForm form, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            // データバインドに失敗した場合、GETリクエストを呼び出してユーザー登録画面に戻す
            return getSignUp(form, model);
        }

        System.out.println(form);

        User user = new User();
        // userにformの情報を設定する
        user.setUserId(form.getUserId());
        user.setPassword(form.getPassword());
        user.setUserName(form.getUserName());
        user.setBirthDay(form.getBirthDay());
        user.setAge(form.getAge());
        user.setMarriage(form.isMarriage());
        user.setRole("ROLE_GENERAL");

        // userの情報をm_userテーブルへ追加
        boolean result = userService.insert(user);

        // 登録結果判定
        if (result == true) {
            System.out.println("登録成功!");
        } else {
            System.out.println("登録失敗...");
        }

        return "redirect:/login";
    }

    /**
     * DataAccessException発生時の例外処理ハンドラー
     * @param ex
     * @param model
     * @return エラー画面
     */
    @ExceptionHandler(DataAccessException.class)
    public String dataAcceccExceptionHandler(DataAccessException ex, Model model) {

        model.addAttribute("error", "内部サーバーエラー(DB) : ExceptionHandler");
        model.addAttribute("message", "SignUpControllerでDataAccessExceptionが発生しました");
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
        return "error";
    }

    /**
     * Exception発生時の例外処理ハンドラー
     * @param e
     * @param model
     * @return エラー画面
     */
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model) {

        model.addAttribute("error", "内部サーバーエラー : ExceptionHandler");
        model.addAttribute("message", "SignUpControllerでExceptionが発生しました");
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
        return "error";
    }
}
