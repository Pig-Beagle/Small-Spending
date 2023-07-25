package com.zzdd.smallspending.token;

import lombok.Getter;

@Getter
public class RefreshToken {
    private final String memberId;
    private final String refreshToken;

    public RefreshToken(String memberId, String refreshToken) {
        this.memberId = memberId;
        this.refreshToken = refreshToken;
    }
}
