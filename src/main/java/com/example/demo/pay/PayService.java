package com.example.demo.pay;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


import java.util.Map;

@Slf4j
@Service
public class PayService {
    @Value("${my.admin}")
    private String ADMIN_KEY;
    private KakaoPayReadyResponse kakaoPayReadyResponse;

    public KakaoPayReadyResponse kakaoPayReady(Map<String, Object> params) {
        MultiValueMap<String, Object> payParams = new LinkedMultiValueMap<>();
        payParams.add("cid", "TC0ONETIME"); //가맹점 코드
        payParams.add("partner_order_id", "KA2020338445"); //가맹점 주문번호
        payParams.add("partner_user_id", "kakaopayTest"); //가맹점 회원 id
        payParams.add("item_name", params.get("item_name"));
        payParams.add("quantity", params.get("quantity"));
        payParams.add("total_amount", params.get("total_amount"));
        payParams.add("tax_free_amount", params.get("tax_free_amount")); //상품 비과세 금액
        payParams.add("approval_url", "http://localhost:83/pay/success"); //결제 성공시 넘어갈 url
        payParams.add("cancel_url", "http://localhost:83/pay/cancel"); //결제 취소시 넘어갈 url
        payParams.add("fail_url", "http://localhost:83/pay/fail"); //결제 실패시 넘어갈 url

        HttpEntity<Map> requestEntity = new HttpEntity<>(payParams, this.getHeaders());

        RestTemplate template = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/ready";

        kakaoPayReadyResponse = template.postForObject(
                url,
                requestEntity,
                KakaoPayReadyResponse.class);
        /*
        데이터베이스 저장 로직
        etc...
         */
        return kakaoPayReadyResponse;
    }

    public KaokaoPayApproveResponse kakaoPayApprove(String pgToken) {
        MultiValueMap<String, String> payParams = new LinkedMultiValueMap<>();
        payParams.add("cid", "TC0ONETIME");
        payParams.add("tid", kakaoPayReadyResponse.getTid());
        payParams.add("partner_order_id", "KA2020338445");
        payParams.add("partner_user_id", "kakaopayTest");
        payParams.add("pg_token", pgToken);

        HttpEntity<Map> requestEntity = new HttpEntity<>(payParams, this.getHeaders());

        RestTemplate template = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/approve";

        KaokaoPayApproveResponse approveResponse = template.postForObject(
                url,
                requestEntity,
                KaokaoPayApproveResponse.class);
        log.info("결제 승인 응답객체: " + approveResponse);
        return approveResponse;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();

        String auth = "KakaoAK " + ADMIN_KEY;
        headers.set("Authorization", auth);
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        return headers;
    }
}
