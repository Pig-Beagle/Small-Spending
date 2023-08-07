package com.zzdd.smallspending.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
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
    public int updateMyPage(MemberDto memberDto) {
        return memberMapper.updateMyPage(memberDto);
    }

    @Override
    public int updateIntroduce(String userId, String introduce) {
        return memberMapper.updateIntroduce(userId, introduce);
    }

    @Override
    public int updatePwd(MemberDto memberDto) {
        return memberMapper.updatePwd(memberDto);
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
