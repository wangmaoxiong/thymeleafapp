package com.wmx.thymeleafapp.controller;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/5/6 19:49
 */
@Controller
public class SystemController {

    /**
     * 自定义日期转换器
     * Spring3.0 之前是会自动转换的，但是 3.0 之后需要程序员自己转换
     * 直接将 @InitBinder... 注解的方法放置在 @Controller 中即可
     *
     * @param dataBinder
     */
    @InitBinder
    public void initBind(WebDataBinder dataBinder) {
        /**指定日期格式*/
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        /**
         * 指定日期/时间解析是否不严格
         * lenient - 为 true 时，解析过程是不严格的
         */
        dateFormat.setLenient(true);
        /**
         * Date.class：表示这是注册的是日期类型
         * new CustomDateEditor(dateFormat, true):true 表示允许为空
         */
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


    /**
     * 未自定义日期格式化时，使用 "/" 分割：http://localhost:8080/system/addData?id==1000&birthday=1993/08/25 19:08:09
     * 自定义日期格式化时，使用自定义的格式分割：http://localhost:8080/system/addData?id==1000&birthday=1993-08-25 19:18:19
     * http://localhost:8080/system/addData?id==1000&birthday=19930825 19:18:19
     *
     * @param id
     * @param birthday 如 1993/08/25 08:12:22
     * @return
     */
    @ResponseBody
    @GetMapping("system/addData")
    public String addData(@RequestParam String id, @RequestParam Date birthday) {
        System.out.println("id=" + id + ", birthday=" + birthday);
        JsonNodeFactory nodeFactory = JsonNodeFactory.instance;
        ObjectNode objectNode = nodeFactory.objectNode();
        objectNode.put("code", 200);
        objectNode.put("msg", "success");
        ArrayNode arrayNode = objectNode.putArray("data");
        arrayNode.add(id);
        arrayNode.add(birthday.toString());
        return objectNode.toString();
    }

    @ResponseBody
    @GetMapping("system/test")
    public String test() {
        System.out.println(1 / 0);
        return "xx";
    }
}
