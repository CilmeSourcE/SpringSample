package com.example.demo.login.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    /** ラジオボタンのマップ */
    private Map<String, String> radioMarriage;

    /**
     * ラジオボタンの初期処理
     * @return ラジオボタンのマップ
     */
    private Map<String, String> initRadioMarriage() {
        Map<String, String> radio = new LinkedHashMap<>();

        radio.put("既婚", "true");
        radio.put("未婚", "false");

        return radio;
    }

    /**
     * ホーム画面へ遷移時のコントローラー
     * @param model
     * @return ホーム画面
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHome(Model model) {

        // コンテンツ部分にユーザー一覧を表示するための文字列を登録
        model.addAttribute("contents", "login/home :: home_contents");

        return "login/homeLayout";
    }

    /**
     * ログアウトボタン押下時のコントローラー
     * @return ログイン画面
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String postLogout() {

        return "redirect:/login";
    }

    /**
     * ユーザー一覧画面へ遷移時のコントローラー
     * @param model
     * @return ユーザー一覧画面
     */
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public String getUserList(Model model) {

        model.addAttribute("contents", "login/userList :: userList_contents");

        // ユーザー一覧情報の取得・設定
        List<User> userList = userService.selectAll();
        model.addAttribute("userList", userList);

        // レコード数の取得・設定
        int count = userService.count();
        model.addAttribute("userListCount", count);

        return "login/homeLayout";
    }

    /**
     * ユーザー詳細画面へ遷移時のコントローラー
     *
     * @param form
     * @param model
     * @param userId
     * @return ユーザー詳細画面
     */
    @RequestMapping(value = "/userDetail/{id:.+}", method = RequestMethod.GET)
    public String getUserDetail(@ModelAttribute SignupForm form, Model model, @PathVariable("id") String userId) {

        // コンソール出力(デバッグ)
        System.out.println("userId=" + userId);

        model.addAttribute("contents", "login/userDetail :: userDetail_contents");

        radioMarriage = initRadioMarriage();

        model.addAttribute("radioMarriage", radioMarriage);

        if (userId == null || userId.equals("")) {
            // userIdが空であれば、処理中断
            return "login/homeLayout";
        }

        User user = userService.selectOne(userId);

        //user情報をformへ設定
        form.setUserId(user.getUserId());
        form.setPassword(user.getPassword());
        form.setUserName(user.getUserName());
        form.setBirthDay(user.getBirthDay());
        form.setAge(user.getAge());
        form.setMarriage(user.isMarriage());

        model.addAttribute("signupForm", form);

        return "login/homeLayout";
    }

    /**
     * ユーザー詳細画面から更新処理時のコントローラー
     * @param form
     * @param model
     * @return ユーザー一覧画面
     */
    @RequestMapping(value = "/userDetail", params = "update", method = RequestMethod.POST)
    public String postUserDetailUpdate(@ModelAttribute SignupForm form, Model model) {

        System.out.println("更新ボタンの処理");

        User user = new User();

        // formの情報をuserに設定する
        user.setUserId(form.getUserId());
        user.setPassword(form.getPassword());
        user.setUserName(form.getUserName());
        user.setBirthDay(form.getBirthDay());
        user.setAge(form.getAge());
        user.setMarriage(form.isMarriage());

        try {
            // 更新処理
            boolean result = userService.updateOne(user);

            if (result) {
                model.addAttribute("result", "更新成功");
            } else {
                model.addAttribute("result", "更新失敗");
            }

        } catch (DataAccessException e) {
            model.addAttribute("result", "更新失敗(トランザクションテスト)");
        }

        // ユーザー一覧画面へ遷移する
        return getUserList(model);
    }

    /**
     * ユーザー詳細画面から削除処理時のコントローラー
     * @param form
     * @param model
     * @return ユーザー一覧画面
     */
    @RequestMapping(value = "/userDetail", params = "delete", method = RequestMethod.POST)
    public String postUserDetailDelete(@ModelAttribute SignupForm form, Model model) {

        System.out.println("削除ボタンの処理");

        boolean result = userService.deleteOne(form.getUserId());

        if (result) {
            model.addAttribute("result", "削除成功");
        } else {
            model.addAttribute("result", "削除失敗");
        }

        return getUserList(model);
    }

    /**
     * CSV出力ボタン押下時のコントローラー
     * @param model
     * @return
     */
    @RequestMapping(value = "/userList/csv", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getUserListCsv(Model model) {

        // ユーザー情報を全件取得し、CSVをサーバーに保存する
        userService.userCsvOut();

        byte[] bytes = null;

        try {
            // サーバーに保存されているファイルをbyte配列で取得する
            bytes = userService.getFile("sample.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }

        //HTTPヘッダーの設定
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/csv; charset=UTF-8");

        headers.setContentDispositionFormData("filename", "sample.csv");

        // sample.csvを返す
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    /**
     * Admin画面へ遷移時のコントローラー
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdmin(Model model) {

        model.addAttribute("contents", "login/admin :: admin_contents");

        return "login/homeLayout";
    }
}
