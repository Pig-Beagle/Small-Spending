package com.zzdd.smallspending.chat;

import com.zzdd.smallspending.common.ApiMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/room")
    public ResponseEntity<ApiMessage<ChatRoom>> getOrCreateChatRoom(int postNo) {
        ChatRoom chatRoom = chatService.findByRoomNo(postNo);
        if(chatRoom != null){
            return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "채팅방 조회 성공", chatRoom));
        }
        chatService.createChatRoom(postNo);
        chatRoom = chatService.findByRoomNo(postNo);
        if(chatRoom == null){
            return ResponseEntity.ok(new ApiMessage<>(HttpStatus.BAD_REQUEST, "채팅방 생성 실패", null));
        }
        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "채팅 조회 성공", chatRoom));
    }

    @MessageMapping("/pub/chat/room/{postNo}")
    @SendTo("/sub/chat/room/{postNo}")
    public ChatDto message(@DestinationVariable("postNo") int postNo, ChatDto chatDto) {
        chatService.saveMessage(postNo, chatDto);
        return chatDto;
    }

}
