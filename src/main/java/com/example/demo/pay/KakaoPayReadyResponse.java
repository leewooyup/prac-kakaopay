package com.example.demo.pay;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class KakaoPayReadyResponse {
    private String tid; //결제고유번호
    private String next_redirect_pc_url; //요청한 클라이언트가 PC WEB
//    private String partner_order_id; //가맹점 주문번호
    private Date created_at; //결제 준비 요청 시간
}
