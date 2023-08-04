package com.zzdd.smallspending.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

public interface EmailRepository {

    void sendEmail(String email);

    MimeMessage createMessage(String email) throws MessagingException, UnsupportedEncodingException;


}
