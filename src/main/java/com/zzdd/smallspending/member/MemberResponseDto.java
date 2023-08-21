package com.zzdd.smallspending.member;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Alias("MemberResponseDto")
public class MemberResponseDto {

    @Data
    public static class Member{
        private int no;
        private String id;
        private String pwd;
        private String name;
        private String nick;
        private String introduce;
        private LocalDateTime enrollDate;
        private String quitYN;
    }


}
