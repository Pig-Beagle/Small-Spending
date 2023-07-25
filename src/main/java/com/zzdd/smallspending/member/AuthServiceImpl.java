package com.zzdd.smallspending.member;

import com.zzdd.smallspending.token.JwtUtil;
import com.zzdd.smallspending.token.RefreshToken;
import com.zzdd.smallspending.token.RefreshTokenRepository;
import com.zzdd.smallspending.token.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public Optional<TokenDto> login(MemberDto memberDto) {
        MemberDto selectMember = memberRepository.selectOneMember(memberDto);

        if(selectMember == null){
            return Optional.empty();
        }

        if(!passwordEncoder.matches(memberDto.getPwd(), selectMember.getPwd())){
            return Optional.empty();
        }
        TokenDto token = jwtUtil.generateToken(selectMember.getId());
        refreshTokenRepository.save(new RefreshToken(selectMember.getId(), token.getRefreshToken()));

        return Optional.of(token);
    }

    @Override
    public void logout(MemberDto memberDto) {

    }

    public TokenDto refreshToken(String refreshToken) {
        String userId = jwtUtil.getUserName(refreshToken);
        TokenDto newToken = jwtUtil.generateToken(userId);
        refreshTokenRepository.save(new RefreshToken(userId, newToken.getRefreshToken()));
        return newToken;
    }

    private String encodePwd(String pwd){
        return passwordEncoder.encode(pwd);
    }

}
