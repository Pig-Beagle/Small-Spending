package com.zzdd.smallspending.member;

public interface MemberService {
    int signUp(MemberDto memberDto);

    int deleteMember(MemberDto memberDto);

    boolean isIdExist(String id);

    boolean isNickExist(String nick);

}
