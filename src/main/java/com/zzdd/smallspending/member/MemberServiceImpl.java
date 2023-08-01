package com.zzdd.smallspending.member;

import com.zzdd.smallspending.token.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public int signUp(MemberDto memberDto) {

        if(isIdExist(memberDto.getId())){
            return 0;
        }

        memberDto.setPwd(encodePwd(memberDto.getPwd()));

        return memberRepository.insertMember(memberDto);
    }

    @Override
    public int deleteMember(String authorization) {
        String token = authorization.split(" ")[1];
        String userId = jwtUtil.getUserId(token);
        MemberDto member = memberRepository.selectOneById(userId);

        if(member == null){
            return 0;
        }
        int result = memberRepository.deleteMember(member);

        authService.logout(authorization);

        return result;
    }
    @Override
    public List<MemberDto> myPage(String authorization) {
        String token = authorization.split(" ")[1];
        Integer userNo = jwtUtil.getuserNo(token);

        return memberRepository.selectMyPage(userNo);
    }


    @Override
    public int editPwd(String authorization) {


        return 0;
    }

    @Override
    public boolean validatePwd(String authorization, String pwd) {
        String token = authorization.split(" ")[1];
        String userId = jwtUtil.getUserId(token);

        MemberDto selectMember = memberRepository.selectOneById(userId);

        if(!passwordEncoder.matches(pwd, selectMember.getPwd())) {
            return false;
        }

        return true;
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
