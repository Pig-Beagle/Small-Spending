package com.zzdd.smallspending.member;

public interface MemberService {
    int signUp(MemberDto memberDto);

    int deleteMember(String authorization);

    MemberDto myPage(String authorization);

    boolean sendMail(String userId);

    boolean validateNum(String userId, String num);

    int resetPwd(MemberDto memberDto);

    boolean validatePwd(String authorization, String pwd);

    boolean isIdExist(String id);

    boolean isNickExist(String nick);

}
