package com.pintharuHomes.Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDto {

    private Integer id;
    private String orderFullName;
    private String orderFullAddress;
    private String orderContactNo;
    private String orderStatus;
    private Integer orderAmount;
    private PaintDto paintDto;
    private UserDto userDto;

}
