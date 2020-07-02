package com.turtle.dto;

import lombok.Data;

/**
 * @author lijiayu
 * @date 2020/7/2
 * @description
 */
@Data
public class AddressResult {
    private Integer status;
    private String message;
    private Result result;

    @Data
    public static class Result{
       private String ip;
       private location location;
       private adInfo adInfo;
    }

    @Data
    public static class location{
        /**
         * 经度
         */
        private Double lng;
        /**
         * 纬度
         */
        private Double lat;
    }

    @Data
    public static class adInfo{
        /**
         * 国家
         */
        private String nation;
        /**
         * 省
         */
        private String province;
        /**
         * 市
         */
        private String city;
        /**
         * 行政区划代码
         */
        private Integer adcode;
    }
}
