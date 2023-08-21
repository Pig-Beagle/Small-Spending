package com.zzdd.smallspending.member;

public interface MemberService {
    int signUp(MemberRequestDto.SignUp memberDto);

    int deleteMember(String authorization);

    MemberResponseDto.Member myPage(String authorization);

    int editNick(String authorization, MemberRequestDto.EditNick memberDto);

    int editItroduce(String authorization, MemberRequestDto.EditIntroduce memberDto);

    boolean validatePwd(String authorization, String pwd);

    boolean sendMail(String userId);

    boolean validateNum(String userId, String num);

    int resetPwd(MemberRequestDto.ResetPwd memberDto);

    boolean isIdExist(String id);

    boolean isNickExist(String nick);

}
