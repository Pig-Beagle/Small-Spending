package com.zzdd.smallspending.chat;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ChatRepositoryImpl implements ChatRepository{

    private final Map<Integer, ChatRoom> chatRooms = new HashMap<>();

    @Override
    public ChatRoom findByRoomNo(int postNo) {
        return chatRooms.get(postNo);
    }

    @Override
    public void createChatRoom(int postNo) {
        chatRooms.put(postNo, new ChatRoom(postNo));
    }

    @Override
    public void saveMessage(int postNo, ChatDto chatDto) {
        ChatRoom chatRoom = chatRooms.get(postNo);
        chatRoom.getLog().add(chatDto);
    }


}
