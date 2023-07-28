package com.zzdd.smallspending.token;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
public class RefreshTokenRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public RefreshTokenRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(RefreshToken refreshToken) {
        // redisDB에 저장
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(refreshToken.getMemberId(), refreshToken.getRefreshToken());
        redisTemplate.expire(refreshToken.getRefreshToken(), 1209600000L, TimeUnit.MICROSECONDS);
    }

    public boolean delete(String memberId){
        if(redisTemplate.opsForValue().get(memberId) == null){
            return false;
        }
       return Boolean.TRUE.equals(redisTemplate.delete(memberId));
    }

    public Optional<RefreshToken> findById(String refreshToken) {
        // redisDB에서 RefreshToken 검색
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String memberId = valueOperations.get(refreshToken);

        if (Objects.isNull(memberId)) {
            return Optional.empty();
        }

        return Optional.of(new RefreshToken(refreshToken, memberId));
    }
}
