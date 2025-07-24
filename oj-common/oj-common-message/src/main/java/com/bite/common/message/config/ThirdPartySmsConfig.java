package com.bite.common.message.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 第三方短信服务配置类
 */
@Configuration
public class ThirdPartySmsConfig {

    @Value("${sms.thirdparty.host:https://smsv2.market.alicloudapi.com}")
    private String host;

    @Value("${sms.thirdparty.path:/sms/sendv2}")
    private String path;

    @Value("${sms.thirdparty.appcode:}")
    private String appcode;

    @Value("${sms.thirdparty.signature:【文博oj在线编程】}")
    private String signature;

    public String getHost() {
        return host;
    }

    public String getPath() {
        return path;
    }

    public String getAppcode() {
        return appcode;
    }

    public String getSignature() {
        return signature;
    }
} 