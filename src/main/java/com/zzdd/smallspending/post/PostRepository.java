package com.zzdd.smallspending.post;

import java.util.List;

public interface PostRepository {

    int insertPost(PostDto postDto);

    List<PostDto> selectList(PageDto pageDto);
}
