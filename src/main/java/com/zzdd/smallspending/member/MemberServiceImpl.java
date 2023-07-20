package com.zzdd.smallspending.member;

import com.zzdd.smallspending.token.JwtUtil;
import com.zzdd.smallspending.token.TokenDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public int signUp(MemberDto memberDto) {

        if(isIdExist(memberDto.getId())){
            return 0;
        }

        memberDto.setPwd(encodePwd(memberDto.getPwd()));

        return memberRepository.insertMember(memberDto);
    }
    @Override
    public TokenDto login(MemberDto memberDto) {
        MemberDto selectMember = memberRepository.selectOneMember(memberDto);

        if(selectMember == null){
            return null;
        }

        if(!passwordEncoder.matches(memberDto.getPwd(), selectMember.getPwd())){
            return null;
        }


        return jwtUtil.generateToken(memberDto.getId());
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
