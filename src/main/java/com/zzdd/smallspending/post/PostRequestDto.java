package com.zzdd.smallspending.post;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Alias("PostRequestDto")
public class PostRequestDto {

    @Data
    public static class Post {
        private int no;
        private int memberNo;
        @NotNull(message = "금액은 필수 입력 값 입니다.")
        private int amount;
        @NotNull(message = "카테고리는 필수 선택 입니다.")
        private int category;
        private String content;
        @NotBlank(message = "오픈 여부를 선택해 주세요.")
        private String openYN;
    }

    @Data
    public static class Delete{
        private int no;
        private int memberNo;
    }

    @Data
    public static class Page {
        private int page;
        private int size;
        private int memberNo;
        private int currentMemberNo;

        public int getOffset(){
            return (page - 1) * size;
        }

    }

    @Data
    public static class Reaction {
        private int no;
        private int memberNo;
        private int postNo;
        private int reaction;
    }

    @Data
    public static class Statistics {
        private int memberNo;
        private String date;
    }

}
