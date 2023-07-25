package com.zzdd.smallspending.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    @Autowired
    MemberMapper memberMapper;

    @Override
    public int insertMember(MemberDto memberDto) {
        return memberMapper.insertMember(memberDto);
    }
    @Override
    public MemberDto selectOneMember(MemberDto memberDto) {
        return memberMapper.selectOneMember(memberDto);
    }

    @Override
    public int deleteMember(MemberDto memberDto) {
        return memberMapper.deleteOneMember(memberDto);
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
