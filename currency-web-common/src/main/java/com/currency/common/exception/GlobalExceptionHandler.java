package com.currency.common.exception;

import com.currency.exception.BusinessException;
import com.currency.exception.IllegalRequestException;
import com.currency.utils.ResultHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public ResultHandler baseException(BusinessException e) {
        return ResultHandler.error(e.getMessage());
    }

    /**
     * 非法请求异常
     */
    @ExceptionHandler(IllegalRequestException.class)
    public ResultHandler businessException(IllegalRequestException e) {
        return ResultHandler.error(e.getMessage());
    }


}
