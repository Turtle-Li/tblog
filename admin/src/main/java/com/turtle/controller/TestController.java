package com.turtle.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: fuzongle
 * @Date: 2020/5/16 18:17
 * @Description:  测试过后删除
 */
@RestController
@RequestMapping("/test")
@Api(tags = {"测试是否改变数据"})
public class TestController {


    public String test(){
        return  "测试数据";
    }
}