package com.turtle.service.impl;

import com.turtle.api.FeignApi;
import com.turtle.exception.UserAlertException;
import com.turtle.vo.ResultBody;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lijiayu
 * @date 2020/4/30
 * @description
 */
@Service
@ResponseBody
public class FeignService implements FeignApi {
    /**
     * 私有化，不允许其他方式调用
     */
    private FeignService(){}

    @Override

    public ResultBody feignTest(String name) throws InterruptedException {
        System.out.println("1111111");
        Thread.sleep(1000);
        return ResultBody.result("123");
    }
}
