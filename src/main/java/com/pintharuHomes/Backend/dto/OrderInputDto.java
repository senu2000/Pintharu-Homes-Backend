package com.pintharuHomes.Backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderInputDto {

    private String fullName;
    private String fullAddress;
    private String contactNumber;
    private List<OrderPaintQuantity> orderPaintQuantityList;
}
