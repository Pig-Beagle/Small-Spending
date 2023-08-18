package com.zzdd.smallspending.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository{

    private final PostMapper postMapper;

    @Override
    public int insertPost(PostDto postDto) {
        return postMapper.insertPost(postDto);
    }

    @Override
    public List<PostDto> selectListAll(PageDto pageDto) {
        return postMapper.selectListAll(pageDto);
    }

    @Override
    public List<PostDto> selectListByNo(PageDto pageDto) {
        return postMapper.selectListByNo(pageDto);
    }

    @Override
    public int updateUserPost(PostDto postDto) {
        return postMapper.updateUserPost(postDto);
    }

    @Override
    public int deletePost(PostDto postDto) {
        return postMapper.deletePost(postDto);
    }

    @Override
    public  List<StatisticsDto> selectStatistics(StatisticsRequestDto statisticsRequestDto) {
        return postMapper.selectStatistics(statisticsRequestDto);
    }

    @Override
    public int insertReaction(ReactionDto reactionDto) {
        try {
            return postMapper.insertReaction(reactionDto);
        } catch (DataIntegrityViolationException e) {
            return 0;
        }
    }

    @Override
    public int upsertReactionCnt(ReactionDto reactionDto) {
        return postMapper.upsertReactionCnt(reactionDto);
    }

    @Override
    public int deleteReaction(ReactionDto reactionDto) {
        return postMapper.deleteReaction(reactionDto);
    }

    @Override
    public int decreaseReactionCnt(ReactionDto reactionDto) {
        return postMapper.decreaseReactionCnt(reactionDto);
    }
}
