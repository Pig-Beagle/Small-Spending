package com.zzdd.smallspending.member;

import com.zzdd.smallspending.config.RedisRepository;
import com.zzdd.smallspending.token.JwtUtil;
import com.zzdd.smallspending.token.RefreshToken;
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
    private final RedisRepository redisRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public Optional<TokenDto> login(MemberRequestDto.Login memberDto) {
        MemberResponseDto.Member member = memberRepository.selectOneById(memberDto.getId());

        if(member == null){
            return Optional.empty();
        }

        if(!passwordEncoder.matches(memberDto.getPwd(), member.getPwd())){
            return Optional.empty();
        }
        TokenDto token = jwtUtil.generateToken(member.getNo());
        redisRepository.save(new RefreshToken(member.getNo(), token.getRefreshToken()));

        return Optional.of(token);
    }

    @Override
    public boolean logout(String refreshToken) {
        return redisRepository.delete(refreshToken);
    }

    public TokenDto newToken(String authorization, String refreshToken) {
        String token = authorization.split(" ")[1];
        if(!jwtUtil.isExpired(token)){
            return null;
        }
        if(jwtUtil.isExpired(refreshToken)){
            return null;
        }

        Optional<RefreshToken> redisToken = redisRepository.findById(refreshToken);

        if(redisToken.isEmpty()){
            return null;
        }
        if(!redisToken.get().getRefreshToken().equals(refreshToken)){
            return null;
        }

        int userNo = redisToken.get().getUserNo();
        TokenDto newToken = jwtUtil.generateToken(userNo);
        redisRepository.save(new RefreshToken(userNo, newToken.getRefreshToken()));
        return newToken;
    }


}
