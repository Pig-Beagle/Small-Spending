package com.zzdd.smallspending.post;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("StatisticsDto")
public class StatisticsDto {

    private int amount;
    private int category;
    private String date;

}
