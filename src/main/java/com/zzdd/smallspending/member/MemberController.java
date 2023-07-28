package com.zzdd.smallspending.member;

import com.zzdd.smallspending.common.ApiMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/member")
    public ResponseEntity<ApiMessage<Object>> signUp(MemberDto memberDto){

        int result = memberService.signUp(memberDto);
        if(result != 1){
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "회원가입 실패", null));
        }
        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "회원가입 성공", null));

    }

    @DeleteMapping("/member")
    public ResponseEntity<ApiMessage<MemberDto>> deleteMember(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        int result = memberService.deleteMember(authorization);
        if (result != 1) {
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "회원탈퇴 실패", null));
        }
        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "회원탈퇴 성공", null));
    }

    @GetMapping("/check_id")
    public ResponseEntity<ApiMessage<Boolean>> checkId(String id) {
        boolean idExist = memberService.isIdExist(id);
        return ResponseEntity.ok().body(new ApiMessage<>(HttpStatus.OK, "조회 성공", idExist));
    }

    @GetMapping("/check_nick")
    public ResponseEntity<ApiMessage<Boolean>> checkNick(String nick) {
        boolean nickExist = memberService.isNickExist(nick);
        return ResponseEntity.ok().body(new ApiMessage<>(HttpStatus.OK, "조회 성공", nickExist));
    }


}
