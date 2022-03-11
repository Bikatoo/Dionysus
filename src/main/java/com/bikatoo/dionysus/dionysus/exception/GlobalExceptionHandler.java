package com.bikatoo.dionysus.dionysus.exception;

import com.bikatoo.dionysus.dionysus.interfaces.DTO.CommonResult;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    @ResponseBody
    public Object handleException(@NonNull GlobalException e) {
        return CommonResult.error(e.getStatus(), e.getMessage(), e.getUserHint());
    }
}
