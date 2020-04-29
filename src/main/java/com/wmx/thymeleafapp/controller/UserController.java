package com.wmx.thymeleafapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/29 16:31
 */
@Controller
public class UserController {

    /**
     * http://localhost:8080/user/toHome
     *
     * @param model
     * @return
     */
    @GetMapping("/user/toHome")
    public String toHome(Model model) {
        //向页面返回的数据
        model.addAttribute("code", 200);
        model.addAttribute("msg", "管理员向你表示祝贺！");
        //自动跳转到默认的 classpath:/templates/home.html 页面
        return "home";
    }
}
