package com.wmx.thymeleafapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.wmx.thymeleafapp.pojo.User;
import com.wmx.thymeleafapp.utils.FileWmxUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.Date;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/5/7 9:12
 */
@Controller
public class ExampleController {

    /**
     * 跳转到修改页面，get 请求： http://localhost:8080/example/httpPut
     *
     * @return
     */
    @GetMapping("example/httpPut")
    public String toPutPage(Model model) {
        //待修改人员数据
        User user = new User(9527, "华安", 18990.98F, "本科", new Date());

        //学历下拉框数据
        String[] educationArr = {"研究生及以上", "本科", "大专", "高中", "初中及以下"};

        //往前端传递数据.
        model.addAttribute("user", user);
        model.addAttribute("educationArr", educationArr);
        return "examples/httpPut";
    }

    /**
     * 修改提交时调用此接口, put 请求:  http://localhost:8080/example/httpPut
     *
     * @param user
     * @return
     */
    @PutMapping("example/httpPut")
    @ResponseBody
    public String httpPut(User user) {
        //将数据返回给页面，不操作数据.
        String valueAsString = "{}";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            valueAsString = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return valueAsString;
    }


    /**
     * http://localhost:8080/example/zTree
     * 跳转到 zTree.html 页面
     *
     * @return
     */
    @GetMapping("example/zTree")
    public String toZTree() {
        return "examples/zTree";
    }

    /**
     * http://localhost:8080/example/standardData
     * 跳转到 standardData.html zTree 标准数据展示页面
     *
     * @return
     */
    @GetMapping("example/standardData")
    public String toStandardData() {
        return "examples/standardData";
    }

    /**
     * http://localhost:8080/example/getStandardData
     * 用于 standardData.html zTree 标准数据展示页面获取 json 数据.
     * @return
     */
    @GetMapping("example/getStandardData")
    @ResponseBody
    public String getStandardData() {
        File rootFile = new File("C:\\wmx\\document\\验收文档 20200429");
        JsonArray jsonArray = FileWmxUtils.fileTreeByDirPath(rootFile, 3);
        return jsonArray.toString();
    }

}
