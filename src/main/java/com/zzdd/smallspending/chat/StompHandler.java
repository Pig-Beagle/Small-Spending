package com.zzdd.smallspending.chat;

import com.zzdd.smallspending.token.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StompHandler implements ChannelInterceptor {

    private final JwtUtil jwtUtil;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        if (accessor.getCommand() == StompCommand.CONNECT) {
            String authorization = accessor.getFirstNativeHeader("Authorization");
            if(authorization == null){
                throw new IllegalArgumentException("토큰이 없습니다.");
            }

            String token = authorization.split(" ")[1];

            if(jwtUtil.isExpired(token)){
                throw new IllegalArgumentException("토큰이 만료되었습니다.");
            }
        }
        return message;
    }
}
