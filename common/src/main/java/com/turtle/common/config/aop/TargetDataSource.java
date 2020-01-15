package com.turtle.common.config.aop;


import com.turtle.common.config.enums.DataSourceKey;

import java.lang.annotation.*;

/**
 * @author lijiayu
 * @date 2019/12/27
 * @description
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    DataSourceKey value() default DataSourceKey.MASTER;
}
