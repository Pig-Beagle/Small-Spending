package com.zzdd.smallspending.chat;

public interface ChatService {

    ChatRoom findByRoomNo(int postNo);
    void createChatRoom(int postNo);
    void saveMessage(int postNo, ChatDto chatDto);
}
