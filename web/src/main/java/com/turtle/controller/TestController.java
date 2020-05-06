package com.turtle.controller;

import com.turtle.service.FeignClientService;
import com.turtle.vo.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 测试请求数据
 * @author: fuzongle
 * @create: 2020-01-18 11:07
 **/
@RestController
public class TestController {

    @Autowired
    private FeignClientService feignClientService;

    @RequestMapping("/A")
    public ResultBody testRequest(int mile) throws InterruptedException {
        return feignClientService.feignTest(mile);
    }
}
