package com.zzdd.smallspending.member;

public interface MemberRepository {

    int insertMember(MemberDto memberDto);

    MemberDto selectOneById(String id);

    MemberDto selectOneByNick(String nick);
}
