package com.turtle.common.config.mybatis;

import com.turtle.common.config.enums.DataSourceKey;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lijiayu
 * @date 2019/12/27
 * @description
 */
public class DynamicDataSourceContextHolder {
    private static ThreadLocal<Object> CONTEXT_HOLDER = ThreadLocal.withInitial(()-> DataSourceKey.MASTER.getName());

    public static List<Object> dataSourceKeys = new ArrayList<>();

    public static void setDataSourceKey(String key){
        CONTEXT_HOLDER.set(key);
    }

    public static Object getDataSourceKey(){
        return CONTEXT_HOLDER.get();
    }

    public static void clearDataSourceKey(){
        CONTEXT_HOLDER.remove();
    }

    public static Boolean containDataSourceKey(String key){
        return dataSourceKeys.contains(key);
    }
}
