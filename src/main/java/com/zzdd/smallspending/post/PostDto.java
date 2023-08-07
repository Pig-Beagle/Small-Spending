package com.zzdd.smallspending.post;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("PostDto")
public class PostDto {

    private int no;
    private int memberNo;
    private int amount;
    private int category;
    private String content;
    private String openYN;
    private String deleteYN;

}
