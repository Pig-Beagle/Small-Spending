package com.zzdd.smallspending.post;

import java.util.List;

public interface PostService {
    int write(String authorization, PostDto postDto);

    List<PostDto> listAll(PageDto pageDto);

    List<PostDto> listByNo(String authorization, PageDto pageDto);

    int editUserPost(String authorization, PostDto postDto);

    int deleteUserPost(String authorization, PostDto postDto);
}
