package com.zzdd.smallspending.member;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;

@Data
@Alias("MemberDto")
public class MemberDto {

    private int no;
    private String id;
    private String pwd;
    private String name;
    private String nick;
    private Timestamp enrollDate;
    private String quitYN;


}
