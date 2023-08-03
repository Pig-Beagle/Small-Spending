package com.zzdd.smallspending.member;

public interface MemberService {
    int signUp(MemberDto memberDto);

    int deleteMember(String authorization);

    MemberDto myPage(String authorization);

    int editPwd(String authorization);

    boolean validatePwd(String authorization, String pwd);

    boolean isIdExist(String id);

    boolean isNickExist(String nick);


}
