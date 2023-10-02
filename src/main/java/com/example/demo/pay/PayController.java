package com.example.demo.pay;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/ready")
    public @ResponseBody KakaoPayReadyResponse kakaoPayReady(@RequestParam Map<String, Object> params) {
        log.info("item_name: " + params.get("item_name"));
        log.info("quantity: " + params.get("quantity"));
        log.info("total_amount: " + params.get("total_amount"));
        log.info("tax_free_amount: " + params.get("tax_free_amount"));

        KakaoPayReadyResponse kakaoPayReadyResponse = payService.kakaoPayReady(params);
        log.info(kakaoPayReadyResponse.toString());
        return kakaoPayReadyResponse;
    }

    /**
     * 결제 성공
     * @param pgToken
     * @return
     */
    @GetMapping("/success")
    public ResponseEntity paySuccess(@RequestParam("pg_token") String pgToken) {
        KaokaoPayApproveResponse approveResponse = payService.kakaoPayApprove(pgToken);
        return new ResponseEntity<>(approveResponse, HttpStatus.OK);
    }

    @GetMapping("/cancel")
    public String cancel() {
        return "redirect:/";
    }

    @GetMapping("/fail")
    public String fail() {
        return "redirect:/";
    }
}
