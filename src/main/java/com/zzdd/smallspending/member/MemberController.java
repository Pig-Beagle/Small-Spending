package com.zzdd.smallspending.member;

import com.zzdd.smallspending.common.ApiMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping()
    public ResponseEntity<ApiMessage<Boolean>> signUp(@Validated MemberRequestDto.SignUp memberDto) {
        int result = memberService.signUp(memberDto);

        if(result != 1){
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "회원가입 실패", false));
        }
        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "회원가입 성공", true));

    }

    @DeleteMapping()
    public ResponseEntity<ApiMessage<Boolean>> deleteMember(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        int result = memberService.deleteMember(authorization);
        if (result != 1) {
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "회원탈퇴 실패", false));
        }
        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "회원탈퇴 성공", true));
    }

    @GetMapping("/my_page")
    public ResponseEntity<ApiMessage<MemberResponseDto.Member>> myPage(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        MemberResponseDto.Member member = memberService.myPage(authorization);
        if(member == null){
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "마이페이지 조회 실패", null));
        }
        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "마이페이지 조회 성공", member));
    }

    @PatchMapping("/my_page")
    public ResponseEntity<ApiMessage<Boolean>> editNick(HttpServletRequest request, @Validated MemberRequestDto.EditNick memberDto) {
        String authorization = request.getHeader("Authorization");
        int result = memberService.editNick(authorization, memberDto);
        if(result != 1){
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "마이페이지 수정 실패", false));
        }
        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "마이페이지 수정 성공", true));
    }

    @PatchMapping("/introduce")
    public ResponseEntity<ApiMessage<Boolean>> editItroduce(HttpServletRequest request, MemberRequestDto.EditIntroduce memberDto) {
        String authorization = request.getHeader("Authorization");
        int result = memberService.editItroduce(authorization, memberDto);
        if(result != 1){
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "자기소개 작성 실패", false));
        }
        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "자기소개 작성 성공", true));
    }


    @PostMapping("/validate_pwd")
    public ResponseEntity<ApiMessage<Boolean>> validatePwd(HttpServletRequest request, String pwd) {
        String authorization = request.getHeader("Authorization");
        boolean result = memberService.validatePwd(authorization, pwd);
        if(!result){
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "비밀번호 불일치", false));
        }
        return ResponseEntity.ok().body(new ApiMessage<>(HttpStatus.OK, "비밀번호 일치", true));
    }

    @PostMapping("/send_mail")
    public ResponseEntity<ApiMessage<Boolean>> sendMail(String userId) {
        boolean result = memberService.sendMail(userId);
        if(!result){
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "가입된 회원 없음", false));
        }
        return ResponseEntity.ok().body(new ApiMessage<>(HttpStatus.OK, "메일 전송 시도 완료", true));
    }

    @PostMapping("/validate_num")
    public ResponseEntity<ApiMessage<Boolean>> validateNum(String userId, String num){
        boolean result = memberService.validateNum(userId, num);
        return ResponseEntity.ok().body(new ApiMessage<>(HttpStatus.OK, "인증 완료", result));
    }

    @PatchMapping("/reset_pwd")
    public ResponseEntity<ApiMessage<Boolean>> resetPwd(String num, @Validated MemberRequestDto.ResetPwd memberDto) {
        boolean validate = memberService.validateNum(memberDto.getId(), num);
        if (!validate) {
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "인증번호 불일치", false));
        }
        int result = memberService.resetPwd(memberDto);
        if(result != 1){
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "비밀번호 수정 실패", false));
        }
        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "비밀번호 수정 성공", true));
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
