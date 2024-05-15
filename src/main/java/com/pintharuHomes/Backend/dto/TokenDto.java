package com.pintharuHomes.Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {

    private Integer id;
    private String token;
    private boolean loggedOut;
    private Integer userId;

}
