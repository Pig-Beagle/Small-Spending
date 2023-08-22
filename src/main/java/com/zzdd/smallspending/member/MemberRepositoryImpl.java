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
    public int insertMember(MemberRequestDto.SignUp memberDto) {
        return memberMapper.insertMember(memberDto);
    }

    @Override
    public int insertOauthMember(MemberRequestDto.OauthSignUp memberDto) {
        return memberMapper.insertOauthMember(memberDto);
    }

    @Override
    public int deleteMember(int userNo) {
        return memberMapper.deleteOneMember(userNo);
    }

    @Override
    public int updateNick(MemberRequestDto.EditNick memberDto) {
        return memberMapper.updateNick(memberDto);
    }

    @Override
    public int updateIntroduce(MemberRequestDto.EditIntroduce memberDto) {
        return memberMapper.updateIntroduce(memberDto);
    }

    @Override
    public int updatePwd(MemberRequestDto.ResetPwd memberDto) {
        return memberMapper.updatePwd(memberDto);
    }

    @Override
    public MemberResponseDto.Member selectOneByNo(int userNo) {
        return memberMapper.selectOneByNo(userNo);
    }

    @Override
    public MemberResponseDto.Member selectOneById(String id) {
        return memberMapper.selectOneById(id);
    }

    @Override
    public MemberResponseDto.Member selectOneByNick(String nick) {
        return memberMapper.selectOneByNick(nick);
    }


}
