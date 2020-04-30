package com.wmx.thymeleafapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/29 17:15
 */
@Controller
public class PhotoController {

    /**
     * http://localhost:8080/photo/list
     * @return
     */
    @GetMapping("photo/list")
    public String toPhoto() {
        return "photo";
    }
}
