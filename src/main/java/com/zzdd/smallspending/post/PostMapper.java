package com.zzdd.smallspending.post;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

@Mapper
@MapperScan
public interface PostMapper {

    int insertPost(PostRequestDto.Post postDto);

    List<PostResponseDto.Post> selectListAll(PostRequestDto.Page pageDto);

    List<PostResponseDto.Post> selectListByNo(PostRequestDto.Page pageDto);

    int updateUserPost(PostRequestDto.Post postDto);

    int deletePost(PostRequestDto.Delete postDto);

    List<PostResponseDto.Statistics> selectStatistics(PostRequestDto.Statistics statistics);

    int insertReaction(PostRequestDto.Reaction reactionDto) throws DataIntegrityViolationException;

    int upsertReactionCnt(PostRequestDto.Reaction reactionDto);

    int deleteReaction(PostRequestDto.Reaction reactionDto);

    int decreaseReactionCnt(PostRequestDto.Reaction reactionDto);
}
