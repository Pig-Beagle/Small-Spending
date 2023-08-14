package com.zzdd.smallspending.chat;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatRoom {
    private final int roomNo;
    private final List<ChatDto> log;

    public ChatRoom(int roomNo) {
        this.roomNo = roomNo;
        this.log = new ArrayList<>();
    }
}
