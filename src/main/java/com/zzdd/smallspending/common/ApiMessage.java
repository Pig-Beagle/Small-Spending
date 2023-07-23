package com.zzdd.smallspending.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiMessage<T> {

    private HttpStatus status;
    private String message;
    private T data;

    public ApiMessage(HttpStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
