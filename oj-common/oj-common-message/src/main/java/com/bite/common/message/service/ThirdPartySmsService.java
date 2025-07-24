package com.bite.common.message.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.bite.common.message.config.ThirdPartySmsConfig;
import com.bite.common.message.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 第三方短信服务实现类
 * 保持与AliSmsService相同的接口结构
 */
@Component
@Slf4j
public class ThirdPartySmsService {

    @Autowired
    private ThirdPartySmsConfig smsConfig;

    /**
     * 发送短信验证码
     * 保持与AliSmsService相同的方法签名
     *
     * @param phone 手机号
     * @param code  验证码
     * @return 发送结果
     */
    public boolean sendMobileCode(String phone, String code) {
        String content = smsConfig.getSignature() + "您的验证码是" + code + "。如非本人操作，请忽略本短信";
        return sendTempMessage(phone, content);
    }

    /**
     * 发送模板消息
     * 适配第三方接口的实现方式
     *
     * @param phone   手机号
     * @param content 短信内容
     * @return 发送结果
     */
    public boolean sendTempMessage(String phone, String content) {
        try {
            // 构建请求头
            Map<String, String> headers = new HashMap<>();
            headers.put("Authorization", "APPCODE " + smsConfig.getAppcode());
            headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

            // 构建请求参数
            Map<String, String> querys = new HashMap<>();
            Map<String, String> bodys = new HashMap<>();
            bodys.put("mobile", phone);
            bodys.put("content", content);

            // 发送请求
            HttpResponse response = HttpUtils.doPost(
                    smsConfig.getHost(),
                    smsConfig.getPath(),
                    "POST",
                    headers,
                    querys,
                    bodys
            );

            // 处理响应
            String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
            log.info("第三方短信发送响应: {}", responseBody);

            // 解析响应结果
            JSONObject jsonResponse = JSON.parseObject(responseBody);
            
            // 根据第三方接口的响应格式判断是否成功
            // 这里需要根据实际的第三方接口响应格式进行调整
            if (jsonResponse != null) {
                String code = jsonResponse.getString("code");
                String message = jsonResponse.getString("message");
                
                if ("200".equals(code) || "success".equalsIgnoreCase(code)) {
                    log.info("短信发送成功: phone={}, content={}", phone, content);
                    return true;
                } else {
                    log.error("短信发送失败: phone={}, code={}, message={}", phone, code, message);
                    return false;
                }
            }
            
            return false;
            
        } catch (Exception e) {
            log.error("短信发送异常: phone={}, content={}, error={}", phone, content, e.getMessage(), e);
            return false;
        }
    }

    /**
     * 兼容原有的sendTempMessage方法签名
     * 保持与AliSmsService相同的接口
     *
     * @param phone        手机号
     * @param singName     签名名称（第三方接口中已集成在content中）
     * @param templateCode 模板代码（第三方接口不需要）
     * @param params       参数Map
     * @return 发送结果
     */
    public boolean sendTempMessage(String phone, String singName, String templateCode, Map<String, String> params) {
        // 从参数中提取验证码
        String code = params.get("code");
        if (code != null) {
            return sendMobileCode(phone, code);
        }
        
        // 如果没有验证码，构建通用消息内容
        String content = smsConfig.getSignature() + "您的操作验证码，请妥善保管。";
        return sendTempMessage(phone, content);
    }
} 