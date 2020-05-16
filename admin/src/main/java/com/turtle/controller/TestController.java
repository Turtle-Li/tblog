package com.turtle.controller;

import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: fuzongle
 * @Date: 2020/5/16 18:17
 * @Description:
 */
@RestController
public class TestController {



    public String test(){
        return  "测试数据";
    }
}