package com.zzdd.smallspending.member;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;


@Mapper
@MapperScan
public interface MemberMapper {
    int insertMember(MemberDto memberDto);

    int deleteOneMember(MemberDto memberDto);

    int updatePwd(MemberDto memberDto);

    MemberDto selectOneById(String id);

    MemberDto selectOneByNick(String nick);

}
