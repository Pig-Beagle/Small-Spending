package com.zzdd.smallspending.post;

import com.zzdd.smallspending.token.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final JwtUtil jwtUtil;

    @Override
    public int write(String authorization, PostRequestDto.Post postDto) {
        String token = authorization.split(" ")[1];
        Integer userNo = jwtUtil.getuserNo(token);
        postDto.setMemberNo(userNo);

        return postRepository.insertPost(postDto);
    }

    @Override
    public List<PostResponseDto.Post> listAll(PostRequestDto.Page pageDto) {
        return postRepository.selectListAll(pageDto);
    }

    @Override
    public List<PostResponseDto.Post> listByNo(String authorization, PostRequestDto.Page pageDto) {
        if (authorization != null) {
            String token = authorization.split(" ")[1];
            Integer userNo = jwtUtil.getuserNo(token);
            pageDto.setCurrentMemberNo(userNo);
        }
        return postRepository.selectListByNo(pageDto);
    }

    @Override
    public int editUserPost(String authorization, PostRequestDto.Post postDto) {
        String token = authorization.split(" ")[1];
        Integer userNo = jwtUtil.getuserNo(token);
        postDto.setMemberNo(userNo);

        return postRepository.updateUserPost(postDto);
    }

    @Override
    public int deleteUserPost(String authorization, PostRequestDto.Delete postDto) {
        String token = authorization.split(" ")[1];
        Integer userNo = jwtUtil.getuserNo(token);
        postDto.setMemberNo(userNo);

        return postRepository.deletePost(postDto);
    }

    @Override
    public List<PostResponseDto.Statistics> userStatistics(String authorization, PostRequestDto.Statistics statistics) {
        String token = authorization.split(" ")[1];
        Integer userNo = jwtUtil.getuserNo(token);
        statistics.setMemberNo(userNo);

        return postRepository.selectStatistics(statistics);
    }

    @Override
    public int addReaction(String authorization, PostRequestDto.Reaction reactionDto) {
        String token = authorization.split(" ")[1];
        Integer userNo = jwtUtil.getuserNo(token);
        reactionDto.setMemberNo(userNo);

        int result = postRepository.insertReaction(reactionDto);

        if(result == 0) {
            return 0;
        }

        postRepository.upsertReactionCnt(reactionDto);

        return result;
    }

    @Override
    public int deleteReaction(String authorization, PostRequestDto.Reaction reactionDto) {
        String token = authorization.split(" ")[1];
        Integer userNo = jwtUtil.getuserNo(token);
        reactionDto.setMemberNo(userNo);

        int result = postRepository.deleteReaction(reactionDto);
        if(result == 0) {
            return 0;
        }
        postRepository.decreaseReactionCnt(reactionDto);
        return result;
    }

}
