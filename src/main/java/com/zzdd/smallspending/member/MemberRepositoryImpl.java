package com.zzdd.smallspending.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberMapper memberMapper;

    @Override
    public int insertMember(MemberDto memberDto) {
        return memberMapper.insertMember(memberDto);
    }

    @Override
    public int deleteMember(MemberDto memberDto) {
        return memberMapper.deleteOneMember(memberDto);
    }

    @Override
    public int updatePwd(Integer userNo) {
        return 0;
    }


    @Override
    public MemberDto selectOneById(String id) {
        return memberMapper.selectOneById(id);
    }

    @Override
    public MemberDto selectOneByNick(String nick) {
        return memberMapper.selectOneByNick(nick);
    }


}
