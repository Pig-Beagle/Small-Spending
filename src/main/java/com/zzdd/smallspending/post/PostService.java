package com.zzdd.smallspending.post;

import java.util.List;

public interface PostService {
    int write(String authorization, PostRequestDto.Post postDto);

    List<PostResponseDto.Post> listAll(PostRequestDto.Page pageDto);

    List<PostResponseDto.Post> listByNo(String authorization, PostRequestDto.Page pageDto);

    int editUserPost(String authorization, PostRequestDto.Post postDto);

    int deleteUserPost(String authorization, PostRequestDto.Delete postDto);

    List<PostResponseDto.Statistics> userStatistics(String authorization, PostRequestDto.Statistics statistics);

    int addReaction(String authorization, PostRequestDto.Reaction reactionDto);

    int deleteReaction(String authorization, PostRequestDto.Reaction reactionDto);
}
