package com.zzdd.smallspending.member;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
@MapperScan
public interface MemberMapper {
    int insertMember(MemberDto memberDto);

    MemberDto selectOneById(String id);

    MemberDto selectOneByNick(String nick);

    MemberDto selectOneMember(MemberDto memberDto);
}
