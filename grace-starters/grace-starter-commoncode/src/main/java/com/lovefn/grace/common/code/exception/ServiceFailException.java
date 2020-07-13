package com.lovefn.grace.common.code.exception;

import com.lovefn.grace.common.api.entity.ResultCode;
import lombok.Getter;

/**
 * 业务失败
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
