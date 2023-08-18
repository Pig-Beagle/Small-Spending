package com.zzdd.smallspending.post;

import java.util.List;

public interface PostRepository {

    int insertPost(PostDto postDto);

    List<PostDto> selectListAll(PageDto pageDto);

    List<PostDto> selectListByNo(PageDto pageDto);

    int updateUserPost(PostDto postDto);

    int deletePost(PostDto postDto);

    List<StatisticsDto> selectStatistics(StatisticsRequestDto statisticsRequestDto);

    int insertReaction(ReactionDto reactionDto);

    int upsertReactionCnt(ReactionDto reactionDto);

    int deleteReaction(ReactionDto reactionDto);

    int decreaseReactionCnt(ReactionDto reactionDto);
}
