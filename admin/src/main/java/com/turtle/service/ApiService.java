package com.turtle.service;

import com.turtle.vo.ResultBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author lijiayu
 * @date 2020/5/3
 * @description
 */
public interface ApiService {

    String getUpToken();

    ResultBody upload(MultipartFile file);

    ResultBody delete(String url);

    ResultBody deleteBatch(List<String> urls);
}
