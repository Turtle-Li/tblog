package com.turtle.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.turtle.entity.sql.User;
import org.springframework.stereotype.Repository;

/**
 * @author lijiayu
 * @date 2020/1/15
 * @description
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
