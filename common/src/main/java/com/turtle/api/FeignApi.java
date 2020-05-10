package com.turtle.api;


import com.turtle.vo.ResultBody;
import org.springframework.web.bind.annotation.*;

/**
 * @author lijiayu
 * @date 2020/4/30
 * @description
 */
@RequestMapping("/rpc")
public interface FeignApi {

    /**
     * get请求会触发重试，请不要在get请求中保存数据。
     **/

    @PostMapping("/checkName")
    ResultBody feignTest(@RequestParam("mile") int mile) throws InterruptedException;
}
