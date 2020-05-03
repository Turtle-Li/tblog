package com.turtle.controller;

import com.turtle.service.ApiService;
import com.turtle.vo.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author lijiayu
 * @date 2020/5/3
 * @description
 */
@RestController
@RequestMapping("/qiniu")
public class ApiController {
    @Autowired
    private ApiService apiService;

    @GetMapping("/up-token")
    public String getUpToken(){
        return apiService.getUpToken();
    }

    @PostMapping("/upload")
    public ResultBody upload(@RequestParam("file") MultipartFile file){
        return apiService.upload(file);
    }

    @DeleteMapping("/delete")
    public ResultBody delete(@RequestParam("url") String url){
        return apiService.delete(url);
    }

    @DeleteMapping("/delete-batch")
    public ResultBody deleteBatch(@RequestBody() List<String> urls){
        return apiService.deleteBatch(urls);
    }
}
