package com.zzdd.smallspending.post;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@Mapper
@MapperScan
public interface PostMapper {

    int insertPost(PostDto postDto);

    List<PostDto> selectListAll(PageDto pageDto);

    List<PostDto> selectListByNo(PageDto pageDto);

    int updateUserPost(PostDto postDto);

    int deletePost(PostDto postDto);
}
