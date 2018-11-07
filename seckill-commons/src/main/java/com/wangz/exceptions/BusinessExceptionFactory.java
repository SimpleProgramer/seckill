package com.wangz.exceptions;


import com.wangz.enums.ErrorCode;

/**
 * 
 * @author qijiang
 * @date 2018/10/9 下午5:00
 * @Param 
 * @return 
 * @Description
 */
public class BusinessExceptionFactory {

    /**
     * 
     * @author qijiang
     * @date 2018/10/9 下午5:45
     * @Param [msg, data]
     * @return cn.lefull.common.exception.BusinessException
     * @Description
     */
    public static BusinessException  BusinessException(String msg, Object data){
        return new BusinessException(ErrorCode.ERROR.getCode(), msg, data);
    }

    /**
     * 
     * @author qijiang
     * @date 2018/10/9 下午5:04
     * @Param [msg]
     * @return cn.lefull.common.exception.BusinessException
     * @Description
     */
    public static BusinessException  BusinessException(String msg){
        return new BusinessException(ErrorCode.ERROR.getCode(), msg);
    }

    /**
     * 
     * @author qijiang
     * @date 2018/10/9 下午5:04
     * @Param [msg]
     * @return cn.lefull.common.exception.BusinessException
     * @Description
     */
    public static BusinessException LoginException(String msg){
        return new BusinessException(ErrorCode.LOGIN_ERROR_CODE.getCode(), msg);
    }
}
