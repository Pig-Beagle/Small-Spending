package com.zzdd.smallspending.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Alias("MemberRequestDto")
public class MemberRequestDto {

    @Data
    public static class SignUp{
        @NotBlank(message = "아이디는 필수 입력 값입니다.")
        @Email(message = "이메일 형식으로 입력해주세요.")
        private String id;
        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}",
                message = "비밀번호는 8자 이상 영문 대문자, 소문자, 숫자, 특수문자를 사용하세요.")
        private String pwd;
        @NotBlank(message = "이름은 필수 입력 값입니다.")
        private String name;
        @NotBlank(message = "닉네임은 필수 입력 값입니다.")
        @Pattern(regexp = "^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$",
                message = "닉네임은 2자 이상 16자 이하, 한글, 영어 또는 숫자를 사용하세요.")
        private String nick;
    }

    @Data
    @AllArgsConstructor
    public static class OauthSignUp{
        @NotBlank(message = "아이디는 필수 입력 값입니다.")
        @Email(message = "이메일 형식으로 입력해주세요.")
        private String id;
        @NotBlank(message = "이름은 필수 입력 값입니다.")
        private String name;
    }

    @Data
    public static class Login{
        @NotBlank(message = "아이디는 필수 입력 값입니다.")
        @Email(message = "이메일 형식으로 입력해주세요.")
        private String id;
        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}",
                message = "비밀번호는 8자 이상 영문 대문자, 소문자, 숫자, 특수문자를 사용하세요.")
        private String pwd;
    }

    @Data
    public static class EditNick {
        private int no;
        @NotBlank(message = "닉네임은 필수 입력 값입니다.")
        @Pattern(regexp = "^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$",
                message = "닉네임은 2자 이상 16자 이하, 한글, 영어 또는 숫자를 사용하세요.")
        private String nick;
        private String introduce;
    }

    @Data
    public static class EditIntroduce {
        private int no;
        private String introduce;
    }

    @Data
    public static class ResetPwd{
        private String id;
        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}",
                message = "비밀번호는 8자 이상 영문 대문자, 소문자, 숫자, 특수문자를 사용하세요.")
        private String pwd;
    }

}
