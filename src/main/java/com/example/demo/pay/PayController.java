package com.example.demo.pay;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/pay")
public class PayController {
    private final PayService payService;

    @GetMapping("/pay/ready")
    @ResponseBody
    public KakaoPayReadyVO kakaoPay(@RequestParam Map<String, Object> params) {
        KakaoPayReadyVO res = payService.kakaoPay(params);
        log.info(res.toString());
        return res;
    }
}
