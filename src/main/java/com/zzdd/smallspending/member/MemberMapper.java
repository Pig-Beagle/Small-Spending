package com.zzdd.smallspending.member;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;


@Mapper
@MapperScan
public interface MemberMapper {
    int insertMember(MemberDto memberDto);

    int deleteOneMember(MemberDto memberDto);

    int updateMyPage(MemberDto memberDto);

    int updateIntroduce(@Param("no") int userNo, @Param("introduce") String introduce);

    int updatePwd(MemberDto memberDto);

    MemberDto selectOneById(String id);

    MemberDto selectOneByNick(String nick);

    MemberDto selectOneByNo(int userNo);
}
