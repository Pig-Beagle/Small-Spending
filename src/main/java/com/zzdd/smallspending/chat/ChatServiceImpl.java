package com.zzdd.smallspending.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{

    private final ChatRepository chatRepository;

    @Override
    public ChatRoom findByRoomNo(int postNo) {
        return chatRepository.findByRoomNo(postNo);
    }

    @Override
    public void createChatRoom(int postNo) {
        chatRepository.createChatRoom(postNo);
    }

    @Override
    public void saveMessage(int postNo, ChatDto chatDto) {
        chatRepository.saveMessage(postNo, chatDto);
    }
}
