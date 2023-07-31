package com.zzdd.smallspending.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public List<PostDto> selectList(PageDto pageDto) {
        return postMapper.selectList(pageDto);
    }
}
