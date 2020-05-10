package com.turtle.service.impl;

import com.turtle.api.FeignApi;
import com.turtle.dto.AssignRoleParam;
import com.turtle.entity.sql.Role;
import com.turtle.entity.sql.UserRole;
import com.turtle.exception.UserAlertException;
import com.turtle.mapper.RoleMapper;
import com.turtle.mapper.UserRoleMapper;
import com.turtle.service.RoleService;
import com.turtle.vo.ResultBody;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
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
