package com.zzdd.smallspending.mail;

import com.zzdd.smallspending.common.ApiMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send_mail")
@RequiredArgsConstructor
@Slf4j
public class MailController {

    private final EmailService emailService;

    @PostMapping("/password")
    public ResponseEntity<ApiMessage<MailDto>> pwdMail(String email){
        emailService.sendEmail(email);
        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "메일 전송 성공", null));
    }


}
