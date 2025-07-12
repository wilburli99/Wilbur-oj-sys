package com.bite.friend.test;

import com.bite.common.core.controller.BaseController;
import com.bite.common.core.domain.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/nginx")
@Slf4j
public class NginxTestController  extends BaseController {

    @GetMapping("/info")
    public R<Void> info() {
        log.info("负载均衡测试");
        return R.ok();
    }

//    location /bitoj-dev/ {
//        proxy_pass http://bitoj/;
//    }

//    upstream bitoj {
//        server 192.168.100.136:9202 weight=1;
//        server 192.168.100.136:19202 weight=2;
//    }

}
