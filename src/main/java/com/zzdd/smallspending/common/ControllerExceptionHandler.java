package com.zzdd.smallspending.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiMessage<List<ValidError>>> validateHandler(BindException e) {
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        List<ValidError> validErrors = new ArrayList<>();
        for (FieldError error : errors) {
            ValidError validError = new ValidError();
            validError.setField(error.getField());
            validError.setDefaultMessage(error.getDefaultMessage());
            validErrors.add(validError);
        }
        return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "입력값이 올바르지 않습니다.", validErrors));
    }

}
