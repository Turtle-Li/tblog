package com.turtle.service;

import com.turtle.api.FeignApi;
import com.turtle.config.feign.FeignConfiguration;
import com.turtle.config.feign.HystrixFeignFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author lijiayu
 * @date 2020/4/30
 * @description
 */
@FeignClient(name = "admin" ,path = "/v1",configuration = FeignConfiguration.class,fallbackFactory = HystrixFeignFallBackFactory.class)
@Component
public interface FeignClientService extends FeignApi {

}
