package com.zzdd.smallspending.post;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("ReactionDto")
public class ReactionDto {
    private int no;
    private int memberNo;
    private int postNo;
    private int reaction;
}
