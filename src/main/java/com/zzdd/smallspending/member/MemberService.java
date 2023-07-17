package com.zzdd.smallspending.member;

public interface MemberService {
    int signUp(MemberDto memberDto);

    boolean isIdExist(String id);

    boolean isNickExist(String nick);
}
