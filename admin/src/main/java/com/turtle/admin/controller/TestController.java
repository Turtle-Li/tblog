package com.turtle.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 测试请求数据
 * @author: fuzongle
 * @create: 2020-01-18 11:07
 **/
@RestController
public class TestController {

    @RequestMapping("/A")
    public  String  testRequest(){
        return "正常访问请求!";
    }
}
