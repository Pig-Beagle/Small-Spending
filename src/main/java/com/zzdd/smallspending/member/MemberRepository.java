package com.zzdd.smallspending.member;

public interface MemberRepository {

    int insertMember(MemberRequestDto.SignUp memberDto);

    int deleteMember(int userNo);

    int updateNick(MemberRequestDto.EditNick memberDto);

    int updateIntroduce(MemberRequestDto.EditIntroduce memberDto);

    int updatePwd(MemberRequestDto.ResetPwd memberDto);

    MemberResponseDto.Member selectOneByNo(int userNo);

    MemberResponseDto.Member selectOneByNick(String nick);

    MemberResponseDto.Member selectOneById(String id);
}
