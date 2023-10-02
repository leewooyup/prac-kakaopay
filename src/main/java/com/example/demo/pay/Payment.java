package com.example.demo.pay;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String order_id;
    private String item_name;
    private Integer quantity;
    private Integer pay_tot_price;
    private String pay_user;
    private String pay_bank;
    private String pay_at; //결제일
}
