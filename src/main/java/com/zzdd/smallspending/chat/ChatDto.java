package com.zzdd.smallspending.chat;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "chat")
public class ChatDto {
    private String id;
    private int roomNo;
    private int memberNo;
    private String message;

}
