package com.turtle.controller;

import com.turtle.dto.UserUpdateInfoParam;
import com.turtle.vo.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Auther: fuzongle
 * @Date: 2020/5/16 18:17
 * @Description:  测试过后删除
 */
@RestController
@RequestMapping("/test")
@Api(tags = {"测试是否改变数据"})
public class TestController {


    @PostMapping("/terst")
    @ApiOperation(value = "测试",notes = "测试")
    public void updateInfo(@RequestBody @Valid UserUpdateInfoParam userUpdateInfoParam){
    }

}