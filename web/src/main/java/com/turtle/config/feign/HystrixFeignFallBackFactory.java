package com.turtle.config.feign;

import com.turtle.service.FeignClientService;
import com.turtle.vo.ResultBody;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lijiayu
 * @date 2020/4/30
 * @description
 */
@Component
@Slf4j
public class HystrixFeignFallBackFactory implements FallbackFactory<FeignClientService> {
    @Override
    public FeignClientService create(Throwable cause) {

        log.error("fallback; reason was: " + cause.getMessage(), cause);

        return new FeignClientService() {
            @Override
            public ResultBody feignTest(String name) {
                return ResultBody.error("error");
            }
        };
    }
}
