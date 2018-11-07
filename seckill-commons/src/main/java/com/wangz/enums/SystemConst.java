package com.wangz.enums;

/**
 * @Auther: qijiang
 * @Date: 2018/9/29 10:04
 * @Description:系统常量
 */

public class SystemConst {
    /********************---系统全局变量----****************/
    public final static String SYSTEMAPI_USER_INFO_HEADER = "SYSTEMAPI_USER_INFO_HEADER";
    public final static String APPLICATION_NAME = "lefull-service-demo-dev";
    public final static String app_sendUrl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send";
    
    public final static String SystemVersions = "/api/v1";

    public static final String PREPAY = "0";
    public static final String PAYSUCCESS = "1";
    public static final String PAYFAIL = "2";
    public static final String OUTRADENO = "1";

    public enum MsgPushState {
        NotSend(1,"未发送"),
        SendSucc(2,"已发送"),
        SendFail(3,"发送失败");
        private MsgPushState(int state,String msg) {
            this.state = state;
            this.msg = msg;
        }
        private int state;
        private String msg;

        public int getState() {
            return state;
        }


    }

    public enum DeleteState {
        NotDel(1,"存在状态"),
        DelSucc(2,"删除状态");
        private int state;
        private String msg;
        private DeleteState(int state,String msg){
            this.state = state;
            this.msg = msg;

        }

        public int getState() {
            return state;
        }
    }
/********************---系统全局变量----****************/


}
