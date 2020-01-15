package com.turtle.common.config.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author lijiayu
 * @date 2019/12/27
 * @description
 */
@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        logger.info("current datasource is : {"+DynamicDataSourceContextHolder.getDataSourceKey()+"}");
        return DynamicDataSourceContextHolder.getDataSourceKey();
    }
}
