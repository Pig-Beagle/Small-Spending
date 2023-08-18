package com.zzdd.smallspending.post;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

@Mapper
@MapperScan
public interface PostMapper {

    int insertPost(PostDto postDto);

    List<PostDto> selectListAll(PageDto pageDto);

    List<PostDto> selectListByNo(PageDto pageDto);

    int updateUserPost(PostDto postDto);

    int deletePost(PostDto postDto);

    List<StatisticsDto> selectStatistics(StatisticsRequestDto statisticsRequestDto);

    int insertReaction(ReactionDto reactionDto) throws DataIntegrityViolationException;

    int upsertReactionCnt(ReactionDto reactionDto);

    int deleteReaction(ReactionDto reactionDto);

    int decreaseReactionCnt(ReactionDto reactionDto);
}
