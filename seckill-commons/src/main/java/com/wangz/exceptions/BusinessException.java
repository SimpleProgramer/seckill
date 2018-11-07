package com.wangz.exceptions;

import com.wangz.enums.ErrorCode;
import lombok.Data;

/**
 * 系统业务异常类
 * @author created by Singer email:313402703@qq.com
 * @time 2018/9/29
 * @description 系统业务异常类
 */
@Data
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1656492381432429706L;

    private String errorCode;

    private String errorMessage;

    private Object data;

    public BusinessException(String errorCode, String errorMessage) {
        super(errorCode +":"+errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    public BusinessException(ErrorCode errorCode) {
        super(errorCode +":"+errorCode.getMessage());
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getMessage();
    }

    public BusinessException(String errorCode, String errorMessage,Object data) {
        super(errorCode +":"+errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.data = data;
    }
}
