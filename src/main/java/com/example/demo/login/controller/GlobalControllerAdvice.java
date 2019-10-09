package com.example.demo.login.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Component
public class GlobalControllerAdvice {

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
