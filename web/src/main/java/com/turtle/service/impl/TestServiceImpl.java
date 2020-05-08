package com.turtle.service.impl;

import com.turtle.service.TestService;
import com.turtle.vo.ResultBody;
import org.springframework.stereotype.Service;

/**
 * @author lijiayu
 * @date 2020/5/7
 * @description
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    public ResultBody test(int mile) throws InterruptedException {
        if(mile==0){
            throw new RuntimeException();
        }
        Thread.sleep(mile);
        return ResultBody.success();
    }
}
