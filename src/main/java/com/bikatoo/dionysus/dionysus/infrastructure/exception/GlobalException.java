package com.bikatoo.dionysus.dionysus.infrastructure.exception;

import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {

    protected int status;
    protected String message;
    protected String userHint;

    public GlobalException(String message) {
        this.message = message;
    }

    public GlobalException(int status, String message, String userHint) {
        this.status = status;
        this.message = message;
        this.userHint = userHint;
    }

    public GlobalException(String message, String userHint) {
        this.status = 400;
        this.message = message;
        this.userHint = userHint;
    }
}
