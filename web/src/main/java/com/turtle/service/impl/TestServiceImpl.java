package com.turtle.service.impl;

import com.turtle.entity.sql.sql.FavoriteBlog;
import com.turtle.mapper.FavoriteBlogMapper;
import com.turtle.service.FeignClientService;
import com.turtle.service.TestService;
import com.turtle.vo.ResultBody;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author lijiayu
 * @date 2020/5/7
 * @description
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private FavoriteBlogMapper favoriteBlogMapper;
    @Autowired
    private FeignClientService feignClientService;

    @Override
//    @GlobalTransactional
    public ResultBody test(int mile) throws InterruptedException {
//        if(mile==0){
//            throw new RuntimeException();
//        }
//
//        Thread.sleep(mile);
        favoriteBlogMapper.insert(new FavoriteBlog().setUserId(1L).setBlogId(1L).setDateTime(LocalDateTime.now()));
        feignClientService.feignTest(mile);
        int i = 1/0;
        return ResultBody.success();
    }
}
