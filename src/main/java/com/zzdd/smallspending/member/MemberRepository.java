package com.zzdd.smallspending.member;

import java.util.List;

public interface MemberRepository {

    int insertMember(MemberDto memberDto);

    MemberDto selectOneById(String id);

    MemberDto selectOneByNick(String nick);

    int deleteMember(MemberDto memberDto);

    int updatePwd(Integer userNo);

    List<MemberDto> selectMyPage(Integer userNo);
}
