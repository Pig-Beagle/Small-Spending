package com.zzdd.smallspending.chat;

import com.zzdd.smallspending.token.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService{

    private final ChatRepository chatRepository;
    private final MongoDBRepository mongoDBRepository;
    private final JwtUtil jwtUtil;

    @Override
    public Page<ChatDto> findByRoomNo(int postNo, int page) {
        Pageable pageRequest = PageRequest.of(page, 30);
        return mongoDBRepository.findByRoomNo(postNo, pageRequest);

    }

    @Override
    public void createChatRoom(int postNo) {
        chatRepository.createChatRoom(postNo);
    }

    @Override
    public ChatDto saveMessage(int postNo, ChatDto chatDto) {
        chatDto.setRoomNo(postNo);
        return mongoDBRepository.save(chatDto);
    }

    @Override
    public ChatDto updateChat(String authorization, ChatDto chatDto) {
        String token = authorization.split(" ")[1];
        Integer userNo = jwtUtil.getuserNo(token);

        if(!userNo.equals(chatDto.getMemberNo())){
            return null;
        }

        Optional<ChatDto> chat = mongoDBRepository.findById(chatDto.getId());

        if (chat.isEmpty()) {
            return null;
        }

        chat.get().setMessage(chatDto.getMessage());
        return mongoDBRepository.save(chat.get());
    }

    @Override
    public int deleteChat(String authorization, ChatDto chatDto) {
        String token = authorization.split(" ")[1];
        Integer userNo = jwtUtil.getuserNo(token);

        if(!userNo.equals(chatDto.getMemberNo())){
            return -1;
        }

        try {
            mongoDBRepository.deleteById(chatDto.getId());
            return 1;
        }catch (Exception e){
            return -1;
        }
    }
}
