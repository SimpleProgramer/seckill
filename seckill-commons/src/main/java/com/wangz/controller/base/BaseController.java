package com.wangz.controller.base;

import com.wangz.enums.ErrorCode;
import com.wangz.enums.SystemConst;
import com.wangz.exceptions.ApiException;
import com.wangz.exceptions.BusinessException;
import com.wangz.models.resp.ApiResponse;
import com.wangz.utils.DateTimeUtil;
import com.wangz.utils.MDCUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 验证器 基本的控制器
 * @author created by Singer email:313402703@qq.com
 * @time 2018/9/29
 * @description 验证器 基本的控制器
 */
@RestController
@Slf4j
public class BaseController {

    @Resource
    private HttpServletRequest httpServletRequest;


    /**
     * 拿到当前请求用户信息Header
     * @return
     */
    public String currentRequestUserHeader(){
        return httpServletRequest.getHeader(SystemConst.SYSTEMAPI_USER_INFO_HEADER);
    }


    /**
     * 生成APIResponse
     * @return
     */
    public ApiResponse apiResponse(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMsg(ErrorCode.SUCCESS.getMessage());
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setRequestSeqNo(MDCUtils.getOrGenMsgId());
        apiResponse.setResponseTime(DateTimeUtil.currentDateToStr(""));
        return apiResponse;
    }


    /**
     * GET请求的返回封装
     * @param object
     * @return
     */
    public ApiResponse apiResponse(Object object){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMsg(ErrorCode.SUCCESS.getMessage());
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setRequestSeqNo(MDCUtils.getOrGenMsgId());
        apiResponse.setResponseTime(DateTimeUtil.currentDateToStr(""));
        apiResponse.setData(object);
        return apiResponse;
    }


    /**
     * 处理基本的异常信息
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler
    public ApiResponse<Map<String,Object>> handleBaseException(HttpServletRequest request, Exception e){
        ApiResponse apiResponse = apiResponse();
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>"+request.getRequestURI()+"<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) return noHandlerFound(apiResponse, e);
        if(e instanceof MethodArgumentNotValidException) return methodArgumentNotValidExceptionHandler(apiResponse,e);
        if (e instanceof ApiException) return apiExceptionHandlerMethod(apiResponse,e);
        if (e instanceof BusinessException) return businessExceptionHandlerMethod(apiResponse,e);
        return otherExceptionHandler(apiResponse,e);
    }

    private ApiResponse<String> noHandlerFound(ApiResponse<String> apiResponse,Exception e) {
        apiResponse.setCode("500");
        apiResponse.setMsg("访问的地址被丢到了异次元空间~");
        apiResponse.setData("换个地址试试吧~");
        return apiResponse;
    }


    /**
     * 其他异常处理器
     * @param resp
     * @param e
     * @return
     */
    private ApiResponse<Map<String, Object>> otherExceptionHandler(ApiResponse<Map<String, Object>> resp,Exception e){
        resp.setCode(ErrorCode.ERROR.getCode());
        resp.setMsg(ErrorCode.ERROR.getMessage());
        log.error("发生系统异常:"+e.getMessage(),e);
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
     * API异常处理器
     * @param resp
     * @param e
     * @author Singer
     */
    private ApiResponse<Map<String, Object>> apiExceptionHandlerMethod(ApiResponse<Map<String, Object>> resp,Exception e){
        ApiException se = (ApiException) e;
        resp.setCode(se.getErrorCode());
        resp.setMsg(se.getErrorMessage());
        return resp;
    }

    /**
     * 处理参数格式验证不通过的时候的处理器
     * @param resp
     * @param e
     */
    public ApiResponse methodArgumentNotValidExceptionHandler(ApiResponse resp, Exception e){

        MethodArgumentNotValidException be = (MethodArgumentNotValidException) e;
        StringBuilder errStr = new StringBuilder();
        if(be.getBindingResult().getErrorCount()>0){
            for (ObjectError oe : be.getBindingResult().getAllErrors()) {
                errStr.append(oe.getDefaultMessage()).append(";");
            }
        }
        resp.setCode(ErrorCode.PARAM_ERROR.getCode());
        resp.setMsg(errStr.length()>0?errStr.toString():ErrorCode.PARAM_ERROR.getMessage());
        return resp;
    }

}
