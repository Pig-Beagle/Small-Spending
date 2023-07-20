package com.zzdd.smallspending.member;

import com.zzdd.smallspending.token.TokenDto;

public interface MemberService {
    int signUp(MemberDto memberDto);

    boolean isIdExist(String id);

    boolean isNickExist(String nick);

    TokenDto login(MemberDto memberDto);
}
