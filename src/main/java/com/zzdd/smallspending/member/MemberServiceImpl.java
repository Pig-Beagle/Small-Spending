package com.zzdd.smallspending.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public int signUp(MemberDto memberDto) {

        if(isIdExist(memberDto.getId())){
            return 0;
        }

        memberDto.setPwd(encodePwd(memberDto.getPwd()));

        return memberRepository.insertMember(memberDto);
    }
    @Override
    public int deleteMember(MemberDto memberDto) {

        MemberDto member = memberRepository.selectOneMember(memberDto);

        if(member == null){
            return 0;
        }

        return memberRepository.deleteMember(memberDto);
    }

    @Override
    public boolean isIdExist(String id){
        MemberDto memberDto = memberRepository.selectOneById(id);
        return memberDto != null;
    }

    @Override
    public boolean isNickExist(String nick){
        MemberDto memberDto = memberRepository.selectOneByNick(nick);
        return memberDto != null;
    }


    private String encodePwd(String pwd){
        return passwordEncoder.encode(pwd);
    }
}
