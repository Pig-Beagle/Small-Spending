package com.zzdd.smallspending.member;

import com.zzdd.smallspending.common.ApiMessage;
import com.zzdd.smallspending.token.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<ApiMessage<MemberDto>> logout(MemberDto memberDto) {
        authService.logout(memberDto);
        return ResponseEntity.ok().body(new ApiMessage<>(HttpStatus.OK, "로그인 성공", memberDto));
    }

}
