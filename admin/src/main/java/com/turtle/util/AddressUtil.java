package com.turtle.util;

import com.alibaba.fastjson.JSONObject;
import com.turtle.dto.AddressResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author lijiayu
 * @date 2020/7/2
 * @description 地址相关api
 */
@Component
@Slf4j
public class AddressUtil {

    @Value("${addressKey}")
    private String KEY;

    /**
     * 根据ip获取地址信息
     * @param ip
     * @return
     */
    public String getCityInfo(String ip)  {
        String s = sendGet(ip, KEY);
        AddressResult addressResult = JSONObject.parseObject(s, AddressResult.class);
        if(addressResult.getStatus()==0){
            AddressResult.Result result = addressResult.getResult();
            AddressResult.adInfo adInfo = result.getAdInfo();
            String nation = adInfo.getNation();
            String province = adInfo.getProvince();
            String city = adInfo.getCity();
            return nation + "-" + province + "-" + city;
        }else{
            System.out.println(addressResult.getMessage());
            return null;
        }
    }
    //根据在腾讯位置服务上申请的key进行请求操作
    public String sendGet(String ip, String key) {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            String urlNameString = "https://apis.map.qq.com/ws/location/v1/ip?ip="+ip+"&key="+key;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            log.error("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result.toString();
    }
}
