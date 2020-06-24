package com.turtle.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.turtle.service.ApiService;
import com.turtle.vo.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

/**
 * @author lijiayu
 * @date 2020/5/3
 * @description 七牛云相关方法
 */
@Service
@Slf4j
public class ApiServiceImpl implements ApiService {

    @Value("${accessKey}")
    private String accessKey;
    @Value("${secretKey}")
    private String secretKey;
    @Value("${bucket}")
    private String bucket;
    @Value("${CDN}")
    private String CDN;

    private Auth auth;
    private UploadManager uploadManager;
    private BucketManager bucketManager;

    @PostConstruct
    public void init(){
        auth = Auth.create(accessKey, secretKey);
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region2());
        uploadManager = new UploadManager(cfg);
        bucketManager = new BucketManager(auth, cfg);
    }

    @Override
    public String getUpToken(){
        return auth.uploadToken(bucket);
    }

    @Override
    public ResultBody upload(MultipartFile file) {
        DefaultPutRet putRet = new DefaultPutRet();
        try{
            byte[] uploadBytes = file.getBytes();
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(uploadBytes, null, upToken);
                //解析上传成功的结果
                putRet = JSONObject.parseObject(response.bodyString(), DefaultPutRet.class);
                log.info(putRet.key);
                log.info(putRet.hash);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    ex2.printStackTrace();
                }
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return ResultBody.result(CDN+putRet.hash);
    }

    @Override
    public ResultBody delete(String url) {
        String key = url.substring(CDN.length());
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            log.error(String.valueOf(ex.code()));
            log.error(ex.response.toString());
            return ResultBody.error("删除失败！");
        }
        return ResultBody.success();
    }

    @Override
    public ResultBody deleteBatch(List<String> urls) {
        try {
            //单次批量请求的文件数量不得超过1000
            String[] keyList = urls.stream().map(x -> x.substring(CDN.length())).toArray(String[]::new);
            BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
            batchOperations.addDeleteOp(bucket, keyList);
            Response response = bucketManager.batch(batchOperations);
            BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);
            for (int i = 0; i < keyList.length; i++) {
                BatchStatus status = batchStatusList[i];
                String key = keyList[i];
                log.info(key + "\t");
                if (status.code == 200) {
                    log.info("delete success");
                } else {
                    log.info(status.data.error);
                }
            }
        } catch (QiniuException ex) {
            log.error(ex.response.toString());
        }
        return ResultBody.success();
    }
}
