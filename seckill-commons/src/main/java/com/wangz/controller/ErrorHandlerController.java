package com.wangz.controller;

import com.wangz.enums.ErrorCode;
import com.wangz.exceptions.BusinessException;
import com.wangz.models.resp.ApiResponse;
import com.wangz.utils.DateTimeUtil;
import com.wangz.utils.MDCUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangzun
 * @version 2018/11/2 下午4:17
 * @desc
 */
@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ApiResponse<Object> defaultErrorHandler(HttpServletRequest request, Exception e) {
        ApiResponse<Object> resp = apiResponse();
        if(e instanceof BusinessException) return businessExceptionHandlerMethod(resp,e);
        if(e instanceof HttpRequestMethodNotSupportedException) return methodNotSupportHandler(resp,e);
        if(e instanceof org.springframework.web.servlet.NoHandlerFoundException) noHandlerFound(resp,e);
        return resp;
    }

    private ApiResponse<Object> noHandlerFound(ApiResponse<Object> resp,Exception e) {
        resp.setCode("500");
        resp.setMsg("请求被吃掉了。换一个地址试试吧~");
        resp.setData("Got an wrong request,");
        return resp;
    }

    private ApiResponse<Object> methodNotSupportHandler(ApiResponse<Object> resp,Exception e) {
        resp.setData("GET or POST?");
        resp.setMsg("请求好像有点小问题");
        resp.setCode("500");
        return resp;
    }
    /**
     * 业务异常处理器
     * @param resp
     * @param e
     * @return
     */
    private ApiResponse<Object> businessExceptionHandlerMethod(ApiResponse<Object> resp,	Exception e) {
        BusinessException se = (BusinessException) e;
        resp.setCode(se.getErrorCode());
        resp.setMsg(se.getErrorMessage());
        resp.setData(se.getData());
        return resp;
    }

    /**
     * 生成APIResponse
     * @return
     */
    public ApiResponse apiResponse(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMsg(ErrorCode.ERROR.getMessage());
        apiResponse.setCode(ErrorCode.ERROR.getCode());
        apiResponse.setRequestSeqNo(MDCUtils.getOrGenMsgId());
        apiResponse.setResponseTime(DateTimeUtil.currentDateToStr(""));
        return apiResponse;
    }

}
