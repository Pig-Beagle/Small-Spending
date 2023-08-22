package com.zzdd.smallspending.member;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;


@Mapper
@MapperScan
public interface MemberMapper {
    int insertMember(MemberRequestDto.SignUp memberDto);

    int insertOauthMember(MemberRequestDto.OauthSignUp memberDto);

    int deleteOneMember(int userNo);

    int updateNick(MemberRequestDto.EditNick memberDto);

    int updateIntroduce(MemberRequestDto.EditIntroduce memberDto);

    int updatePwd(MemberRequestDto.ResetPwd memberDto);

    MemberResponseDto.Member selectOneById(String id);

    MemberResponseDto.Member selectOneByNick(String nick);

    MemberResponseDto.Member selectOneByNo(int userNo);

}
