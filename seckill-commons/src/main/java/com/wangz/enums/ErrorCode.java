package com.wangz.enums;

/**
 * 系统错误码枚举
 * @author created by Singer email:313402703@qq.com
 * @time 2018/9/29
 * @description
 */
public enum ErrorCode {

	SUCCESS("200","成功"),
    PARAM_ERROR("601","参数格式错误"),
    ERROR("500","业务处理异常"),
    LOGIN_ERROR_CODE("501","登录失效,需要验证登录"),

    NOTIFYERROR("6101","业务系统回调无响应"),
    TOKENNOTEXISTS("6102","ACCESS_TOKEN不存在"),

    CHECKSIGNFAIL("1013","验签失败"),
    CHECKPARAMFAIL("1014","验参失败"),
    NO_USER_CODE("1015","用户不存在"),
    NO_PRODUCT_CODE("1016","产品不存在"),
    STORE_NOT_ENOUGH("1017","产品库存不足"),
    NOTENOUGH("5201","余额不足"),
    ORDERPAID("5202","商户订单已支付"),
    ORDERCLOSED("5203","订单已关闭"),
    PAYPARAMERROR("5206","微信支付参数错误"),
    SYSTEMERROR("5204","微信支付系统错误"),
    SIGNERROR("5205","签名异常"),
	
	WX_USER_INFO_ERROR("6001","获取微信用户信息失败"),
	LOGIN_NEED_CODE("404","需要登录"),
	
	WX_REQUEST_ERROR("7001","请求微信接口失败");

    private String code;

    private String message;

    private ErrorCode(String code,String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
