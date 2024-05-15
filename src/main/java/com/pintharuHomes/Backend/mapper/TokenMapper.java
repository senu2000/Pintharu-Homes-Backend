package com.pintharuHomes.Backend.mapper;

import com.pintharuHomes.Backend.dto.TokenDto;
import com.pintharuHomes.Backend.entity.Token;

public class TokenMapper {

    public static TokenDto mapToTokenDto(Token token){
        return new TokenDto(
                token.getId(),
                token.getToken(),
                token.isLoggedOut(),
                token.getUser().getId()
        );
    }
}
