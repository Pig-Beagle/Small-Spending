package com.zzdd.smallspending.post;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("StatisticsRequestDto")
public class StatisticsRequestDto {

    private int memberNo;
    private String date;

}
