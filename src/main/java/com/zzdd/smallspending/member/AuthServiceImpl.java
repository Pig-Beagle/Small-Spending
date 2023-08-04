package com.zzdd.smallspending.member;

import com.zzdd.smallspending.token.JwtUtil;
import com.zzdd.smallspending.token.RefreshToken;
import com.zzdd.smallspending.token.RefreshTokenRepository;
import com.zzdd.smallspending.token.TokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{

    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public Optional<TokenDto> login(MemberDto memberDto) {
        MemberDto selectMember = memberRepository.selectOneById(memberDto.getId());

        if(selectMember == null){
            return Optional.empty();
        }

        if(!passwordEncoder.matches(memberDto.getPwd(), selectMember.getPwd())){
            return Optional.empty();
        }
        TokenDto token = jwtUtil.generateToken(selectMember.getId(), selectMember.getNo());
        refreshTokenRepository.save(new RefreshToken(selectMember.getId(), token.getRefreshToken()));

        return Optional.of(token);
    }

    @Override
    public boolean logout(String authorization) {
        String token = authorization.split(" ")[1];
        if(jwtUtil.isExpired(token)){
            return false;
        }
        String userId = jwtUtil.getUserId(token);
        return refreshTokenRepository.delete(userId);
    }

    public TokenDto newToken(String refreshToken) {
        String userId = jwtUtil.getUserId(refreshToken);
        Integer userNo = jwtUtil.getuserNo(refreshToken);
        TokenDto newToken = jwtUtil.generateToken(userId, userNo);
        refreshTokenRepository.save(new RefreshToken(userId, newToken.getRefreshToken()));
        return newToken;
    }


}
