package com.zzdd.smallspending.member;

import com.zzdd.smallspending.token.TokenDto;

import java.util.Optional;

public interface AuthService {

    Optional<TokenDto> login(MemberRequestDto.Login memberDto);

    TokenDto googleLogin(String code);

    Optional<TokenDto> newToken(String refreshToken, String token);

    boolean logout(String authorization);

}
