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
        // redisDB에 저장
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(refreshToken.getRefreshToken(), String.valueOf(refreshToken.getUserNo()));
        redisTemplate.expire(refreshToken.getRefreshToken(), 1209600000L, TimeUnit.MICROSECONDS);
    }

    public boolean delete(String refreshToken){
        if(redisTemplate.opsForValue().get(refreshToken) == null){
            return false;
        }
        return Boolean.TRUE.equals(redisTemplate.delete(refreshToken));
    }

    public Optional<RefreshToken> findById(String refreshToken) {
        // redisDB에서 RefreshToken 검색
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String value = valueOperations.get(refreshToken);

        if (Objects.isNull(value)) {
            return Optional.empty();
        }

        int userNo = Integer.parseInt(value);

        return Optional.of(new RefreshToken(userNo, refreshToken));
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
