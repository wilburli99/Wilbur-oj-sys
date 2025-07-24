package com.bite.friend.test;

import com.bite.common.core.controller.BaseController;
import com.bite.common.core.domain.R;
import com.bite.common.message.service.AliSmsService;
import com.bite.common.message.service.ThirdPartySmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController extends BaseController {

    @Autowired
    private AliSmsService aliSmsService;

    @Autowired
    private ThirdPartySmsService thirdPartySmsService;

    @GetMapping("/sendCode")
    public R<Void> sendCode(String phone, String code) {
        log.info("验证码发送测试（通过AliSmsService统一入口）");
        return toR(aliSmsService.sendMobileCode(phone, code));
    }

    @GetMapping("/sendCode/thirdparty")
    public R<Void> sendCodeThirdParty(String phone, String code) {
        log.info("第三方验证码发送测试（直接调用ThirdPartySmsService）");
        return toR(thirdPartySmsService.sendMobileCode(phone, code));
    }
}
