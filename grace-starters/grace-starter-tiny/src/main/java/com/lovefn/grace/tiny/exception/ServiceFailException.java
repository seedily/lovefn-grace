package com.lovefn.grace.tiny.exception;

import com.lovefn.grace.common.api.entity.ResultCode;
import lombok.Getter;

/**
 * 常规业务失败，建议打warn日志
 */
@Getter
public class ServiceFailException extends RuntimeException {

    private String code;
    private String msg;

    /**
     * Constructor.
     */
    public ServiceFailException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    /**
     * Constructor.
     */
    public ServiceFailException(String code, String errorMsg) {
        super(errorMsg);
        this.code = code;
        this.msg = errorMsg;
    }


}
