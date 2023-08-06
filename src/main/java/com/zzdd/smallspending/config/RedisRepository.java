package com.zzdd.smallspending.config;

import com.zzdd.smallspending.token.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class RedisRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public void save(RefreshToken refreshToken) {
        String key = "token_" + refreshToken.getMemberId();
        // redisDB에 저장
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, refreshToken.getRefreshToken());
        redisTemplate.expire(refreshToken.getRefreshToken(), 1209600000L, TimeUnit.MICROSECONDS);
    }

    public boolean delete(String memberId){
        String key = "token_" + memberId;
        if(redisTemplate.opsForValue().get(key) == null){
            return false;
        }
        return Boolean.TRUE.equals(redisTemplate.delete("token_" + memberId));
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

    public String getNum(String key){
        key = "num_" + key;
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public void setNumExpired(String key, String num, long expiredTime) {
        key = "num_" + key;
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        Duration duration = Duration.ofSeconds(expiredTime);
        valueOperations.set(key, num, duration);
    }

    public void deleteNum(String key) {
        key = "num_" + key;
        redisTemplate.delete(key);
    }



}
