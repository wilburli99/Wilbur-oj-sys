package com.bite.common.message.service;

import com.alibaba.fastjson2.JSON;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class AliSmsService {

    @Autowired(required = false)
    private Client aliClient;

    @Autowired
    private ThirdPartySmsService thirdPartySmsService;

    //业务配置
    @Value("${sms.aliyun.templateCode:}")
    private String templateCode;

    @Value("${sms.aliyun.sing-name:}")
    private String singName;

    @Value("${sms.provider:thirdparty}")
    private String smsProvider;

    /**
     * 发送短信验证码
     *
     * @param phone
     * @param code
     */
    public boolean sendMobileCode(String phone, String code) {
        // 根据配置选择短信服务提供商
        if ("aliyun".equalsIgnoreCase(smsProvider)) {
            Map<String, String> params = new HashMap<>();
            params.put("code", code);
            return sendTempMessage(phone, singName, templateCode, params);
        } else {
            // 使用第三方短信服务
            return thirdPartySmsService.sendMobileCode(phone, code);
        }
    }

    /**
     * 发送模板消息
     *
     * @param phone
     * @param singName
     * @param templateCode
     * @param params
     */
    public boolean sendTempMessage(String phone, String singName, String templateCode,
                                   Map<String, String> params) {
        // 根据配置选择短信服务提供商
        if ("aliyun".equalsIgnoreCase(smsProvider)) {
            return sendAliyunSms(phone, singName, templateCode, params);
        } else {
            // 使用第三方短信服务
            return thirdPartySmsService.sendTempMessage(phone, singName, templateCode, params);
        }
    }

    /**
     * 发送阿里云短信
     */
    private boolean sendAliyunSms(String phone, String singName, String templateCode,
                                  Map<String, String> params) {
        if (aliClient == null) {
            log.error("阿里云短信客户端未配置，无法发送短信");
            return false;
        }
        
        SendSmsRequest sendSmsRequest = new SendSmsRequest();
        sendSmsRequest.setPhoneNumbers(phone);
        sendSmsRequest.setSignName(singName);
        sendSmsRequest.setTemplateCode(templateCode);
        sendSmsRequest.setTemplateParam(JSON.toJSONString(params));
        try {
            SendSmsResponse sendSmsResponse = aliClient.sendSms(sendSmsRequest);
            SendSmsResponseBody responseBody = sendSmsResponse.getBody();
            if (!"OK".equalsIgnoreCase(responseBody.getCode())) {
                log.error("阿里云短信{} 发送失败，失败原因:{}.... ", JSON.toJSONString(sendSmsRequest), responseBody.getMessage());
                return false;
            }
            return true;
        }  catch (Exception e) {
            log.error("阿里云短信{} 发送失败，失败原因:{}.... ",  JSON.toJSONString(sendSmsRequest), e.getMessage());
            return false;
        }
    }
}