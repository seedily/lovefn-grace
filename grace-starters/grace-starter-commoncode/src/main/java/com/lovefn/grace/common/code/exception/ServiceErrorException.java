package com.lovefn.grace.common.code.exception;


import com.lovefn.grace.common.api.entity.ResultCode;

/**
 * 业务异常
 */
public class ServiceErrorException extends RuntimeException {

    private ResultCode resultCode;
    private String errorMsg;

    /**
     * Constructor.
     *
     * @param resultCode
     * @param errorMsg
     */
    public ServiceErrorException(ResultCode resultCode, String errorMsg) {
        super(errorMsg);
        this.resultCode = resultCode;
        this.errorMsg = errorMsg;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
