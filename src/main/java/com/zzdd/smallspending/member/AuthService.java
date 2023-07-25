package com.zzdd.smallspending.member;

import com.zzdd.smallspending.token.TokenDto;

import java.util.Optional;

public interface AuthService {

    Optional<TokenDto> login(MemberDto memberDto);

    void logout(MemberDto memberDto);

    TokenDto refreshToken(String refreshToken);

}
