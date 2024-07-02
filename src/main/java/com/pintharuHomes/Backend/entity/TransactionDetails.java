package com.pintharuHomes.Backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetails {

    private String orderId;
    private String currency;
    private Integer amount;
    private String key;

}
