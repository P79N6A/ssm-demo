package com.fc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 访问控制器，测试视图解析器配置
 * @author SYSTEM on 2017/10/27.
 */
@Controller
public class IndexController {

    @RequestMapping("/home")
    public String home() {
        return "jsp/lottey";
    }

    @RequestMapping("/register")
    public String register() {
        return "jsp/register";
    }

}
