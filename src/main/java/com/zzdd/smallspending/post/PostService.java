package com.zzdd.smallspending.post;

import java.util.List;

public interface PostService {
    int write(String authorization, PostDto postDto);

    List<PostDto> list(PageDto pageDto);
}
