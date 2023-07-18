package com.zzdd.smallspending.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public int signUp(MemberDto memberDto) {

        if(isIdExist(memberDto.getId())){
            return 0;
        }

        memberDto.setPwd(encodePwd(memberDto.getPwd()));

        return memberRepository.insertMember(memberDto);
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
