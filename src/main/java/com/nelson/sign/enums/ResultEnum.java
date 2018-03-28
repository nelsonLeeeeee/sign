package com.nelson.sign.enums;

public enum ResultEnum {
    UNKONW_ERROR(-1, "未知错误"),
    SUCCESS(200, "成功"),
    ERR_COUNT_PASSWORD(101, "账号或密码错误"),
    ERR_SIGN_EARLY(102, "无法签到,请在上课前30分钟内签到"),
    ERR_SIGN_LATE(103,"由于您迟到时间过长,无法签到")

    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
