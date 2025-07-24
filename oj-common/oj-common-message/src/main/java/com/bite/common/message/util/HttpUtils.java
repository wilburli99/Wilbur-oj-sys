package com.bite.common.message.util;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HTTP工具类，用于第三方短信接口调用
 */
public class HttpUtils {

    /**
     * 执行POST请求
     *
     * @param host    主机地址
     * @param path    请求路径
     * @param method  请求方法
     * @param headers 请求头
     * @param querys  查询参数
     * @param bodys   请求体参数
     * @return HttpResponse
     * @throws Exception
     */
    public static HttpResponse doPost(String host, String path, String method,
                                      Map<String, String> headers,
                                      Map<String, String> querys,
                                      Map<String, String> bodys) throws Exception {
        
        CloseableHttpClient httpClient = HttpClients.createDefault();
        
        // 构建完整URL
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(host).append(path);
        
        // 添加查询参数
        if (querys != null && !querys.isEmpty()) {
            urlBuilder.append("?");
            boolean first = true;
            for (Map.Entry<String, String> entry : querys.entrySet()) {
                if (!first) {
                    urlBuilder.append("&");
                }
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue());
                first = false;
            }
        }
        
        HttpPost httpPost = new HttpPost(urlBuilder.toString());
        
        // 设置请求头
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }
        
        // 设置请求体
        if (bodys != null && !bodys.isEmpty()) {
            List<NameValuePair> params = new ArrayList<>();
            for (Map.Entry<String, String> entry : bodys.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        }
        
        return httpClient.execute(httpPost);
    }
} 