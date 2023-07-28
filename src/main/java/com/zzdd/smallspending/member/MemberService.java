package com.zzdd.smallspending.member;

public interface MemberService {
    int signUp(MemberDto memberDto);

    int deleteMember(String authorization);

    boolean isIdExist(String id);

    boolean isNickExist(String nick);

}
