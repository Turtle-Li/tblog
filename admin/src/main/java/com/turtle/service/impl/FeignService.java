package com.turtle.service.impl;

import com.turtle.api.FeignApi;
import com.turtle.service.RoleService;
import com.turtle.vo.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

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

    @Autowired
    private RoleService roleService;

    @Override
    public ResultBody feignTest(int mile) throws InterruptedException {
        Thread.sleep(mile);
        return ResultBody.success();
    }
}
