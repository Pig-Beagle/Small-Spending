package com.zzdd.smallspending.chat;

import org.springframework.data.domain.Page;

public interface ChatService {

    Page<ChatDto> findByRoomNo(int postNo, int page);

    void createChatRoom(int postNo);

    ChatDto saveMessage(int postNo, ChatDto chatDto);

    ChatDto updateChat(String authorization, ChatDto chatDto);

    int deleteChat(String authorization, ChatDto chatDto);
}
