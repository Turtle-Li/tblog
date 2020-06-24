package com.turtle.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.turtle.dto.UserListParam;
import com.turtle.entity.sql.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author lijiayu
 * @date 2020/1/15
 * @description
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    IPage<User> getListPage(@Param("page")Page<User> page, @Param("param")UserListParam param);
}
