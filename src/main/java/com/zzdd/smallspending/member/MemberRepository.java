package com.zzdd.smallspending.member;

public interface MemberRepository {

    int insertMember(MemberDto memberDto);

    int deleteMember(MemberDto memberDto);

    int updateMyPage(MemberDto memberDto);

    int updateIntroduce(int userNo, String introduce);

    int updatePwd(MemberDto memberDto);

    MemberDto selectOneByNo(int userNo);

    MemberDto selectOneByNick(String nick);

    MemberDto selectOneById(String id);
}
