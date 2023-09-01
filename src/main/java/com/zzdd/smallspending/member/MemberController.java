package com.zzdd.smallspending.member;

import com.zzdd.smallspending.common.ApiMessage;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "회원가입", description = "회원가입 메소드 입니다.")
    @PostMapping()
    public ResponseEntity<ApiMessage<Boolean>> signUp(@RequestBody @Validated MemberRequestDto.SignUp memberDto) {
        int result = memberService.signUp(memberDto);

        if(result != 1){
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "회원가입 실패", false));
        }
        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "회원가입 성공", true));
    }

    @Operation(summary = "회원탈퇴", description = "회원탈퇴 메소드 입니다.")
    @DeleteMapping()
    public ResponseEntity<ApiMessage<Boolean>> deleteMember(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        int result = memberService.deleteMember(authorization);
        if (result != 1) {
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "회원탈퇴 실패", false));
        }
        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "회원탈퇴 성공", true));
    }

    @Operation(summary = "마이페이지", description = "마이페이지 조회 메소드 입니다.")
    @GetMapping("/my_page")
    public ResponseEntity<ApiMessage<MemberResponseDto.Member>> myPage(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        MemberResponseDto.Member member = memberService.myPage(authorization);
        if(member == null){
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "마이페이지 조회 실패", null));
        }
        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "마이페이지 조회 성공", member));
    }

    @Operation(summary = "닉네임 수정", description = "닉네임 수정 메소드 입니다.")
    @PatchMapping("/my_page")
    public ResponseEntity<ApiMessage<Boolean>> editNick(HttpServletRequest request, @RequestBody @Validated MemberRequestDto.EditNick memberDto) {
        String authorization = request.getHeader("Authorization");
        int result = memberService.editNick(authorization, memberDto);
        if(result != 1){
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "마이페이지 수정 실패", false));
        }
        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "마이페이지 수정 성공", true));
    }

    @Operation(summary = "자기소개 수정", description = "자기소개 수정 메소드 입니다.")
    @PatchMapping("/introduce")
    public ResponseEntity<ApiMessage<Boolean>> editItroduce(HttpServletRequest request, @RequestBody MemberRequestDto.EditIntroduce memberDto) {
        String authorization = request.getHeader("Authorization");
        int result = memberService.editItroduce(authorization, memberDto);
        if(result != 1){
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "자기소개 작성 실패", false));
        }
        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "자기소개 작성 성공", true));
    }

    @Operation(summary = "비밀번호 유효성", description = "비밀번호 유효성 검사 메소드 입니다.")
    @PostMapping("/validate_pwd")
    public ResponseEntity<ApiMessage<Boolean>> validatePwd(HttpServletRequest request, @RequestBody String pwd) {
        String authorization = request.getHeader("Authorization");
        boolean result = memberService.validatePwd(authorization, pwd);
        if(!result){
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "비밀번호 불일치", false));
        }
        return ResponseEntity.ok().body(new ApiMessage<>(HttpStatus.OK, "비밀번호 일치", true));
    }

    @Operation(summary = "인증 메일", description = "비밀번호 수정 시 인증 메일 메소드 입니다.")
    @PostMapping("/send_mail")
    public ResponseEntity<ApiMessage<Boolean>> sendMail(@RequestBody MemberRequestDto.SendMail memberDto) {
        boolean result = memberService.sendMail(memberDto);
        if(!result){
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "가입된 회원 없음", false));
        }
        return ResponseEntity.ok().body(new ApiMessage<>(HttpStatus.OK, "메일 전송 시도 완료", true));
    }

    @Operation(summary = "인증번호 확인", description = "비밀번호 수정 시 인증번호 확인 메소드 입니다.")
    @PostMapping("/validate_num")
    public ResponseEntity<ApiMessage<Boolean>> validateNum(@RequestBody MemberRequestDto.ValidateNum memberDto) {
        boolean result = memberService.validateNum(memberDto);
        return ResponseEntity.ok().body(new ApiMessage<>(HttpStatus.OK, "인증 완료", result));
    }

    @Operation(summary = "비밀번호 수정", description = "비밀번호 수정 메소드 입니다.")
    @PatchMapping("/reset_pwd")
    public ResponseEntity<ApiMessage<Boolean>> resetPwd(@RequestBody @Validated MemberRequestDto.ResetPwd memberDto) {
        MemberRequestDto.ValidateNum validateNum = new MemberRequestDto.ValidateNum();
        validateNum.setId(memberDto.getId());
        validateNum.setNum(memberDto.getNum());
        boolean validate = memberService.validateNum(validateNum);
        if (!validate) {
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "인증번호 불일치", false));
        }

        int result = memberService.resetPwd(memberDto);
        if(result != 1){
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "비밀번호 수정 실패", false));
        }
        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "비밀번호 수정 성공", true));
    }

    @Operation(summary = "아이디 중복 확인", description = "아이디 중복 확인 메소드 입니다.")
    @GetMapping("/check_id")
    public ResponseEntity<ApiMessage<Boolean>> checkId(String id) {
        boolean idExist = memberService.isIdExist(id);
        return ResponseEntity.ok().body(new ApiMessage<>(HttpStatus.OK, "조회 성공", idExist));
    }

    @Operation(summary = "닉네임 중복 확인", description = "닉네임 중복 확인 메소드 입니다.")
    @GetMapping("/check_nick")
    public ResponseEntity<ApiMessage<Boolean>> checkNick(String nick) {
        boolean nickExist = memberService.isNickExist(nick);
        return ResponseEntity.ok().body(new ApiMessage<>(HttpStatus.OK, "조회 성공", nickExist));
    }


}
