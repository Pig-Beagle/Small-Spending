package com.zzdd.smallspending.token;

import lombok.Getter;

@Getter
public class RefreshToken {
    private final int userNo;
    private final String refreshToken;

    public RefreshToken(int userNo, String refreshToken) {
        this.userNo = userNo;
        this.refreshToken = refreshToken;
    }
}
