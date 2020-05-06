package com.turtle.api;


import com.turtle.vo.ResultBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lijiayu
 * @date 2020/4/30
 * @description
 */
@RequestMapping("/rpc")
public interface FeignApi {

    @GetMapping("/checkName")
    ResultBody feignTest(@RequestParam("mile") int mile) throws InterruptedException;
}
