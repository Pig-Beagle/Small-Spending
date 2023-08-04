package com.zzdd.smallspending.mail;

import com.zzdd.smallspending.config.RedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

@Repository
@RequiredArgsConstructor
public class EmailRepositoryImpl implements EmailRepository {

    private final JavaMailSender javaMailSender;
    private final RedisRepository redisRepository;

    public static final String num = createNum();
    @Value(" ${mail.username}")
    private String from;

    @Override
    public void sendEmail(String email){
        MimeMessage message = createMessage(email);
        javaMailSender.send(message);

        redisRepository.setNumExpired(email, num, 60 * 5L);
    }

    @Override
    public MimeMessage createMessage(String email){

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            mimeMessage.addRecipients(Message.RecipientType.TO, email);
            mimeMessage.setSubject("비밀번호 재설정 코드입니다.");

            String msg = "";
            msg+= "<div style='margin:20px;'>";
            msg+= "<h1> 안녕하세요 Small-Spending입니다. </h1>";
            msg+= "<br>";
            msg+= "<p>아래 인증코드를 입력해주세요<p>";
            msg+= "<p>감사합니다.<p>";
            msg+= "<br>";
            msg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
            msg+= "<h3 style='color:blue;'>비밀번호 재설정 인증 코드입니다.</h3>";
            msg+= "<div style='font-size:130%'>";
            msg+= "CODE : <strong>";
            msg+= num + "</strong><div><br/> ";
            msg+= "</div>";
            mimeMessage.setText(msg, "UTF-8", "html");
            mimeMessage.setFrom(new InternetAddress(from, "Small_Spending"));
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return mimeMessage;

    }

    public static String createNum(){

        SecureRandom randomNum = new SecureRandom();
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            str.append(randomNum.nextInt(9));
        }

        return str.toString();
    }

}
