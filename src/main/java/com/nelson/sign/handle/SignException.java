package com.nelson.sign.handle;

import com.nelson.sign.enums.ResultEnum;

public class SignException extends RuntimeException{

    private Integer code;

    public SignException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
