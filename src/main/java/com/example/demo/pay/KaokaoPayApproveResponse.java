package com.example.demo.pay;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KaokaoPayApproveResponse {
    private String aid; //요청 고유번호
    private String tid; //결제 고유번호
    private String cid; //가맹점 코드
    private String sid; //정기 결제용 id
    private String partner_order_id; //가맹점 주문번호
    private String partner_user_id; //가맹점 회원id
    private String payment_method_type; //결제수단, CARD OR MONEY

    private String item_name;
    private String item_code;
    private int quantity;
    private String created_at; // 결제 준비 요청 시각
    private String approved_at; //결제 승인 시각
    private String payload; //결제 승인 요청에 대해 저장한 값

    private Amount amount;
}
