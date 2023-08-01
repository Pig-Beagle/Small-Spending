package com.zzdd.smallspending.member;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@Mapper
@MapperScan
public interface MemberMapper {
    int insertMember(MemberDto memberDto);

    int deleteOneMember(MemberDto memberDto);

    List<MemberDto> selectMyPage(Integer memberDto);

    MemberDto selectOneById(String id);

    MemberDto selectOneByNick(String nick);

}
