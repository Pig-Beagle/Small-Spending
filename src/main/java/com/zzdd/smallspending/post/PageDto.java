package com.zzdd.smallspending.post;

import lombok.Data;
import org.springframework.data.annotation.TypeAlias;

@Data
@TypeAlias("PageDto")
public class PageDto {

    private int page;
    private int size;

    public int getOffset(){
        return (page - 1) * size;
    }

}
