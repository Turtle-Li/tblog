package com.turtle.controller;

import com.turtle.vo.ResultBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lijiayu
 * @date 2020/5/6
 * @description
 */
@RestController
public class DefaultHystrixController {

    @RequestMapping("/default-fallback")
    public ResultBody defaultFallback(){
        return ResultBody.error("服务器异常！");
    }
}
