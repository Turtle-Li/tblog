package com.turtle.service.impl;

import com.turtle.entity.sql.FavoriteBlog;
import com.turtle.mapper.FavoriteBlogMapper;
import com.turtle.service.FeignClientService;
import com.turtle.service.TestService;
import com.turtle.vo.ResultBody;
import io.seata.core.context.RootContext;
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

}
