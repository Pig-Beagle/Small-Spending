package com.zzdd.smallspending.member;

import com.fasterxml.jackson.databind.JsonNode;
import com.zzdd.smallspending.config.RedisRepository;
import com.zzdd.smallspending.token.JwtUtil;
import com.zzdd.smallspending.token.RefreshToken;
import com.zzdd.smallspending.token.TokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{

    private final MemberRepository memberRepository;
    private final RedisRepository redisRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${google.client-id}")
    private String clientId;
    @Value("${google.client-secret}")
    private String clientSecret;
    @Value("${google.redirect-uri}")
    private String redirectUri;

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
    public TokenDto googleLogin(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("grant_type", "authorization_code");
        params.add("redirect_uri", redirectUri);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, httpHeaders);

        ResponseEntity<JsonNode> responseNode = restTemplate.exchange("https://oauth2.googleapis.com/token", HttpMethod.POST, entity, JsonNode.class);
        JsonNode body = responseNode.getBody();

        String accessToken = Objects.requireNonNull(body).get("access_token").asText();

        httpHeaders.set("Authorization", "Bearer " + accessToken);
        JsonNode userInfo = restTemplate.exchange("https://www.googleapis.com/oauth2/v2/userinfo", HttpMethod.GET, new HttpEntity<>(httpHeaders), JsonNode.class).getBody();

        if(userInfo == null){
            throw new IllegalArgumentException("유저 정보를 가져오는데 실패했습니다.");
        }

        String email = userInfo.get("email").asText();
        String name = userInfo.get("name").asText();

        MemberResponseDto.Member member = memberRepository.selectOneById(email);

        if(member == null){
            MemberRequestDto.OauthSignUp oauthSignUp = new MemberRequestDto.OauthSignUp(email, name);
            memberRepository.insertOauthMember(oauthSignUp);
            member = memberRepository.selectOneById(email);
        }

        TokenDto token = jwtUtil.generateToken(member.getNo());
        redisRepository.save(new RefreshToken(member.getNo(), token.getRefreshToken()));

        return token;
    }

    @Override
    public boolean logout(String refreshToken) {
        return redisRepository.delete(refreshToken);
    }


    public Optional<TokenDto> newToken(String authorization, String refreshToken) {
        String token = authorization.split(" ")[1];
        if(!jwtUtil.isExpired(token)){
            return Optional.empty();
        }
        if(jwtUtil.isExpired(refreshToken)){
            return Optional.empty();
        }

        Optional<RefreshToken> redisToken = redisRepository.findById(refreshToken);

        if(redisToken.isEmpty()){
            return Optional.empty();
        }
        if(!redisToken.get().getRefreshToken().equals(refreshToken)){
            return Optional.empty();
        }

        int userNo = redisToken.get().getUserNo();
        TokenDto newToken = jwtUtil.generateToken(userNo);
        redisRepository.save(new RefreshToken(userNo, newToken.getRefreshToken()));
        return Optional.of(newToken);
    }


}
