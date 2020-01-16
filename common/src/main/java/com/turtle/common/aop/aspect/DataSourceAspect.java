package com.turtle.common.aop.aspect;

import com.turtle.common.aop.TargetDataSource;
import com.turtle.common.config.mybatis.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author lijiayu
 * @date 2019/12/27
 * @description
 */
@Aspect
@Component
@Slf4j
public class DataSourceAspect {

    @Before("@annotation(targetDataSource))")
    public void switchDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource){
        if ( !DynamicDataSourceContextHolder.containDataSourceKey( targetDataSource.value().getName() ) ) {
            log.error("DataSource [{}] doesn't exist, use default DataSource [{}]", targetDataSource.value());
        } else {
            DynamicDataSourceContextHolder.setDataSourceKey( targetDataSource.value().getName());
            log.info("Switch DataSource to [{}] in Method [{}]",
                    DynamicDataSourceContextHolder.getDataSourceKey(), joinPoint.getSignature());
        }
    }


    @After("@annotation(targetDataSource))")
    public void restoreDataSource(JoinPoint joinPoint,TargetDataSource targetDataSource){
        DynamicDataSourceContextHolder.clearDataSourceKey();
        log.info("Restore DataSource to [{}] in Method [{}]", DynamicDataSourceContextHolder.getDataSourceKey(), joinPoint.getSignature());
    }
}
