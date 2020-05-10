package com.turtle.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lijiayu
 * @date 2020/1/17
 * @description
 */
@Data
@Accessors(chain = true)
@TableName("tbg_options")
public class Options {
    private String id;
    private String key;
    private String value;
}
