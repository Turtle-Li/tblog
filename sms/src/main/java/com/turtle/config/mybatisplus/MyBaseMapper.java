package com.turtle.config.mybatisplus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author lijiayu
 * @date 2020/4/28
 * @description
 */
public interface MyBaseMapper<T> extends BaseMapper<T> {
    /**
     * 全量插入,等价于insert
     * @param entityList
     * @return
     */
    int insertBatchSomeColumn(List<T> entityList);
}
