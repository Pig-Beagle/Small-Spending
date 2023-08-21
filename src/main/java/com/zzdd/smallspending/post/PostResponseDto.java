package com.zzdd.smallspending.post;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("PostResponseDto")
public class PostResponseDto {

    @Data
    public static class Post{
        private int no;
        private int memberNo;
        private int amount;
        private int category;
        private String content;

    }

    @Data
    public static class Statistics {
        private int amount;
        private int category;
        private String date;
    }

}
