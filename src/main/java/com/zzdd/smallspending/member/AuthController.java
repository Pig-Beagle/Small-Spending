package com.zzdd.smallspending.member;

import com.zzdd.smallspending.common.ApiMessage;
import com.zzdd.smallspending.token.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiMessage<Optional<TokenDto>>> login(MemberDto memberDto) {
        Optional<TokenDto> token = authService.login(memberDto);
        if (token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiMessage<>(HttpStatus.FORBIDDEN, "로그인 실패", null));
        }
        return ResponseEntity.ok().body(new ApiMessage<>(HttpStatus.OK, "로그인 성공", token));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiMessage<Boolean>> logout(HttpServletRequest request) {
        String refreshToken = request.getHeader("refreshToken");
        boolean result = authService.logout(refreshToken);
        if(!result){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiMessage<>(HttpStatus.FORBIDDEN, "로그아웃 실패", false));
        }
        return ResponseEntity.ok().body(new ApiMessage<>(HttpStatus.OK, "로그아웃 성공", true));
    }

    @PostMapping("/get_token")
    public ResponseEntity<ApiMessage<TokenDto>> newToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String refreshToken = request.getHeader("refreshToken");
        TokenDto newToken = authService.newToken(authorization, refreshToken);
        if(newToken == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiMessage<>(HttpStatus.FORBIDDEN, "리프레시 토큰 발급 실패", null));
        }
        return ResponseEntity.ok().body(new ApiMessage<>(HttpStatus.OK, "리프레시 토큰 발급 성공", newToken));
    }

}
