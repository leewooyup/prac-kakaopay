package com.example.demo.pay;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class PaymentVO {
    private int pay_code; //일련번호
    private long order_code; //주문번호
    private Date pay_date; //결제일
    private int pay_tot_price; //결제금액
    private int pay_rest_price; //미지급금액
    private String pay_nobank_user; //입금자명(무통장)
    private String pay_nobank; //입금은행
}
