package com.turtle.controller;

import com.turtle.service.FeignClientService;
import com.turtle.service.TestService;
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
    private TestService testService;

    @RequestMapping("/A")
    public ResultBody testA(int mile) throws InterruptedException {
        System.out.println("AAAAA");
        return testService.test(mile);
    }

    @RequestMapping("/B")
    public ResultBody testB(int mile) throws InterruptedException {
        System.out.println("BBBBB");
        return testService.test(mile);
    }

    @RequestMapping("/V")
    public ResultBody testC(int mile) throws InterruptedException {
        System.out.println("CCCCC");
        return testService.test(mile);
    }

}
