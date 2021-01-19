package com.explore.mall.exception;

import com.explore.mall.api.IErrorCode;

/**
 * 自定义API异常
 * Created by liaoxr on 2021/1/17.
 */
public class ApiException extends RuntimeException{
    private IErrorCode errorCode;
    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
