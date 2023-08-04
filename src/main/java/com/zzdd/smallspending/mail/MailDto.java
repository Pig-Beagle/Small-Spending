package com.zzdd.smallspending.mail;

import lombok.Data;

@Data
public class MailDto {

    private String to;
    private String subject;
    private String content;

}
