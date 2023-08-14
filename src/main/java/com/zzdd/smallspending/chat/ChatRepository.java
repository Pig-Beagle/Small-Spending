package com.zzdd.smallspending.chat;

public interface ChatRepository {

    ChatRoom findByRoomNo(int postNo);

    void createChatRoom(int postNo);

    void saveMessage(int postNo, ChatDto chatDto);
}
