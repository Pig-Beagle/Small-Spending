package com.zzdd.smallspending.member;

public interface MemberService {
    int signUp(MemberDto memberDto);

    int deleteMember(String authorization);

    MemberDto myPage(String authorization);

    int editMyPage(String authorization, String nick, String introduce);

    int introduce(String authorization, String introduce);

    boolean validatePwd(String authorization, String pwd);

    boolean sendMail(String userId);

    boolean validateNum(String userId, String num);

    int resetPwd(MemberDto memberDto);

    boolean isIdExist(String id);

    boolean isNickExist(String nick);

}
