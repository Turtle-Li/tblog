package com.turtle.common.config.enums;

/**
 * @author lijiayu
 * @date 2019/12/27
 * @description
 */
public enum DataSourceKey {
    MASTER("master"),
    SLAVE("slave");

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private DataSourceKey(String name){
        this.name = name;
    }
}
