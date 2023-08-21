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
    public int insertPost(PostRequestDto.Post postDto) {
        return postMapper.insertPost(postDto);
    }

    @Override
    public List<PostResponseDto.Post> selectListAll(PostRequestDto.Page pageDto) {
        return postMapper.selectListAll(pageDto);
    }

    @Override
    public List<PostResponseDto.Post> selectListByNo(PostRequestDto.Page pageDto) {
        return postMapper.selectListByNo(pageDto);
    }

    @Override
    public int updateUserPost(PostRequestDto.Post postDto) {
        return postMapper.updateUserPost(postDto);
    }

    @Override
    public int deletePost(PostRequestDto.Delete postDto) {
        return postMapper.deletePost(postDto);
    }

    @Override
    public List<PostResponseDto.Statistics> selectStatistics(PostRequestDto.Statistics statistics) {
        return postMapper.selectStatistics(statistics);
    }

    @Override
    public int insertReaction(PostRequestDto.Reaction reactionDto) {
        try {
            return postMapper.insertReaction(reactionDto);
        } catch (DataIntegrityViolationException e) {
            return 0;
        }
    }

    @Override
    public int upsertReactionCnt(PostRequestDto.Reaction reactionDto) {
        return postMapper.upsertReactionCnt(reactionDto);
    }

    @Override
    public int deleteReaction(PostRequestDto.Reaction reactionDto) {
        return postMapper.deleteReaction(reactionDto);
    }

    @Override
    public int decreaseReactionCnt(PostRequestDto.Reaction reactionDto) {
        return postMapper.decreaseReactionCnt(reactionDto);
    }
}
