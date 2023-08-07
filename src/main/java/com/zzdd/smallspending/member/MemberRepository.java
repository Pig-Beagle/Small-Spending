package com.zzdd.smallspending.member;

public interface MemberRepository {

    int insertMember(MemberDto memberDto);

    int deleteMember(MemberDto memberDto);

    int updateMyPage(MemberDto memberDto);

    int updateIntroduce(String userId, String introduce);

    int updatePwd(MemberDto memberDto);

    MemberDto selectOneById(String id);

    MemberDto selectOneByNick(String nick);

}
