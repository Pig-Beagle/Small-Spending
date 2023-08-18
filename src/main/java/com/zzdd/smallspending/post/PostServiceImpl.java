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
    public int write(String authorization, PostDto postDto) {
        String token = authorization.split(" ")[1];
        Integer userNo = jwtUtil.getuserNo(token);
        postDto.setMemberNo(userNo);

        return postRepository.insertPost(postDto);
    }

    @Override
    public List<PostDto> listAll(PageDto pageDto) {
        return postRepository.selectListAll(pageDto);
    }

    @Override
    public List<PostDto> listByNo(String authorization, PageDto pageDto) {
        if (authorization != null) {
            String token = authorization.split(" ")[1];
            Integer userNo = jwtUtil.getuserNo(token);
            pageDto.setCurrentMemberNo(userNo);
        }
        return postRepository.selectListByNo(pageDto);
    }

    @Override
    public int editUserPost(String authorization, PostDto postDto) {
        String token = authorization.split(" ")[1];
        Integer userNo = jwtUtil.getuserNo(token);
        postDto.setMemberNo(userNo);

        return postRepository.updateUserPost(postDto);
    }

    @Override
    public int deleteUserPost(String authorization, PostDto postDto) {
        String token = authorization.split(" ")[1];
        Integer userNo = jwtUtil.getuserNo(token);
        postDto.setMemberNo(userNo);

        return postRepository.deletePost(postDto);
    }

    @Override
    public List<StatisticsDto> userStatistics(String authorization, StatisticsRequestDto statisticsRequestDto) {
        String token = authorization.split(" ")[1];
        Integer userNo = jwtUtil.getuserNo(token);
        statisticsRequestDto.setMemberNo(userNo);

        return postRepository.selectStatistics(statisticsRequestDto);
    }

}
