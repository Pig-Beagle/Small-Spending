package com.zzdd.smallspending.member;

import com.zzdd.smallspending.common.ApiMessage;
import com.zzdd.smallspending.token.TokenDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "로그인", description = "로그인 메소드 입니다.")
    @PostMapping("/login")
    public ResponseEntity<ApiMessage<Optional<TokenDto>>> login(@RequestBody @Validated MemberRequestDto.Login memberDto) {
        Optional<TokenDto> token = authService.login(memberDto);
        if (token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiMessage<>(HttpStatus.FORBIDDEN, "로그인 실패", null));
        }
        return ResponseEntity.ok().body(new ApiMessage<>(HttpStatus.OK, "로그인 성공", token));
    }

    @Operation(summary = "구글 로그인", description = "구글 로그인 메소드 입니다.")
    @GetMapping("/oauth2")
    public ResponseEntity<ApiMessage<TokenDto>> googleLogin(String code){
        TokenDto token = authService.googleLogin(code);
        return ResponseEntity.ok().body(new ApiMessage<>(HttpStatus.OK, "로그인 성공", token));
    }

    @Operation(summary = "로그아웃", description = "로그아웃 메소드 입니다.")
    @PostMapping("/logout")
    public ResponseEntity<ApiMessage<Boolean>> logout(HttpServletRequest request) {
        String refreshToken = request.getHeader("refreshToken");
        boolean result = authService.logout(refreshToken);
        if(!result){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiMessage<>(HttpStatus.FORBIDDEN, "로그아웃 실패", false));
        }
        return ResponseEntity.ok().body(new ApiMessage<>(HttpStatus.OK, "로그아웃 성공", true));
    }

    @Operation(summary = "토큰 재발급", description = "토큰 재발급 메소드 입니다.")
    @PostMapping("/get_token")
    public ResponseEntity<ApiMessage<Optional<TokenDto>>> newToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String refreshToken = request.getHeader("refreshToken");
        Optional<TokenDto> newToken = authService.newToken(authorization, refreshToken);
        if(newToken.isEmpty()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiMessage<>(HttpStatus.FORBIDDEN, "리프레시 토큰 발급 실패", null));
        }
        return ResponseEntity.ok().body(new ApiMessage<>(HttpStatus.OK, "리프레시 토큰 발급 성공", newToken));
    }

}
